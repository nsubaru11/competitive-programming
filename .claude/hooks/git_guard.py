"""Claude Code の PreToolUse hook。シェルで実行する git コマンドを検査する。

使い方: python git_guard.py <pre|readonly>
	pre       全体の add と commit、force push を拒否し、push はユーザーに確認する。
	readonly  読み取り専用の git コマンドだけを許可する（scope-reviewer 用）。

入力は標準入力の JSON、判定は標準出力の JSON で返す。判定しない場合は何も出力しない。
"""

import json
import re
import sys
from pathlib import Path

PROJECT_DIR = Path(__file__).resolve().parents[2]
TOOLS_DIR = PROJECT_DIR / "tools"
CP_TOOLKIT_DIST = "userscripts/CpToolkit/dist/CpToolkit.user.js"
CP_TOOLKIT_META = "userscripts/CpToolkit/meta.json"

SEPARATORS = ("&&", "||", "|&", ";", "|", "&", "\n")
CHANGE_DIRECTORY = {"cd", "chdir", "pushd", "sl", "set-location", "push-location"}
# 値を別のトークンで受け取る git 全体のオプション
GIT_VALUE_OPTIONS = {"-c", "--git-dir", "--work-tree", "--namespace", "--exec-path", "--super-prefix"}
# 値を受け取る git commit のオプション
COMMIT_VALUE_FLAGS = set("mFCct")
COMMIT_VALUE_OPTIONS = {
	"--message",
	"--file",
	"--reuse-message",
	"--reedit-message",
	"--fixup",
	"--squash",
	"--author",
	"--date",
	"--template",
	"--cleanup",
	"--trailer",
}
# 後ろのコマンドをそのまま実行する前置き
WRAPPERS = {"command", "builtin", "exec", "time", "timeout", "nice", "nohup", "stdbuf", "env"}
READONLY_SUBCOMMANDS = {
	"blame",
	"cat-file",
	"check-ignore",
	"diff",
	"grep",
	"log",
	"ls-files",
	"merge-base",
	"rev-parse",
	"show",
	"status",
}


def scan(command: str, powershell: bool) -> list[list[str]]:
	"""コマンドを区切り記号で分け、それぞれをトークンの並びにする。

	引用符の中は分けない。引用符の外のリダイレクトは `>` というトークンとして残す。
	"""
	escape = "`" if powershell else "\\"
	segments: list[list[str]] = []
	tokens: list[str] = []
	token: list[str] = []
	in_token = False
	quote = ""
	i = 0
	n = len(command)

	def end_token() -> None:
		nonlocal in_token
		if in_token:
			tokens.append("".join(token))
			token.clear()
			in_token = False

	def end_segment() -> None:
		nonlocal tokens
		end_token()
		if tokens:
			segments.append(tokens)
			tokens = []

	while i < n:
		ch = command[i]
		if quote:
			if ch == quote:
				quote = ""
			elif ch == escape and quote == '"' and i + 1 < n:
				token.append(command[i + 1])
				i += 1
			else:
				token.append(ch)
			i += 1
			continue
		if ch in "'\"":
			quote = ch
			in_token = True
			i += 1
			continue
		if ch == escape and i + 1 < n:
			# 行末のエスケープは行の継続なので、何も残さない
			if command[i + 1] != "\n":
				token.append(command[i + 1])
				in_token = True
			i += 2
			continue
		separator = next((s for s in SEPARATORS if command.startswith(s, i)), "")
		if separator:
			end_segment()
			i += len(separator)
			continue
		if ch == ">":
			end_token()
			tokens.append(">")
			i += 1
			continue
		if ch.isspace():
			end_token()
			i += 1
			continue
		token.append(ch)
		in_token = True
		i += 1
	end_segment()
	return segments


def is_git(word: str) -> bool:
	return re.fullmatch(r"(.*[/\\])?git(\.exe)?", word, re.IGNORECASE) is not None


def parse_git(tokens: list[str]) -> tuple[list[str], str, list[str]] | None:
	"""git のコマンドなら (-C の値の並び, サブコマンド, 引数) を返す。"""
	i = 0
	wrapped = False
	while i < len(tokens):
		word = tokens[i].lower()
		if word in WRAPPERS:
			wrapped = True
		elif wrapped and re.fullmatch(r"-.*|\d+(\.\d+)?[smhd]?", word):
			# `timeout 30` や `nice -n 5` の、前置きに渡す値
			pass
		elif not re.fullmatch(r"[A-Za-z_][A-Za-z0-9_]*=.*", tokens[i]):
			break
		i += 1
	if i >= len(tokens) or not is_git(tokens[i]):
		return None
	i += 1
	directories: list[str] = []
	while i < len(tokens) and tokens[i].startswith("-"):
		option = tokens[i]
		if option == "-C" and i + 1 < len(tokens):
			directories.append(tokens[i + 1])
			i += 2
		elif option in GIT_VALUE_OPTIONS:
			i += 2
		else:
			i += 1
	if i >= len(tokens):
		return None
	return directories, tokens[i].lower(), tokens[i + 1 :]


def short_flags(argument: str) -> str:
	"""`-am` のような短いオプションの並びなら、その文字の並びを返す。"""
	if argument.startswith("--") or not argument.startswith("-"):
		return ""
	return argument[1:]


def check_add(arguments: list[str]) -> str:
	for argument in arguments:
		flags = short_flags(argument)
		if (
			argument in {".", "./", ".\\", ":/", "*", "--all", "--update", "--no-ignore-removal"}
			or "A" in flags
			or "u" in flags
		):
			return f"git add {argument} は使わない。ユーザーが編集中のファイルを巻き込まないよう、追加するファイルを名指しする。"
	return ""


def check_commit(arguments: list[str]) -> str:
	skip = False
	for argument in arguments:
		if skip:
			skip = False
			continue
		if argument == "--":
			break
		if argument in COMMIT_VALUE_OPTIONS:
			skip = True
			continue
		if argument == "--all":
			return "git commit --all は使わない。git add でファイルを名指ししてからコミットする。"
		for position, flag in enumerate(short_flags(argument)):
			if flag in COMMIT_VALUE_FLAGS:
				# 値が同じトークンに続いていなければ、次のトークンが値になる
				skip = position == len(argument) - 2
				break
			if flag == "a":
				return "git commit -a は使わない。git add でファイルを名指ししてからコミットする。"
	return ""


def check_force_push(arguments: list[str]) -> str:
	for argument in arguments:
		if argument.startswith("--force") or argument == "--mirror" or "f" in short_flags(argument):
			return f"git push {argument} は使わない。force push は禁止されている。"
		if argument.startswith("+"):
			return f"git push の {argument} は強制更新になる。force push は禁止されている。"
	return ""


def to_windows_path(path: str) -> str:
	"""Git Bash の `/c/Users` の形を `C:/Users` に直す。"""
	match = re.fullmatch(r"/([A-Za-z])(/.*)?", path)
	return f"{match[1].upper()}:{match[2] or '/'}" if match else path


def resolve(base: Path, path: str) -> Path:
	candidate = Path(to_windows_path(path)).expanduser()
	return candidate if candidate.is_absolute() else base / candidate


def git_output(directory: Path, *arguments: str) -> str | None:
	# push のときだけ使うので、起動を速くするためにここで読み込む
	import subprocess

	try:
		result = subprocess.run(
			["git", "-C", str(directory), *arguments],
			capture_output=True,
			encoding="utf-8",
			errors="replace",
			timeout=10,
		)
	except (OSError, subprocess.TimeoutExpired):
		return None
	return result.stdout.strip() if result.returncode == 0 else None


def same_directory(left: Path, right: Path) -> bool:
	try:
		return left.resolve().samefile(right.resolve())
	except OSError:
		return False


def cp_toolkit_warning(directory: Path) -> str:
	"""本体か tools の push で、配布物が変わったのにバージョンが同じなら注意文を返す。"""
	top = git_output(directory, "rev-parse", "--show-toplevel")
	if top is None or not any(same_directory(Path(top), d) for d in (PROJECT_DIR, TOOLS_DIR)):
		return ""
	upstream = git_output(TOOLS_DIR, "rev-parse", "--abbrev-ref", "@{upstream}")
	if upstream is None:
		return ""
	changed = git_output(TOOLS_DIR, "diff", "--name-only", upstream, "HEAD", "--", CP_TOOLKIT_DIST)
	if not changed:
		return ""
	versions = []
	for revision in (upstream, "HEAD"):
		meta = git_output(TOOLS_DIR, "show", f"{revision}:{CP_TOOLKIT_META}")
		try:
			versions.append(json.loads(meta or "")["version"])
		except (ValueError, KeyError, TypeError):
			return ""
	if versions[0] != versions[1]:
		return ""
	return (
		f"注意: CP Toolkit の配布物が変わっていますが、バージョンは {versions[1]} のままです。"
		"動作が変わる変更なら、push の前に meta.json の version を上げてビルドし直します。"
	)


def decide_pre(segments: list[list[str]], cwd: Path) -> tuple[str, str] | None:
	"""(判定, 理由) を返す。判定しない場合は None。"""
	directory = cwd
	ask = ""
	for tokens in segments:
		if tokens[0].lower() in CHANGE_DIRECTORY:
			paths = [t for t in tokens[1:] if not t.startswith("-")]
			if paths:
				directory = resolve(directory, paths[0])
			continue
		parsed = parse_git(tokens)
		if parsed is None:
			continue
		directories, subcommand, arguments = parsed
		target = directory
		for path in directories:
			target = resolve(target, path)
		if subcommand == "add":
			reason = check_add(arguments)
		elif subcommand == "commit":
			reason = check_commit(arguments)
		elif subcommand == "push":
			reason = check_force_push(arguments)
			if not reason and not ask:
				warning = cp_toolkit_warning(target)
				ask = "push の前の確認です。" + warning
		else:
			reason = ""
		if reason:
			return "deny", reason
	return ("ask", ask) if ask else None


def decide_readonly(segments: list[list[str]]) -> tuple[str, str] | None:
	for tokens in segments:
		parsed = parse_git(tokens)
		allowed = (
			parsed is not None
			and parsed[1] in READONLY_SUBCOMMANDS
			and ">" not in tokens
			and not any(a.startswith("--output") for a in parsed[2])
		)
		if not allowed:
			return (
				"deny",
				"このエージェントは読み取り専用で、使えるコマンドは git の "
				+ "、".join(sorted(READONLY_SUBCOMMANDS))
				+ " だけ。ファイルの内容は Read、Grep、Glob で読む。",
			)
	return None


def main() -> None:
	mode = sys.argv[1] if len(sys.argv) > 1 else "pre"
	payload = json.loads(sys.stdin.buffer.read().decode("utf-8"))
	command = (payload.get("tool_input") or {}).get("command")
	if not isinstance(command, str):
		return
	# Bash 以外（PowerShell、Desktop Commander、Terminal パネル）は PowerShell の書き方として読む
	segments = scan(command, payload.get("tool_name") != "Bash")
	if mode == "readonly":
		decision = decide_readonly(segments)
	else:
		decision = decide_pre(segments, Path(payload.get("cwd") or PROJECT_DIR))
	if decision is None:
		return
	# 文字コードの違いを避けるため、ASCII だけの JSON で返す
	print(
		json.dumps(
			{
				"hookSpecificOutput": {
					"hookEventName": "PreToolUse",
					"permissionDecision": decision[0],
					"permissionDecisionReason": decision[1],
				}
			}
		)
	)


if __name__ == "__main__":
	main()
