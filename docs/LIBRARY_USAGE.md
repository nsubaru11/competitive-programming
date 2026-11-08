# 競プロライブラリの利用方法

## 初回セットアップ

リポジトリのルートで実行します。

```powershell
git submodule update --init --recursive
tools/local-runner/bin/setlib.cmd library/java
tools/local-runner/bin/setheader.cmd java templates/header/java.txt
```

## 解答を書く

通常のJavaと同じように、`import lib.ds.unionfind.UnionFind;`や`import lib.io.*;`の形でimportします。実際の解答例は[ABC040 D](../atcoder/abc/001-050/abc040/src/java/D.java)を参照してください。

Local Runner経由（`run`、`test`、`toclip`、CP ToolkitのJavaの提出補助とEasy Test+）では、importを書かずに`FastScanner`や`UnionFind`を使用しても、Java Compiler APIが一意なlibrary型を自動importします。コードの可読性とIDE補完のため、ローカル解答では通常どおり明示importする運用を推奨します。

雛形は[Class.java](../templates/java/Class.java)です。IntelliJではFile Templateの`Class`に登録しておくと、`File → New → Java Class`から作成できます。`lib.*`に対して補完、定義ジャンプ、Find Usages、Renameが利用できます。

`src/patterns`は読む・写経する資料であり、importや自動バンドルの対象ではありません。

## ローカル実行と提出

従来と同じrunnerコマンドを使います。すべての経路が実行前に`lib.*`を単一ソースへ展開します。

```powershell
# 1回実行
run D.java

# AtCoderサンプルテスト
test d

# ブラウザへ手動で貼り付ける提出コードをコピー
toclip d

# サンプルテスト後に提出
submit d
```

バンドルが行われると、次のようなログが出ます。

```text
Bundled library classes: lib.graph.Kruskal, lib.ds.unionfind.UnionFind
```

`run`、`localtest`、`test`、`crosscheck`、`tomain`、`toclip`、`submit`はすべて同じバンドル処理を通るため、ローカル実行と提出コードが一致します。

`toclip d`はカレントディレクトリの`D.java`を短縮指定で解決します。`toclip D.java`のような明示指定も可能です。バンドル、`Main`化、`DEBUG=false`化、ヘッダーの付与を行った提出コードがそのままクリップボードへ入ります。ヘッダーは`--no-header`で省け、`setheader`で名前を付けて登録したものは`--header=<name>`で選べます。

## libraryが見つからない場合

ライブラリの場所は自動では探しません。`setlib`で設定し、`status`で読み込まれているかを確認します。

```powershell
setlib library/java
stop
serve
status
```

未設定でもLocal Runnerは起動します。このとき`lib.*`を使うコードは、設定を促すエラーになります。

環境変数`ATCODER_LIB_SRC`を設定すると、`setlib`より優先します。ライブラリを読むのはWSL上のLocal Runnerなので、Windows側の環境変数は届きません。使うときはWSLの`~/.profile`にLinux形式のパスで設定します。`setlib`の設定はWSLのサーバーにも届きます。

## バンドラの制約

- `import lib.some.Class;`または`import lib.some.*;`を使う
- `import static lib...`は使わない
- 本文中で`lib.ds.unionfind.UnionFind`のような完全修飾参照を使わない
- 解答と依存クラスでトップレベル型の単純名を重複させない

ワイルドカードimportを使った場合も、javacが実際にシンボル解決したトップレベル型だけがインラインされます。バンドル後の元importは削除されず、`// import lib.some.*;`のようにコメントとして残ります。Main化、コンストラクタ・自己参照の変更、DEBUG切替、package/import範囲もJava Compiler APIのASTとシンボル情報に基づきます。

エラー時には未解決importのまま提出せず、runnerが停止します。

## 手貼りへのフォールバック

バンドラに問題がある場合は、次の順で切り戻せます。

1. `toclip`でコピーした、最後に成功した提出コードを使用
2. `library/java/src/lib/...`からログに表示されたクラスを手動展開

`templates/java`のテンプレートはどちらも`lib.*`をimportするため、バンドラなしでは提出できません。
