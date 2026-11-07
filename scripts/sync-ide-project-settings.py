import argparse
from datetime import datetime
import json
from pathlib import Path
import re

REPO = Path(__file__).resolve().parents[1]
IDE_ROOT = REPO / ".ide"
SOURCE = IDE_ROOT / "idea/.idea"
# .ide/idea/.idea の内容をそのまま写すフォルダ
MIRRORED = ("codeStyles", "inspectionProfiles", "scopes")
# 各IDEで追加した単語を失わないよう、全IDEの和集合をとる
DICTIONARY = "dictionaries/project.xml"
WORD = re.compile(r"<w>(.*?)</w>")

def targets():
	return sorted(path for path in IDE_ROOT.glob("*/.idea") if path != SOURCE)

def dictionary_xml(words):
	lines = ['<component name="ProjectDictionaryState">', '  <dictionary name="project">', "    <words>"]
	lines += ["      <w>" + word + "</w>" for word in sorted(words)]
	lines += ["    </words>", "  </dictionary>", "</component>"]
	return ("\n".join(lines) + "\n").encode("utf-8")

def planned_files():
	result = {}
	for target in targets():
		for name in MIRRORED:
			source_dir = SOURCE / name
			for source in source_dir.rglob("*"):
				if source.is_file():
					result[target / source.relative_to(SOURCE)] = source.read_bytes()
			for existing in (target / name).rglob("*"):
				if existing.is_file() and not (SOURCE / existing.relative_to(target)).is_file():
					result[existing] = None
	projects = [SOURCE, *targets()]
	current = {}
	for project in projects:
		path = project / DICTIONARY
		current[path] = set(WORD.findall(path.read_text(encoding="utf-8"))) if path.is_file() else None
	words = set().union(*(found for found in current.values() if found))
	# 単語がそろっていれば、IDEが保存した書式のまま残す
	for path, found in current.items():
		if words and found != words:
			result[path] = dictionary_xml(words)
	return result

def main():
	parser = argparse.ArgumentParser(description="Sync shared project settings from .ide/idea to the other IDE projects.")
	parser.add_argument("--check", action="store_true", help="Only check whether the shared settings match.")
	args = parser.parse_args()
	changes = []
	for path, desired in planned_files().items():
		before = path.read_bytes() if path.is_file() else None
		if before != desired:
			changes.append((path, before, desired))
	if not changes:
		print("All shared project settings are up to date.")
		return
	if args.check:
		for path, _, desired in changes:
			print(("Needs removal: " if desired is None else "Needs sync: ") + str(path))
		raise SystemExit(1)
	backup_dir = REPO / "out/ide-settings-backups" / datetime.now().strftime("%Y%m%d-%H%M%S-%f")
	backup_dir.mkdir(parents=True)
	backup = [{"path": str(path), "before": before.decode("utf-8") if before is not None else None}
		for path, before, _ in changes]
	(backup_dir / "backup.json").write_text(json.dumps(backup, ensure_ascii=False, indent=2), encoding="utf-8")
	for path, before, desired in changes:
		current = path.read_bytes() if path.is_file() else None
		if current != before:
			raise RuntimeError("Setting changed during sync: " + str(path))
		if desired is None:
			path.unlink()
			print("Removed: " + str(path))
			continue
		path.parent.mkdir(parents=True, exist_ok=True)
		path.write_bytes(desired)
		print("Updated: " + str(path))
	print("Backup: " + str(backup_dir))

if __name__ == "__main__":
	main()
