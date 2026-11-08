"""JetBrainsのIDEへ、テンプレートと各IDEプロジェクトの共通設定を同期する。"""
import argparse
import csv
import io
import json
from pathlib import Path
import re
import shutil
import subprocess
import sys

REPO = Path(__file__).resolve().parents[1]
TEMPLATES = REPO / "templates"
IDE_ROOT = REPO / ".ide"
SETTINGS_SOURCE = IDE_ROOT / "idea/.idea"
BACKUP_DIR = REPO / "out/jetbrains-sync-backup"
DEFAULT_CONFIG_ROOT = Path.home() / "AppData/Roaming/JetBrains"

# templates のフォルダと、そのテンプレートを使うIDE
PRODUCTS = {"java": "IntelliJIdea", "python": "PyCharm", "cpp": "CLion"}
# IDE標準のテンプレートを上書きするもの。fileTemplates/internal に置く
INTERNAL = {"Class"}
# .ide/idea/.idea と同じ内容にそろえるフォルダ
MIRRORED = ("codeStyles", "inspectionProfiles", "scopes")
# 各IDEで追加した単語を失わないよう、全IDEの和集合をとる
DICTIONARY = "dictionaries/project.xml"
WORD = re.compile(r"<w>(.*?)</w>")
PROCESSES = ("idea64.exe", "pycharm64.exe", "clion64.exe", "webstorm64.exe", "rustrover64.exe")


def latest_config(config_root, product):
	candidates = []
	for path in config_root.glob(product + "*"):
		match = re.fullmatch(re.escape(product) + r"(\d+)\.(\d+)", path.name)
		if path.is_dir() and match:
			candidates.append(((int(match[1]), int(match[2])), path))
	return max(candidates)[1] if candidates else None


def template_content(path):
	text = path.read_text(encoding="utf-8").replace("\r\n", "\n")
	if path.suffix == ".java":
		# Javaのテンプレートは ${NAME} や #if を使うので、そのまま渡す
		return text.encode("utf-8")
	# Velocityの命令やコメント(#・##・$)として解釈されないよう、該当する行をそのまま出力させる
	lines = []
	for line in text.split("\n"):
		if ("#" in line or "$" in line) and "]]#" not in line:
			line = "#[[" + line + "]]#"
		lines.append(line)
	return "\n".join(lines).encode("utf-8")


def template_plan(config_root, prune, notes):
	result = {}
	for folder, product in PRODUCTS.items():
		directory = latest_config(config_root, product)
		if directory is None:
			notes.append(product + " の設定フォルダが見つからないため、テンプレートを同期しない")
			continue
		target_root = directory / "fileTemplates"
		desired = {}
		for source in sorted((TEMPLATES / folder).iterdir()):
			if not source.is_file() or source.name == "README.md":
				continue
			name = ("internal/" if source.stem in INTERNAL else "") + source.name
			desired[target_root / name] = template_content(source)
		result.update(desired)
		if prune:
			# includes や code など、ほかのフォルダには触れない
			for existing in [*target_root.glob("*"), *target_root.glob("internal/*")]:
				if existing.is_file() and existing not in desired:
					result[existing] = None
	return result


def dictionary_xml(words):
	lines = ['<component name="ProjectDictionaryState">', '  <dictionary name="project">', "    <words>"]
	lines += ["      <w>" + word + "</w>" for word in sorted(words)]
	lines += ["    </words>", "  </dictionary>", "</component>"]
	return ("\n".join(lines) + "\n").encode("utf-8")


def settings_plan():
	result = {}
	targets = sorted(path for path in IDE_ROOT.glob("*/.idea") if path != SETTINGS_SOURCE)
	for target in targets:
		for name in MIRRORED:
			for source in (SETTINGS_SOURCE / name).rglob("*"):
				if source.is_file():
					result[target / source.relative_to(SETTINGS_SOURCE)] = source.read_bytes()
			for existing in (target / name).rglob("*"):
				if existing.is_file() and not (SETTINGS_SOURCE / existing.relative_to(target)).is_file():
					result[existing] = None
	current = {}
	for project in [SETTINGS_SOURCE, *targets]:
		path = project / DICTIONARY
		current[path] = set(WORD.findall(path.read_text(encoding="utf-8"))) if path.is_file() else None
	words = set().union(*(found for found in current.values() if found))
	# 単語がそろっていれば、IDEが保存した書式のまま残す
	for path, found in current.items():
		if words and found != words:
			result[path] = dictionary_xml(words)
	return result


def running_ides():
	if sys.platform != "win32":
		return []
	output = subprocess.run(["tasklist", "/FO", "CSV", "/NH"], capture_output=True, text=True, errors="replace").stdout
	names = {row[0].lower() for row in csv.reader(io.StringIO(output)) if row}
	return [name for name in PROCESSES if name in names]


def display(path):
	for base, label in ((REPO, ""), (DEFAULT_CONFIG_ROOT, "<JetBrains>/")):
		try:
			return label + path.relative_to(base).as_posix()
		except ValueError:
			pass
	return str(path)


def main():
	parser = argparse.ArgumentParser(description="Sync templates and shared project settings to JetBrains IDEs.")
	parser.add_argument("--check", action="store_true", help="Only show what would change.")
	parser.add_argument("--prune", action="store_true", help="Also remove IDE templates that no longer exist in templates/.")
	parser.add_argument("--force", action="store_true", help="Write even while a JetBrains IDE is running.")
	parser.add_argument("--config-root", type=Path, default=DEFAULT_CONFIG_ROOT)
	args = parser.parse_args()

	notes = []
	plan = {**template_plan(args.config_root, args.prune, notes), **settings_plan()}
	changes = []
	for path, desired in plan.items():
		before = path.read_bytes() if path.is_file() else None
		if before != desired:
			changes.append((path, before, desired))
	for note in notes:
		print("Note: " + note)
	if not changes:
		print("Everything is up to date.")
		return
	for path, _, desired in changes:
		print(("Remove: " if desired is None else "Update: ") + display(path))
	if args.check:
		raise SystemExit(1)

	running = running_ides()
	if running and not args.force:
		print("Close the IDEs first, or they may overwrite the result: " + ", ".join(running))
		raise SystemExit(2)

	# 退避は直前の1回分だけ残す
	shutil.rmtree(BACKUP_DIR, ignore_errors=True)
	BACKUP_DIR.mkdir(parents=True)
	backup = [{"path": str(path), "before": before.decode("utf-8") if before is not None else None}
		for path, before, _ in changes]
	(BACKUP_DIR / "backup.json").write_text(json.dumps(backup, ensure_ascii=False, indent=2), encoding="utf-8")
	for path, before, desired in changes:
		current = path.read_bytes() if path.is_file() else None
		if current != before:
			raise RuntimeError("Changed during sync: " + str(path))
		if desired is None:
			path.unlink()
			continue
		path.parent.mkdir(parents=True, exist_ok=True)
		path.write_bytes(desired)
	print("Done. Backup: " + display(BACKUP_DIR / "backup.json"))


if __name__ == "__main__":
	main()
