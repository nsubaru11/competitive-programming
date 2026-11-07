# Java テンプレート

| 正本                | IDEのテンプレート名       | 対象                                 |
|---------------------|---------------------------|--------------------------------------|
| TemplateCode.java   | Class（標準のJavaクラス） | Java 24・AtCoder                     |
| TemplateCode17.java | Class17                   | Java 17以降                          |
| AOJ.java            | AOJ                       | Java 11・標準Scanner/PrintWriter     |
| ICPC.java           | ICPC                      | Java 11・内蔵FastScanner/FastPrinter |
| Judge.java          | Judge                     | Java 17以降・ライブラリの検証用      |
| Test.java           | Test                      | 最小のmainクラス                     |

拡張子は言語ごとの `.java`・`.py`・`.cpp` を使う。
Javaの6件はすべて `${NAME}` や `#if` を含むVelocityテンプレートとして保存し、そのままjavacへ渡さず、IDEで展開して使う。
テンプレート構文はコメントアウトせずに記述し、同期スクリプトもJavaのコメント解除・クラス名置換を行わず、そのままコピーする。
`${NAME}` の置換や `#if` の条件分岐を処理するのは、新規ファイルを作成するときのIDEである。

6件とも先頭に `## @formatter:off` を置く。`##` はVelocityの行コメントであり、生成したJavaファイルには残らない。
元ファイルの整形停止は `.editorconfig` の `[templates/**/*.{java,py,cpp}]` に指定した `ij_formatter_enabled = false` で行う。
この設定はJava・Python・C++のテンプレートだけに適用され、生成した解答ファイルでは通常の整形を使える。
構文エラーや検査を抑制するものではなく、通常のJavaとして開くとテンプレート構文はエラー表示される。

`#if (${NAME} == "E")` の分岐を維持し、クラス名が `E` ならラムダ式、それ以外なら `${NAME}::stringify` を生成する。
例えば `Main` では `Main::stringify` になる。

C++の `#include` などはVelocityのディレクティブと衝突しないよう、同期時にリテラルとして保護する。

リポジトリのルートで次を実行して反映する。

```powershell
.venv\Scripts\python.exe scripts\sync-jetbrains-templates.py
```

同期対象はスクリプトの `TEMPLATES` に登録した11件（Java 6件・Python 4件・C++ 1件）。
`templates/header/*.txt` とREADMEは対象外。新しいテンプレートは `TEMPLATES` に追加する必要がある。

IntelliJ IDEAのプロジェクト設定 `.ide/idea/.idea/fileTemplates` へ11件を同期し、IntelliJ IDEA・PyCharm・CLionでは製品ごとに見つかった最新バージョンの設定へ同期する。
各IDEでは担当言語のテンプレートを追加し、既に存在する管理対象のテンプレートも更新する。
リポジトリからIDEへの一方向の同期で、IDEでの編集をリポジトリへ取り込む機能や常時監視はない。
キーマップ・External Tools・SDKなどのIDE設定は同期しない。プロジェクトのコードスタイルなどは[IDE_SETUP.md](../../docs/IDE_SETUP.md)の同期スクリプトで扱う。
管理対象以外のテンプレートは変更しない。変更前の内容は `out/jetbrains-template-backups` に退避する。
反映状況だけ確認する場合は `--check`、プロジェクトだけへ反映する場合は `--project-only` を指定する。
IDE側で以前の内容が表示される場合は、IDEを開き直してから確認する。
