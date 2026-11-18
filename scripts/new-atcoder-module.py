"""AtCoderのJavaモジュールを用意し、IntelliJ IDEAへ登録する。

フォルダの実態を優先する。フォルダ・src/java・.imlのうち無いものだけを作り、
既にあるものはそのまま使う。modules.xmlには、無ければ登録する。
"""
import argparse
from pathlib import Path
import re
import sys

REPO = Path(__file__).resolve().parents[1]
ATCODER = REPO / "atcoder"
MODULES_XML = REPO / ".ide/idea/.idea/modules.xml"
PREFIXES = ("abc", "arc", "agc", "awc", "ahc")
MODULE_NAME_PATTERN = re.compile(r"[A-Za-z0-9](?:[A-Za-z0-9._-]*[A-Za-z0-9_-])?\Z")
MODULE_ENTRY_PATTERN = re.compile(r'<module\b[^>]*\sfilepath="([^"]*)"')

IML_TEMPLATE = """<?xml version="1.0" encoding="UTF-8"?>
<module type="JAVA_MODULE" version="4">
  <component name="NewModuleRootManager" inherit-compiler-output="true">
    <exclude-output />
    <content url="file://$MODULE_DIR$">
      <sourceFolder url="file://$MODULE_DIR$/src/java" isTestSource="false" />
    </content>
    <orderEntry type="inheritedJdk" />
    <orderEntry type="sourceFolder" forTests="false" />
    <orderEntry type="module" module-name="library.main" />
  </component>
</module>"""

EMPTY_MODULES_XML = """<?xml version="1.0" encoding="UTF-8"?>
<project version="4">
  <component name="ProjectModuleManager">
    <modules>
    </modules>
  </component>
</project>
"""


def fail(message):
	print(message, file=sys.stderr)
	raise SystemExit(1)


def resolve_module(module_name):
	"""モジュール名を正規化し、新しく作る場合のフォルダ（リポジトリからの相対パス）を返す。"""
	if not MODULE_NAME_PATTERN.fullmatch(module_name):
		fail(f"モジュール名に使えない文字が含まれています: {module_name}")

	lowered = module_name.lower()
	for prefix in PREFIXES:
		if not lowered.startswith(prefix):
			continue

		number = lowered[len(prefix):]
		if not number.isascii() or not number.isdigit():
			fail(f"コンテスト番号が不正です: {module_name}")

		contest_id = int(number)
		if contest_id < 1:
			fail(f"コンテスト番号は1以上にしてください: {module_name}")

		width = 4 if prefix == "awc" else 3
		module_name = prefix + f"{contest_id:0{width}d}"
		range_start = (contest_id - 1) // 50 * 50 + 1
		range_name = f"{range_start:0{width}d}-{range_start + 49:0{width}d}"
		return module_name, Path("atcoder") / prefix / range_name / module_name

	return module_name, Path("atcoder/other") / module_name


def find_existing_dir(module_name):
	"""atcoder配下に既にあるコンテストのフォルダを探し、リポジトリからの相対パスを返す。"""
	found = [
		path
		for pattern in (f"*/{module_name}", f"*/*/{module_name}")
		for path in ATCODER.glob(pattern)
	]
	if len(found) > 1:
		fail("同名のフォルダが複数あります: " + "、".join(path.relative_to(REPO).as_posix() for path in found))
	if found:
		if not found[0].is_dir():
			fail(f"フォルダではありません: {found[0].relative_to(REPO).as_posix()}")
		return found[0].relative_to(REPO)
	return None


def updated_modules_xml(iml_relative_path, module_name, recreate):
	"""modules.xmlの更新後の内容を返す。変更が不要ならNoneを返す。

	同じモジュール名で別のパスの登録が残っていれば取り除く。
	recreateがTrueなら、同じパスの登録も一度取り除いて登録し直す。
	"""
	if MODULES_XML.is_file():
		original = MODULES_XML.read_bytes()
		newline = "\r\n" if b"\r\n" in original else "\n"
		text = original.decode("utf-8")
	else:
		newline = "\n"
		text = EMPTY_MODULES_XML

	filepath = "$PROJECT_DIR$/../../" + iml_relative_path.as_posix()
	group = iml_relative_path.parent.parent.as_posix()
	suffix = f"/{module_name}.iml"
	lines = text.splitlines(keepends=True)
	kept = []
	registered = False
	removed_stale = False
	for line in lines:
		match = MODULE_ENTRY_PATTERN.search(line)
		if match:
			if match.group(1) == filepath and not recreate:
				registered = True
			elif match.group(1) == filepath or match.group(1).endswith(suffix):
				removed_stale = True
				continue
		kept.append(line)

	if registered:
		return "".join(kept).encode("utf-8") if removed_stale else None

	closing_indexes = [index for index, line in enumerate(kept) if "</modules>" in line]
	if len(closing_indexes) != 1:
		fail("modules.xmlに</modules>がありません。")
	closing_index = closing_indexes[0]
	indent = kept[closing_index][:len(kept[closing_index]) - len(kept[closing_index].lstrip())]
	entry = f'{indent}  <module fileurl="file://{filepath}" filepath="{filepath}" group="{group}" />{newline}'
	kept.insert(closing_index, entry)
	return "".join(kept).encode("utf-8")


def main():
	parser = argparse.ArgumentParser(description="AtCoderのJavaモジュールを用意します。")
	parser.add_argument("module_name", nargs="?", help="例: abc484。省略すると入力を求めます。")
	args = parser.parse_args()
	module_name = args.module_name
	if module_name is None:
		try:
			module_name = input("モジュール名: ")
		except EOFError:
			fail("モジュール名を入力してください。")
	module_name = module_name.strip()
	if not module_name:
		fail("モジュール名を入力してください。")

	module_name, relative_dir = resolve_module(module_name)
	existing_dir = find_existing_dir(module_name)
	if existing_dir is not None:
		relative_dir = existing_dir
	module_dir = REPO / relative_dir
	iml_path = module_dir / f"{module_name}.iml"
	source_dir = module_dir / "src/java"
	source_missing = not source_dir.is_dir()
	modules_content = updated_modules_xml(relative_dir / f"{module_name}.iml", module_name, source_missing)

	report = [f"フォルダ: {relative_dir.as_posix()}（{'既存を使用' if existing_dir is not None else '作成'}）"]
	if not source_missing:
		report.append("src/java: 既存を使用")
	else:
		source_dir.mkdir(parents=True, exist_ok=True)
		report.append("src/java: 作成")
	if iml_path.is_file():
		report.append(f"{iml_path.name}: 既存を使用")
	else:
		iml_path.write_bytes(IML_TEMPLATE.replace("\n", "\r\n").encode("utf-8"))
		report.append(f"{iml_path.name}: 作成")
	if modules_content is None:
		report.append("modules.xml: 登録済み")
	else:
		MODULES_XML.parent.mkdir(parents=True, exist_ok=True)
		MODULES_XML.write_bytes(modules_content)
		report.append("modules.xml: 登録")
	print("\n".join(report))


if __name__ == "__main__":
	main()
