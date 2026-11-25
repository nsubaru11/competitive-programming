# このリポジトリでのJavaライブラリの使い方

このリポジトリ（competitive-programming）での、ライブラリとLocal Runnerの設定と、解答での使い分けの決まりをまとめます。submoduleの構成やフォルダの名前は、このリポジトリだけのものです。

ライブラリとLocal Runnerそのものの使い方は、それぞれの文書を参照します。

| 内容                                           | 文書                                                                       |
|------------------------------------------------|----------------------------------------------------------------------------|
| ライブラリのクラスと、入手して使う方法         | [Javaライブラリ](../library/java/README.md#使い方)                         |
| Local Runnerの導入とコマンド                   | [Local Runner](../tools/local-runner/README.md)                            |
| importの書き方、展開の規則、展開できない書き方 | [Javaのライブラリを使う](../tools/local-runner/README.md#ライブラリを使う) |
| 設定ファイルの項目                             | [Local Runnerの設定](../tools/local-runner/docs/SETTINGS.md)               |

## 初回セットアップ

ライブラリとLocal Runnerはsubmoduleとして、`library/java`と`tools`に置いています。リポジトリのルートで実行します。

```powershell
git submodule update --init --recursive
tools/local-runner/bin/setlib.cmd library/java/src/lib
tools/local-runner/bin/setheader.cmd java templates/header/java.txt
```

- `setlib`には`library/java/src/lib`を指定します。`src/patterns`は読む・写経する資料なので、展開の対象に含めません。
- 提出コードで入出力のクラスを末尾に並べるため、`tools/local-runner.settings.json`の`languages.java.libraryLast`に`lib.io`を書きます。
- 読み込まれているかは`status`で確かめます。
- 提出コードの先頭に付けるヘッダーは`templates/header/java.txt`です。設定の仕組みは[提出用ヘッダー](../tools/local-runner/README.md#提出用ヘッダー)を参照してください。

## サイトごとの使い分け

JDK 24のモジュール（AtCoder・ICPC）では、通常のJavaと同じように、`import lib.ds.unionfind.UnionFind;`や`import lib.io.*;`の形でimportします。実際の解答例は[ABC040 D](../atcoder/abc/001-050/abc040/src/java/D.java)を参照してください。

言語レベルが24より低いモジュール（AOJ=11、paiza=18、Codeforces=21）はlibraryに依存できないため、importせずにライブラリのコードを解答へ貼り付けます。クラスは解答クラスの中の`private static`の入れ子クラスにします。staticメソッドだけのクラスは、使うメソッドだけを解答クラスへコピーします。Java 11で使えない記法は書き換えます。CodeforcesではJava 17互換の`lib.io.compat17`を貼ります。AOJ・paizaの入出力は、libraryを使わず標準のScannerとPrintWriterを使います。

雛形は[templates/java/README.md](../templates/java/README.md)の表から選びます。IDEへの登録は[IDE_SETUP.md](IDE_SETUP.md#テンプレートと共通設定の同期)の同期で行います。`lib.*`に対して補完、定義ジャンプ、Find Usages、Renameが利用できます。

## 手貼りへのフォールバック

Local Runnerの展開に問題がある場合は、次の順で切り戻せます。

1. `toclip`でコピーした、最後に成功した提出コードを使います。
2. 手動で展開する場合は、解答が使うライブラリのソースを `library/java/src/lib/` からコピーします。同じ解答で変換に成功したときのログがあれば、`Bundled:` に展開した型名とファイル名が表示されています。

Class・Class17・ICPC・Judge のテンプレートを使う場合は、提出前に Local Runner でライブラリを展開するか、使うコードを手動で貼り付けます。
