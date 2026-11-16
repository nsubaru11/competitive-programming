"""実行設定の移行・個別設定の保持・削除範囲を検証する。"""
import os
from pathlib import Path
import runpy
import subprocess
import sys
import tempfile
import threading
import time
import unittest
import xml.etree.ElementTree as ET

SCRIPT = Path(__file__).resolve().parents[1] / "sync-clion-run-configurations.py"
SYNC = runpy.run_path(str(SCRIPT))
PROJECT_NAME = "competitive-programming-clion"
TARGET = "atcoder_abc059_B"


class SyncRunConfigurationsTest(unittest.TestCase):
	def setUp(self):
		self.temporary = tempfile.TemporaryDirectory()
		self.addCleanup(self.temporary.cleanup)
		self.project = Path(self.temporary.name)
		self.idea = self.project / ".idea"
		self.idea.mkdir()
		self.configurations = self.idea / "runConfigurations"

	def sync(self, targets=None):
		if targets is None:
			targets = {TARGET: "atcoder / abc059", "template_cpp": ""}
		return SYNC["sync_configurations"](self.project, PROJECT_NAME, targets)

	def configuration(self, name=TARGET):
		return ET.parse(self.configurations / f"cp_{name}.xml").find("configuration")

	def workspace(self, configuration=""):
		path = self.idea / "workspace.xml"
		path.write_text(f'''<project version="4">
  <component name="RunManager" selected="CMake Application.{TARGET}">{configuration}</component>
  <component name="CMakeSettings"><configurations>
    <configuration ENABLED="true" PROFILE_NAME="WSL Debug" />
  </configurations></component>
</project>''', encoding="utf-8")
		return path

	def test_new_configurations_have_folder_and_build_step(self):
		self.workspace()
		self.assertEqual((2, 0), self.sync())
		configuration = self.configuration()
		self.assertEqual("atcoder / abc059", configuration.get("folderName"))
		self.assertEqual("WSL Debug", configuration.get("CONFIG_NAME"))
		self.assertEqual(TARGET, configuration.get("TARGET_NAME"))
		self.assertEqual(PROJECT_NAME, configuration.get("RUN_TARGET_PROJECT_NAME"))
		self.assertEqual(SYNC["BUILD_TASK"], configuration.find("method/option").get("name"))
		self.assertIsNone(self.configuration("template_cpp").get("folderName"))

	def test_migrate_saved_settings_without_writing_workspace(self):
		path = self.workspace(f'''<configuration name="{TARGET}" type="CMakeRunConfiguration"
PROJECT_NAME="{PROJECT_NAME}" TARGET_NAME="{TARGET}" CONFIG_NAME="WSL Run"
PROGRAM_PARAMS="--sample" REDIRECT_INPUT="true" INPUT_FILE="$PROJECT_DIR$/../../input.txt">
<envs><env name="SAMPLE" value="1" /></envs><method v="2" />
</configuration>''')
		before = path.read_bytes()
		self.sync()
		configuration = self.configuration()
		self.assertEqual(before, path.read_bytes())
		self.assertEqual("WSL Run", configuration.get("CONFIG_NAME"))
		self.assertEqual("--sample", configuration.get("PROGRAM_PARAMS"))
		self.assertEqual("true", configuration.get("REDIRECT_INPUT"))
		self.assertEqual("$PROJECT_DIR$/../../input.txt", configuration.get("INPUT_FILE"))
		self.assertEqual("1", configuration.find("envs/env").get("value"))
		self.assertEqual(0, len(configuration.find("method")))

	def test_inherit_template_and_keep_user_edits(self):
		self.workspace('''<configuration default="true" type="CMakeRunConfiguration"
factoryName="Application" PROGRAM_PARAMS="--template"><method v="2" /></configuration>''')
		self.sync()
		path = self.configurations / f"cp_{TARGET}.xml"
		root = ET.parse(path).getroot()
		configuration = root.find("configuration")
		self.assertIsNone(configuration.get("default"))
		self.assertEqual("--template", configuration.get("PROGRAM_PARAMS"))
		configuration.set("PROGRAM_PARAMS", "--edited")
		configuration.set("CONFIG_NAME", "WSL Run")
		path.write_bytes(SYNC["xml_bytes"](root))
		self.sync({TARGET: "atcoder / changed & 日本語"})
		self.assertEqual("--edited", self.configuration().get("PROGRAM_PARAMS"))
		self.assertEqual("WSL Run", self.configuration().get("CONFIG_NAME"))
		self.assertEqual("atcoder / changed & 日本語", self.configuration().get("folderName"))

	def test_no_changes_leave_files_and_timestamps_untouched(self):
		self.sync()
		before = {path: (path.read_bytes(), path.stat().st_mtime_ns)
			for path in self.idea.rglob("*") if path.is_file()}
		self.assertEqual((0, 0), self.sync())
		self.assertEqual(before, {path: (path.read_bytes(), path.stat().st_mtime_ns) for path in before})

	def test_existing_shared_configuration_is_reused_and_not_pruned(self):
		self.configurations.mkdir()
		path = self.configurations / "my-saved-configuration.xml"
		path.write_text(f'''<component name="ProjectRunConfigurationManager">
<configuration name="{TARGET}" type="CMakeRunConfiguration" PROJECT_NAME="{PROJECT_NAME}"
TARGET_NAME="{TARGET}" PROGRAM_PARAMS="--keep" /></component>''', encoding="utf-8")
		self.sync()
		self.assertFalse((self.configurations / f"cp_{TARGET}.xml").exists())
		self.assertEqual("--keep", ET.parse(path).find("configuration").get("PROGRAM_PARAMS"))
		self.sync({})
		self.assertTrue(path.is_file())
		self.assertFalse((self.configurations / "cp_template_cpp.xml").exists())

	def test_prune_only_generated_files_and_preserve_repurposed_file(self):
		self.sync()
		custom = self.configurations / "custom.xml"
		custom.write_text('<component><configuration name="custom" /></component>', encoding="utf-8")
		path = self.configurations / f"cp_{TARGET}.xml"
		root = ET.parse(path).getroot()
		root.find("configuration").set("name", "my-renamed-configuration")
		path.write_bytes(SYNC["xml_bytes"](root))
		self.assertEqual((0, 1), self.sync({}))
		self.assertTrue(custom.is_file())
		self.assertTrue(path.is_file())

	def test_foreign_file_is_not_overwritten(self):
		self.configurations.mkdir()
		path = self.configurations / f"cp_{TARGET}.xml"
		before = b'<component><configuration name="unrelated" /></component>'
		path.write_bytes(before)
		with self.assertRaises(ValueError):
			self.sync()
		self.assertEqual(before, path.read_bytes())
		self.assertFalse((self.configurations / "cp_template_cpp.xml").exists())

	def test_state_file_cannot_delete_outside_managed_directory(self):
		(self.idea / "cp-run-configurations.json").write_text('["../../keep.xml"]', encoding="utf-8")
		with self.assertRaises(ValueError):
			self.sync({})
		self.assertFalse((self.idea / "cp-run-configurations.lock").exists())

	def test_stale_lock_is_removed(self):
		lock = self.idea / "cp-run-configurations.lock"
		lock.mkdir()
		old = time.time() - SYNC["STALE_LOCK_SECONDS"] - 1
		os.utime(lock, (old, old))
		self.assertEqual((2, 0), self.sync())
		self.assertFalse(lock.exists())

	def test_fresh_lock_is_waited_for(self):
		lock = self.idea / "cp-run-configurations.lock"
		lock.mkdir()
		timer = threading.Timer(0.3, lock.rmdir)
		timer.start()
		self.addCleanup(timer.join)
		self.assertEqual((2, 0), self.sync())

	def test_parallel_profile_sync_and_manifest(self):
		manifest = self.project / "targets.tsv"
		manifest.write_text(f"{TARGET}\tatcoder / abc059\ntemplate_cpp\t\n", encoding="utf-8")
		command = [sys.executable, str(SCRIPT), "--project-dir", str(self.project),
			"--project-name", PROJECT_NAME, "--targets", str(manifest)]
		processes = [subprocess.Popen(command, stdout=subprocess.PIPE, stderr=subprocess.PIPE) for _ in range(2)]
		for process in processes:
			stdout, stderr = process.communicate(timeout=15)
			self.assertEqual(0, process.returncode, stdout + stderr)
		self.assertEqual({TARGET: "atcoder / abc059", "template_cpp": ""}, SYNC["read_targets"](manifest))
		self.assertEqual((0, 0), self.sync())


if __name__ == "__main__":
	unittest.main()
