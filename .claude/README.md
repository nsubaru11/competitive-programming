# .claude

Claude Code 用の共有設定と、AIが読む作業手順を置く。手順は Markdown なので、Claude Code 以外のAIも読める。リポジトリ全体の決まりは [AGENTS.md](../AGENTS.md) にある。

| ファイル                                             | 内容                                                                                                              |
|------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| [settings.json](settings.json)                       | 秘密情報のフォルダと `.env` の読み書きを拒否する。シェルで実行する git コマンドを `hooks/git_guard.py` で検査する |
| [hooks/git_guard.py](hooks/git_guard.py)             | `git add -A`・`git commit -a`・force push を拒否し、push は毎回確認する（Python が必要）                          |
| [skills/verify](skills/verify/SKILL.md)              | 変更した場所ごとの検証コマンド                                                                                    |
| [skills/commit](skills/commit/SKILL.md)              | 本体と submodule のコミットの手順                                                                                 |
| [skills/push](skills/push/SKILL.md)                  | submodule、本体の順に push する手順（`/push` で呼ぶ）                                                             |
| [skills/solved](skills/solved/SKILL.md)              | 解答を `Solved: ABC000 [A ~ G]` の形式でコミットする手順（`/solved` で呼ぶ）                                      |
| [agents/scope-reviewer.md](agents/scope-reviewer.md) | 依頼文と差分を照合し、依頼にない変更を見つける読み取り専用のレビュー担当                                          |

## 個人用のファイル

次は Git の管理外にしている。

- `local/`：作者個人のメモや引き継ぎ文書など、共有しないものを置く
- `settings.local.json`：個人用の設定
- `scheduled_tasks.lock`、`worktrees/`：Claude Code が作る実行時のファイル

## 書き方

skill と subagent の冒頭（YAML）の `description` と `argument-hint` は、二重引用符で囲む。`Solved: ABC000` のように値の中に `: ` があると、囲まない場合は YAML として読めない。
