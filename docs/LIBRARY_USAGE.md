# 競プロライブラリの利用方法

## 初回セットアップ

リポジトリのルートで実行する。

```powershell
git submodule update --init --recursive
tools/local-runner/bin/setlib.cmd library/java
tools/local-runner/bin/setheader.cmd java templates/header/java.txt
```

## 解答を書く

JDK 24のモジュール（AtCoder・ICPC）では、通常のJavaと同じように、`import lib.ds.unionfind.UnionFind;`や`import lib.io.*;`の形でimportする。実際の解答例は[ABC040 D](../atcoder/abc/001-050/abc040/src/java/D.java)を参照する。

言語レベルが24より低いモジュール（AOJ=11、paiza=18、Codeforces=21）はlibraryに依存できないため、importせずにライブラリのコードを解答へ貼り付ける。クラスは解答クラスの中の`private static`の入れ子クラスにする。staticメソッドだけのクラスは、使うメソッドだけを解答クラスへコピーする。Java 11で使えない記法は書き換える。CodeforcesではJava 17互換の`lib.io.compat17`を貼る。AOJ・paizaの入出力は、libraryを使わず標準のScannerとPrintWriterを使う。

Local Runner経由の実行では、importを書かなくても、単純名がライブラリの中で一意ならimportが補われる。ただし、コードの可読性とIDEの補完のため、ローカルの解答では通常どおり明示的にimportする。詳細は[Local Runner](../tools/local-runner/README.md#ライブラリを使う)を参照する。

雛形は[templates/java/README.md](../templates/java/README.md)の表から選ぶ。IDEへの登録は[IDE_SETUP.md](IDE_SETUP.md#テンプレートと共通設定の同期)の同期で行う。`lib.*`に対して補完、定義ジャンプ、Find Usages、Renameが利用できる。

`src/patterns`は読む・写経する資料であり、importや自動バンドルの対象ではない。

## ローカル実行と提出

コマンドとオプションは[Local Runner](../tools/local-runner/README.md#コマンド)を参照する。`run`、`localtest`、`test`、`crosscheck`、`tomain`、`toclip`、`submit`はすべて同じバンドル処理を通るため、ローカル実行と提出コードが一致する。展開したクラスは`Bundled library classes: ...`のログで確かめる。提出コードの先頭に付けるヘッダーの設定は[提出用ヘッダー](../tools/local-runner/README.md#提出用ヘッダー)を参照する。

## libraryが見つからない場合

ライブラリの場所は自動では探さない。`setlib`で設定し、`status`で読み込まれているかを確認する。未設定でもLocal Runnerは起動するが、`lib.*`を使うコードは設定を促すエラーになる。環境変数`ATCODER_LIB_SRC`の扱いは[環境変数](../tools/local-runner/README.md#環境変数)を参照する。

## バンドラの制約

- `import lib.some.Class;`または`import lib.some.*;`を使う
- `import static lib...`は使わない
- 本文中で`lib.ds.unionfind.UnionFind`のような完全修飾参照を使わない
- 解答と依存クラスでトップレベル型の単純名を重複させない

ワイルドカードimportを使った場合も、javacが実際にシンボル解決したトップレベル型だけがインラインされる。バンドル後の元importは削除されず、`// import lib.some.*;`のようにコメントとして残る。Main化、コンストラクタ・自己参照の変更、DEBUG切替、package/import範囲もJava Compiler APIのASTとシンボル情報に基づく。

エラー時には未解決importのまま提出せず、Local Runnerが停止する。

## 手貼りへのフォールバック

バンドラに問題がある場合は、次の順で切り戻せる。

1. `toclip`でコピーした、最後に成功した提出コードを使う
2. `library/java/src/lib/...`からログに表示されたクラスを手動で展開する

Class・Class17・ICPC・Judgeのテンプレートは`lib.*`をimportするため、バンドラなしでは提出できない。
