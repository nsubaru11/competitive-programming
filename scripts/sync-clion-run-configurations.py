"""CMakeのターゲットをCLionの実行設定フォルダへ自動で振り分ける。"""
import argparse
from contextlib import contextmanager
from copy import deepcopy
import json
import os
from pathlib import Path
import re
import tempfile
import time
import xml.etree.ElementTree as ET

CONFIGURATION_TYPE = "CMakeRunConfiguration"
STALE_LOCK_SECONDS = 60
BUILD_TASK = "com.jetbrains.cidr.execution.CidrBuildBeforeRunTaskProvider$BuildBeforeRunTask"


@contextmanager
def sync_lock(idea):
	# WSL Debug / WSL Runの同時Reloadでも、同じ実行設定へ並行して書かない。
	lock = idea / "cp-run-configurations.lock"
	deadline = time.monotonic() + 10
	while True:
		try:
			lock.mkdir()
			break
		except FileExistsError:
			# 同期は数秒で終わるので、古いロックは異常終了の残りとして取り除く。
			try:
				if time.time() - lock.stat().st_mtime > STALE_LOCK_SECONDS:
					lock.rmdir()
					continue
			except FileNotFoundError:
				continue
			if time.monotonic() >= deadline:
				raise RuntimeError(f"実行設定の同期ロックを取得できません: {lock}")
			time.sleep(0.05)
	try:
		yield
	finally:
		lock.rmdir()


def write_if_changed(path, content):
	if path.is_file() and path.read_bytes() == content:
		return False
	with tempfile.NamedTemporaryFile(dir=path.parent, prefix=".cp-", suffix=".tmp", delete=False) as file:
		file.write(content)
		temporary = Path(file.name)
	try:
		os.replace(temporary, path)
	finally:
		temporary.unlink(missing_ok=True)
	return True


def read_targets(path):
	result = {}
	for line in path.read_text(encoding="utf-8").splitlines():
		name, folder = line.split("\t", 1)
		if not re.fullmatch(r"[A-Za-z_][A-Za-z_0-9]*", name) or name in result:
			raise ValueError(f"実行ターゲット名が不正、または重複しています: {name}")
		result[name] = folder
	return result


def is_canonical(configuration, project_name):
	return (configuration.get("type") == CONFIGURATION_TYPE
		and configuration.get("PROJECT_NAME") == project_name
		and configuration.get("name") == configuration.get("TARGET_NAME")
		and configuration.get("name") is not None)


def xml_bytes(root):
	ET.indent(root, space="  ")
	return (ET.tostring(root, encoding="unicode") + "\n").encode("utf-8")


def sync_configurations(project_dir, project_name, targets):
	idea = project_dir / ".idea"
	idea.mkdir(parents=True, exist_ok=True)
	with sync_lock(idea):
		return sync_locked(idea, project_name, targets)


def sync_locked(idea, project_name, targets):
	directory = idea / "runConfigurations"
	directory.mkdir(exist_ok=True)
	state_file = idea / "cp-run-configurations.json"
	owned = json.loads(state_file.read_text(encoding="utf-8")) if state_file.is_file() else []
	# 状態ファイルに書かれたパスを、削除対象としてそのまま信用しない。
	if not isinstance(owned, list) or any(
		not isinstance(name, str) or not re.fullmatch(r"cp_[A-Za-z_][A-Za-z_0-9]*\.xml", name)
		for name in owned
	):
		raise ValueError(f"自動生成ファイルの一覧が不正です: {state_file}")

	workspace_file = idea / "workspace.xml"
	workspace = ET.parse(workspace_file).getroot() if workspace_file.is_file() else ET.Element("project")
	run_manager = workspace.find("./component[@name='RunManager']")
	saved = {}
	template = None
	if run_manager is not None:
		for configuration in run_manager.findall("configuration"):
			if is_canonical(configuration, project_name):
				saved[configuration.get("name")] = configuration
			elif configuration.get("type") == CONFIGURATION_TYPE and configuration.get("default") == "true":
				template = configuration
	profile = workspace.find("./component[@name='CMakeSettings']/configurations/configuration[@ENABLED='true']")

	shared = {}
	for path in sorted(directory.glob("*.xml")):
		root = ET.parse(path).getroot()
		for configuration in root.findall("configuration"):
			if is_canonical(configuration, project_name):
				name = configuration.get("name")
				if name in shared:
					raise ValueError(f"同名の実行設定があります: {shared[name][0]}, {path}")
				shared[name] = (path, root, configuration)

	updates = {}
	managed = set()
	for name, folder in targets.items():
		if name in shared:
			path, root, configuration = shared[name]
			if path.name in owned:
				managed.add(path.name)
			if configuration.get("folderName", "") == folder:
				continue
		else:
			path = directory / f"cp_{name}.xml"
			if path.exists():
				raise ValueError(f"別の実行設定を上書きできません: {path}")
			managed.add(path.name)
			configuration = deepcopy(saved.get(name, template))
			if configuration is None:
				configuration = ET.Element("configuration", {
					"type": CONFIGURATION_TYPE, "factoryName": "Application",
				})
				method = ET.SubElement(configuration, "method", {"v": "2"})
				ET.SubElement(method, "option", {"name": BUILD_TASK, "enabled": "true"})
			configuration.attrib.pop("default", None)
			configuration.attrib.pop("temporary", None)
			configuration.set("name", name)
			for key in ("TARGET_NAME", "RUN_TARGET_NAME"):
				configuration.set(key, name)
			for key in ("PROJECT_NAME", "RUN_TARGET_PROJECT_NAME"):
				configuration.set(key, project_name)
			if "CONFIG_NAME" not in configuration.attrib and profile is not None:
				configuration.set("CONFIG_NAME", profile.get("PROFILE_NAME", ""))
			root = ET.Element("component", {"name": "ProjectRunConfigurationManager"})
			root.append(configuration)
		if folder:
			configuration.set("folderName", folder)
		else:
			configuration.attrib.pop("folderName", None)
		updates[path] = xml_bytes(root)

	removals = []
	for filename in set(owned) - managed:
		path = directory / filename
		if path.is_file():
			root = ET.parse(path).getroot()
			configurations = root.findall("configuration")
			# ユーザーが別の設定へ作り替えたファイルは削除しない。
			if (len(configurations) == 1 and is_canonical(configurations[0], project_name)
				and filename == f"cp_{configurations[0].get('name')}.xml"):
				removals.append(path)

	changed = sum(write_if_changed(path, content) for path, content in updates.items())
	for path in removals:
		path.unlink()
	write_if_changed(state_file, (json.dumps(sorted(managed), indent=2) + "\n").encode("utf-8"))
	return changed, len(removals)


def main():
	parser = argparse.ArgumentParser(description=__doc__)
	parser.add_argument("--project-dir", type=Path, required=True)
	parser.add_argument("--project-name", required=True)
	parser.add_argument("--targets", type=Path, required=True)
	args = parser.parse_args()
	changed, removed = sync_configurations(args.project_dir, args.project_name, read_targets(args.targets))
	print(f"CLion run configurations: {changed} updated, {removed} removed")


if __name__ == "__main__":
	main()
