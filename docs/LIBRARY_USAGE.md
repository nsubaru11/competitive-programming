# このリポジトリでのJavaライブラリの使い方

このリポジトリ（competitive-programming）での、ライブラリとLocal Runnerの設定と、解答での使い分けの決まりをまとめる。submoduleの構成やフォルダの名前は、このリポジトリだけのものである。

ライブラリとLocal Runnerそのものの使い方は、それぞれの文書を参照する。

| 内容                                           | 文書                                                                       |
|------------------------------------------------|----------------------------------------------------------------------------|
| ライブラリのクラスと、入手して使う方法         | [Javaライブラリ](../library/java/README.md#使い方)                         |
| Local Runnerの導入とコマンド                   | [Local Runner](../tools/local-runner/README.md)                            |
| importの書き方、展開の規則、展開できない書き方 | [Javaのライブラリを使う](../tools/local-runner/README.md#ライブラリを使う) |
| 設定ファイルの項目                             | [Local Runnerの設定](../tools/local-runner/docs/SETTINGS.md)               |

## 初回セットアップ

ライブラリとLocal Runnerはsubmoduleとして、`library/java`と`tools`に置いている。リポジトリのルートで実行する。

```powershell
git submodule update --init --recursive
tools/local-runner/bin/setlib.cmd library/java/src/lib
tools/local-runner/bin/setheader.cmd java templates/header/java.txt
```

- `setlib`には`library/java/src/lib`を指定する。`src/patterns`は読む・写経する資料なので、展開の対象に含めない
- 提出コードで入出力のクラスを末尾に並べるため、`tools/local-runner.settings.json`の`languages.java.libraryLast`に`lib.io`を書く
- 読み込まれているかは`status`で確かめる
- 提出コードの先頭に付けるヘッダーは`templates/header/java.txt`である。設定の仕組みは[提出用ヘッダー](../tools/local-runner/README.md#提出用ヘッダー)を参照する

## サイトごとの使い分け

JDK 24のモジュール（AtCoder・ICPC）では、通常のJavaと同じように、`import lib.ds.unionfind.UnionFind;`や`import lib.io.*;`の形でimportする。実際の解答例は[ABC040 D](../atcoder/abc/001-050/abc040/src/java/D.java)を参照する。

言語レベルが24より低いモジュール（AOJ=11、paiza=18、Codeforces=21）はlibraryに依存できないため、importせずにライブラリのコードを解答へ貼り付ける。クラスは解答クラスの中の`private static`の入れ子クラスにする。staticメソッドだけのクラスは、使うメソッドだけを解答クラスへコピーする。Java 11で使えない記法は書き換える。CodeforcesではJava 17互換の`lib.io.compat17`を貼る。AOJ・paizaの入出力は、libraryを使わず標準のScannerとPrintWriterを使う。

Local Runnerはimportを補えるが、コードの可読性とIDEの補完のため、解答では明示的にimportする。

雛形は[templates/java/README.md](../templates/java/README.md)の表から選ぶ。IDEへの登録は[IDE_SETUP.md](IDE_SETUP.md#テンプレートと共通設定の同期)の同期で行う。`lib.*`に対して補完、定義ジャンプ、Find Usages、Renameが利用できる。

## 手貼りへのフォールバック

Local Runnerの展開に問題がある場合は、次の順で切り戻せる。

1. `toclip`でコピーした、最後に成功した提出コードを使う
2. `library/java/src/lib/...`から、ログの`Bundled library classes: ...`に表示されたクラスを手動で展開する

Class・Class17・ICPC・Judgeのテンプレートは`lib.*`をimportするため、Local Runnerなしでは提出できない。
