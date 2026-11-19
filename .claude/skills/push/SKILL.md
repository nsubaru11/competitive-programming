---
name: push
description: "submodule、本体の順に push する。submodule の参照を更新するコミットも作る。"
argument-hint: "[リポジトリ（省略するとすべて）]"
disable-model-invocation: true
---

# push の手順

対象：$ARGUMENTS（指定がなければ、本体と submodule のすべて）

決まりは [AGENTS.md](../../../AGENTS.md) の「Git」にある。force push はしない。push 済みの履歴は書き換えない。push のたびに hook が確認を出す。

## 1. 状態を調べる

`tools`、`library/java`、`library/cpp`、本体の順に、次を調べる。

- `git status -sb`：未コミットの変更と、リモートとの差
- `git log @{upstream}..HEAD --oneline`：まだ push していないコミット

未コミットの変更は報告するだけにし、ここではコミットしない。ただし、手順4の参照の更新は例外とする。

## 2. push の前に確かめる

まだ push していないコミットが次の条件をすべて満たすか確認する。満たさない条件があれば、push の前に報告して止める。

- 秘密情報：`git diff @{upstream}..HEAD` に、セッション、パスワード、トークン、メールアドレスがない。
- 公開できないファイル：`paiza/skill-check/`、`paiza/other/`、ICPC の問題データと問題 PDF が含まれていない。
- 開催中のコンテスト：解答のコミットがあれば、そのコンテストが終了している。当日のコンテストは、コンテストのページで終了時刻を確かめる。
- CP Toolkit：`tools` で `userscripts/CpToolkit/dist/CpToolkit.user.js` が変わっているなら、`meta.json` の `version` が上がっていて、上げた位置が変更の種類に合っている。版の付け方は [DEVELOPMENT.md](../../../tools/userscripts/CpToolkit/DEVELOPMENT.md) の「版の付け方」に従う。

## 3. submodule を push する

まだ push していないコミットがある submodule だけを、`git -C tools push` のように push する。拒否されたら（リモートが先に進んでいるなど）、そこで止めて報告する。

## 4. 本体の参照を更新する

`git status --short` に submodule の変更（`M tools` など）があれば、その submodule だけを名指しで `git add` し、コミットする。

- メッセージは `chore: tools を更新`。複数あるときは `chore: library/java、tools を更新` のように `、` でつなぐ。
- ほかの未コミットの変更は含めない。

## 5. 本体を push する

`git push` を実行する。

## 6. 報告する

リポジトリごとに、push したコミットの範囲を伝える。push しなかったものは、その理由を伝える。
