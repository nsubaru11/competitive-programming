import argparse
from datetime import datetime
import json
from pathlib import Path
import re

REPO = Path(__file__).resolve().parents[1]
TEMPLATES = (
	("java/TemplateCode.java", "internal/Class.java", "IntelliJIdea"),
	("java/TemplateCode17.java", "Class17.java", "IntelliJIdea"),
	("java/AOJ.java", "AOJ.java", "IntelliJIdea"),
	("java/ICPC.java", "ICPC.java", "IntelliJIdea"),
	("java/Judge.java", "Judge.java", "IntelliJIdea"),
	("java/Test.java", "Test.java", "IntelliJIdea"),
	("python/TemplateCodeCPython.py", "CPython.py", "PyCharm"),
	("python/TemplateCodePyPy.py", "PyPy.py", "PyCharm"),
	("python/TemplateCodeCodon.py", "Codon.py", "PyCharm"),
	("python/TemplateCode.py", "Debug Console.py", "PyCharm"),
	("cpp/TemplateCode.cpp", "Competitive C++.cpp", "CLion"),
)

def ide_directories(root):
	if not root.is_dir():
		return []
	result = []
	for product in ("IntelliJIdea", "PyCharm", "CLion"):
		candidates = []
		for path in root.iterdir():
			match = re.fullmatch(re.escape(product) + r"(\d+)\.(\d+)", path.name)
			if path.is_dir() and match:
				candidates.append(((int(match[1]), int(match[2])), path))
		if candidates:
			result.append((product, max(candidates)[1]))
	return result

def planned_files(config_root, project_only):
	ide_roots = [] if project_only else ide_directories(config_root)
	result = {}
	for source_name, target_name, product in TEMPLATES:
		content = (REPO / "templates" / source_name).read_text(encoding="utf-8")
		if source_name.endswith(".cpp"):
			content = re.sub(r"(?m)^([ \t]*#.*)$", r"#[[\1]]#", content)
		data = content.replace("\r\n", "\n").encode("utf-8")
		result[REPO / ".ide/idea/.idea/fileTemplates" / target_name] = data
		for ide_product, directory in ide_roots:
			target = directory / "fileTemplates" / target_name
			if product == ide_product or target.is_file():
				result[target] = data
	return result

def main():
	parser = argparse.ArgumentParser(description="Sync repository templates to the project and installed JetBrains IDEs.")
	parser.add_argument("--check", action="store_true", help="Only check whether managed templates match.")
	parser.add_argument("--project-only", action="store_true", help="Only update .ide/idea/.idea/fileTemplates.")
	parser.add_argument("--ide-config-root", type=Path, default=Path.home() / "AppData/Roaming/JetBrains")
	args = parser.parse_args()
	changes = []
	for path, desired in planned_files(args.ide_config_root, args.project_only).items():
		before = path.read_bytes() if path.is_file() else None
		if before != desired:
			changes.append((path, before, desired))
	if not changes:
		print("All managed templates are up to date.")
		return
	if args.check:
		for path, _, _ in changes:
			print("Needs sync: " + str(path))
		raise SystemExit(1)
	backup_dir = REPO / "out/jetbrains-template-backups" / datetime.now().strftime("%Y%m%d-%H%M%S-%f")
	backup_dir.mkdir(parents=True)
	backup = [{"path": str(path), "before": before.decode("utf-8") if before is not None else None}
		for path, before, _ in changes]
	(backup_dir / "backup.json").write_text(json.dumps(backup, ensure_ascii=False, indent=2), encoding="utf-8")
	for path, before, desired in changes:
		current = path.read_bytes() if path.is_file() else None
		if current != before:
			raise RuntimeError("Template changed during sync: " + str(path))
		path.parent.mkdir(parents=True, exist_ok=True)
		path.write_bytes(desired)
		print("Updated: " + str(path))
	print("Backup: " + str(backup_dir))

if __name__ == "__main__":
	main()
