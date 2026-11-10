# AGENTS.md

## リポジトリの概要

- 競技プログラミングの解答・テンプレート・補助ツールのソースを管理する。継続的な開発は `library/`（Java・C++のsubmodule）と `tools/`（submodule）で行い、解答はコンテスト中に書く。
- 解答はコンテストごとの `src/<言語>/` に置く。JavaはコンテストごとのIDEAモジュール（`*.iml`）として扱う。
- `library/java` と `library/cpp` には個別の `AGENTS.md` がある。それぞれの作業ではそちらを優先する。

## 作業の進め方

- 変更前に問題点・変更対象・変更案を説明する。承認された範囲の実装は、その説明に沿って進める。
- 変更後は、変更したファイルごとの内容と確認結果を説明する。確認していない項目はそう伝える。
- 設定ファイルで扱える変更は直接行い、画面操作（Computer Use）はできるだけ使わない。画面での確認が必要な場合は、具体的な操作手順を示す。
- 時間のかかるビルドや全件の実行は依頼されたときだけ行う。

## 仕様の置き場所

| 内容                                                             | 文書                                                                                                                                                               |
|------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| IDEごとのプロジェクト設定（開くフォルダ、Gitの管理対象）         | [docs/IDE_SETUP.md](docs/IDE_SETUP.md)                                                                                                                             |
| C++のCMake構成とCLionの設定                                      | [docs/CMAKE_SETUP.md](docs/CMAKE_SETUP.md)                                                                                                                         |
| C++の提出先ごとの言語規格                                        | [docs/CPP_JUDGE_ENVIRONMENTS.md](docs/CPP_JUDGE_ENVIRONMENTS.md)                                                                                                   |
| Javaライブラリの使い方                                           | [docs/LIBRARY_USAGE.md](docs/LIBRARY_USAGE.md)                                                                                                                     |
| テンプレートの用途と書き方                                       | [templates/java/README.md](templates/java/README.md)、[templates/python/README.md](templates/python/README.md)、[templates/cpp/README.md](templates/cpp/README.md) |
| テンプレートと共通設定のIDEへの同期（scripts/sync-jetbrains.py） | [docs/IDE_SETUP.md](docs/IDE_SETUP.md#テンプレートと共通設定の同期)                                                                                                |

## 守ること

- JetBrainsのIDEでリポジトリのルートを開かない。各IDEは `.ide/<IDE名>` を開く。ルートに `.idea` を作らない。
- 実行環境はAtCoder基準のものを全サイトで共通利用する。サイトごとのコンパイラ追加や、速度の完全な再現は目指さない。
- C++の自作ライブラリは解答へコピペする。解答用のCMakeから `library/cpp` を読み込まず、検索パスやリンクも追加しない。ACL・Boostは導入しない。
- Pythonの静的解析用環境（uvで管理するルートの `.venv`）と、WSLのCPython・PyPy・Codonによる実行環境を区別する。確認したときは、どの環境で確認したかを明示する。
- パッケージの導入・更新は合意してから行う。依存リストを作る場合はCPython用とPyPy用を分ける。
- インデントは原則タブ。書式は `.editorconfig` と `.clang-format` に従う。`templates/` 内は整形しない。
- ルートの `README.md` は概要・フォルダ構成・使用バージョンだけを短く保つ。手順や仕様は `docs/` に置く。
- セッションやパスワードはサイトごとの `.atcoder/`・`.aoj/`・`.codeforces/`・`.icpc/` に置き、ソースに書かない。これらと `.env` はGitの管理外。
- paizaのスキルチェック（`paiza/skill-check/`・`paiza/other/`）と、ICPCの問題データ・問題PDFはGitに入れない。

## Git

- コミットメッセージは日本語で `feat:`・`fix:`・`refactor:`・`chore:`・`docs:` を付ける。解答は `Solved: ABC000 [A ~ G]` の形式。submoduleの参照だけを更新するコミットは `chore: library/java を更新` の形式で、複数ある場合は `、` で繋ぐ（例: `chore: library/java、tools を更新`）。
- pushは依頼されたときだけ行う。submoduleを先に、本体を後にpushする。force pushはせず、push済みの履歴は書き換えない。
- 公開リポジトリなので、コミット前に秘密情報が含まれていないか確認する。
