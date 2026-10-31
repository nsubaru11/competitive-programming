"""Create IntelliJ modules for every solution folder and rewrite .idea/modules.xml.

Each contest keeps its own module because A.java and friends live in the default package.
Existing .iml files are left as they are, so per-contest JDK settings survive.
Run this after adding a contest:

    python scripts/generate-modules.py
"""
import os
import re

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
TOPS = ("atcoder", "aoj", "codeforces", "icpc")
SKIP = {"out", ".idea", "node_modules"}
IML = """<?xml version="1.0" encoding="UTF-8"?>
<module type="JAVA_MODULE" version="4">
  <component name="NewModuleRootManager" inherit-compiler-output="true">
    <exclude-output />
    <content url="file://$MODULE_DIR$">
      <sourceFolder url="file://$MODULE_DIR$/src/java" isTestSource="false" />
    </content>
    <orderEntry type="inheritedJdk" />
    <orderEntry type="sourceFolder" forTests="false" />{library}
  </component>
</module>
"""
LIBRARY = '\n    <orderEntry type="module" module-name="library" />'
FIXED = ("competitive-programming.iml", "library/java/library.iml", "tools/tools.iml")


def module_name(rel):
    """abc377 under atcoder, otherwise prefixed so names stay unique."""
    parts = rel.split("/")
    return parts[-1] if parts[0] == "atcoder" else f"{parts[0]}-{parts[-1]}"


def contest_dirs():
    for top in TOPS:
        for cur, dirs, _ in os.walk(os.path.join(ROOT, top)):
            dirs[:] = sorted(d for d in dirs if d not in SKIP)
            if os.path.isdir(os.path.join(cur, "src", "java")):
                dirs[:] = []
                yield os.path.relpath(cur, ROOT).replace(os.sep, "/")


def module_entry(path, group=None):
    attr = f' group="{group}"' if group else ""
    return f'      <module fileurl="file://$PROJECT_DIR$/{path}" filepath="$PROJECT_DIR$/{path}"{attr} />'


def main():
    entries = [module_entry(p) for p in FIXED if os.path.exists(os.path.join(ROOT, p))]
    created = 0
    for rel in contest_dirs():
        name = module_name(rel)
        path = f"{rel}/{name}.iml"
        full = os.path.join(ROOT, path)
        if not os.path.exists(full):
            library = LIBRARY if rel.startswith("atcoder/") else ""
            with open(full, "w", encoding="utf8", newline="\n") as w:
                w.write(IML.format(library=library))
            created += 1
        entries.append(module_entry(path, re.sub(r"/[^/]+$", "", rel)))
    body = "\n".join(entries)
    with open(os.path.join(ROOT, ".idea", "modules.xml"), "w", encoding="utf8", newline="\n") as w:
        w.write('<?xml version="1.0" encoding="UTF-8"?>\n<project version="4">\n'
                f'  <component name="ProjectModuleManager">\n    <modules>\n{body}\n    </modules>\n  </component>\n</project>\n')
    print(f"modules: {len(entries)}, created: {created}")


if __name__ == "__main__":
    main()
