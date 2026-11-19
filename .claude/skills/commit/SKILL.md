---
name: commit
description: "本体と submodule（tools、library/java、library/cpp）でコミットを作る手順。変更をコミットするとき、コミットを頼まれたときに使う。コンテストの解答は /solved、push は /push を使う。"
---

# コミットの手順

コミットは [AGENTS.md](../../../AGENTS.md) の「Git」に従う。この手順では push しない。

## 1. 対象を決める

- 本体と各 submodule で `git status --short` を見る。submodule は `git -C tools status --short` のように見る。
- ユーザーがこの会話で「編集中」「変更中」と言ったファイルとフォルダは、「ワーキングツリーをコミット」と頼まれても除く。
- 自分が変更していないファイルは、頼まれたものだけを含める。含めるか迷うものは、コミットの前に尋ねる。

## 2. 差分を依頼と照合する

- 手順1で選んだ差分を読み、依頼にない変更が混ざっていないか確かめる。追跡済みファイルは `git diff HEAD -- <対象パス>` でステージ済みの変更も含めて確認し、対象の未追跡ファイルは中身を読む。
- 次のどれかに当たるなら、`scope-reviewer` に照合させる。
    - 複数のファイルにわたる変更
    - `library/` と `tools/` のソースの変更
    - 文書の変更
- コンテストの解答、submodule の参照だけの更新、1ファイルの小さな修正では省く。
- レビュー担当には、次の情報を渡す。
    - 最初の依頼と、ユーザーが途中で承認・訂正した内容の原文。「その案で」のような返答には、承認された提案の該当部分も添える。
    - 対象のリポジトリと、今回コミットするファイル。ファイルの一部だけを含める場合は、その差分を渡す。
    - 手順1で除外した作業。ファイルや差分のどの部分を除いたかが分かるようにする。
- 指摘された変更は、戻すか残すかを決める。残すものは、理由を最後の報告に書く。

## 3. 秘密情報を確かめる

- 追加する差分に、セッション、パスワード、トークン、メールアドレスがないか読んで確かめる。中身を読まずに追加しない。
- `.atcoder/`、`.aoj/`、`.codeforces/`、`.icpc/`、`.env`、`paiza/skill-check/`、`paiza/other/`、ICPC の問題データと問題 PDF は追加しない。

## 4. CP Toolkit のバージョン

`tools/userscripts/CpToolkit/dist/CpToolkit.user.js` が変わるコミットでは、[DEVELOPMENT.md](../../../tools/userscripts/CpToolkit/DEVELOPMENT.md) の「版の付け方」を読む。前回の push 以降でまだ上げていなければ、`meta.json` の `version` を上げてビルドし直す。上げない場合（動作が変わらない整形など）は、その理由をコミットメッセージに書く。

## 5. コミットする

- `git add` はファイルを名指しする。`git add -A`、`git add .`、`git commit -a` は hook が拒否する。
- 同じファイルに除外した変更がある場合は、対象の差分だけをステージし、`git diff --cached` がレビューした内容と一致することを確認する。
- submodule の変更は、submodule の中で先にコミットする。本体では、参照の更新を `chore: tools を更新` の形式で別のコミットにする。
- メッセージは日本語で書く。

| リポジトリ     | 書き方                                                                                 |
|----------------|----------------------------------------------------------------------------------------|
| 本体           | AGENTS.md の「Git」                                                                    |
| `library/java` | [COMMIT_MESSAGE_TEMPLATE.md](../../../library/java/.github/COMMIT_MESSAGE_TEMPLATE.md) |
| `tools`        | [tools/AGENTS.md](../../../tools/AGENTS.md) の「Git」                                  |
| `library/cpp`  | [library/cpp/AGENTS.md](../../../library/cpp/AGENTS.md) の「Git」                      |

## 6. 報告する

コミットしたファイル、除いたファイルとその理由、`scope-reviewer` の指摘をどう扱ったかを伝える。
