import os
from pathlib import Path
import sys

SITES = ("atcoder", "aoj", "codeforces", "icpc", "paiza")

def cpp_directories(root):
	result = []

	def visit(directory, depth):
		if not directory.is_dir():
			return
		with os.scandir(directory) as entries:
			for entry in entries:
				if not entry.is_dir(follow_symlinks=False) or entry.name.startswith("."):
					continue
				path = Path(entry.path)
				if entry.name == "src":
					cpp = path / "cpp"
					if cpp.is_dir():
						result.append(cpp.relative_to(root).as_posix())
				elif depth < 3 and entry.name not in {"out", "build", "target", "node_modules"}:
					if not entry.name.startswith("cmake-build-"):
						visit(path, depth + 1)

	for site in SITES:
		visit(root / site, 0)
	return sorted(result)

if __name__ == "__main__":
	root = Path(sys.argv[1]).resolve()
	print("\n".join(cpp_directories(root)))
