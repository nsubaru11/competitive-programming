# Competitive Programming

競技プログラミングの解答、ライブラリ、補助ツールをまとめたリポジトリです。

- **プロフィール**: https://atcoder.jp/users/nsubaru
- **解答**: コンテスト単位で `src/<言語>/` に配置
- **テンプレート**: `templates/`の言語別テンプレートと提出用ヘッダー

## リポジトリ構成

```text
competitive-programming/
├── atcoder/             # AtCoder (abc/351-400/abc377/src/java/A.java など)
├── aoj/                 # Aizu Online Judge
├── codeforces/          # Codeforces
├── paiza/               # paiza レベルアップ問題集 (level-up/binary-search/src/java/normal.java など)
├── icpc/                # ICPC・JAG (問題データはローカルのみ)
├── library/java/        # Java ライブラリ (submodule)
├── library/cpp/         # C++ ライブラリ (submodule)
├── templates/           # 言語別テンプレート (java/, cpp/, python/)、提出用ヘッダー (header/)
├── docs/                # ドキュメント
└── tools/               # 補助ツール (submodule)
    ├── shared/          # local-runner / userscripts 共通 TypeScript
    ├── userscripts/     # Tampermonkey 用 UserScript (TypeScript → .user.js)
    └── local-runner/    # ローカル実行・CLI (test / submit)
```

## Python 環境

uv でルートの `.venv` に一元管理します。バージョンは AtCoder の CPython 3.13.7 に合わせています。

```powershell
uv sync
uv run python templates/python/TemplateCode.py
```

## 秘密情報

セッションやパスワードは、サイトごとに `.atcoder/`、`.aoj/`、`.codeforces/`、`.icpc/` へ置きます。
これらは `.gitignore` の対象で、リポジトリには含まれません。ソースコードに直接書かないでください。

## 補助ツール (tools)

| パッケージ   | 説明                   | README                                                         |
|--------------|------------------------|----------------------------------------------------------------|
| tools        | Bun ワークスペースの親 | [tools/README.md](./tools/README.md)                           |
| shared       | 共通ライブラリ         | [tools/shared/README.md](./tools/shared/README.md)             |
| userscripts  | ブラウザ拡張スクリプト | [tools/userscripts/README.md](./tools/userscripts/README.md)   |
| local-runner | CLI・Local Runner      | [tools/local-runner/README.md](./tools/local-runner/README.md) |

### クイックスタート

サブモジュールを初期化します。

```powershell
git submodule update --init --recursive
```

依存パッケージを入れて、ビルドと型チェックを行います。

```powershell
cd tools
bun install
bun run --cwd userscripts build
bun run --cwd local-runner typecheck
```

Local Runner に、Java のライブラリと提出用ヘッダーの場所を設定します。リポジトリのルートで実行します。設定はクローンごとに必要です。

```powershell
tools/local-runner/bin/setlib.cmd library/java
tools/local-runner/bin/setheader.cmd java templates/header/java.txt
```

解答では`import lib.ds.unionfind.UnionFind;`のように記述します。`run` / `test` / `tomain` / `submit`時に推移的依存を含む単一の`Main.java`へ自動展開されます。詳細は[LIBRARY_USAGE.md](./docs/LIBRARY_USAGE.md)を参照してください。

ローカルランナー起動 (Windows):

```powershell
tools/local-runner/bin/serve.cmd
```

## コミットメッセージの例

```text
Solved: ABC000 [A ~ G]
Resolved: ABC000 [A ~ G]
feat: ランナーに新しいコマンドを追加
fix: サンプル取得の失敗を修正
refactor: コンテストフォルダを整理
chore: 無視設定を更新
docs: README を更新
```

## 補足

- UserScript は [Greasy Fork](https://greasyfork.org/scripts/603262-cp-toolkit) からインストールできます。
- UserScript は `tools/userscripts` を編集し、`bun run build` で `dist` を更新します。

## ライセンス

本リポジトリの内容は、個別に別条件が明記された第三者の素材を除き、[0BSD（Zero-Clause BSD）](./LICENSE)で提供します。サブモジュールのライセンスは各リポジトリを参照してください。
