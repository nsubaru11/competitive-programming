# 競プロライブラリの利用方法

## 初回セットアップ

リポジトリのルートで実行します。

```powershell
git submodule update --init --recursive
python scripts/generate-modules.py
```

スクリプトは次を行います。

- 解答フォルダごとのIntelliJモジュールを作成
- AtCoderのモジュールへ`library`のModule Dependencyを追加
- `.idea/modules.xml`を更新

新しいコンテストフォルダを作った後も、このスクリプトを再実行すればモジュールが追加されます。

## 解答を書く

通常のJavaと同じようにimportします。

```java
import lib.ds.UnionFind;
import lib.graph.Kruskal;
import lib.io.FastPrinter;
import lib.io.FastScanner;

public final class D {
	public static void main(final String[] args) {
		final FastScanner sc = new FastScanner();
		try (final FastPrinter out = new FastPrinter()) {
			final int n = sc.nextInt();
			final UnionFind uf = new UnionFind(n);
			out.println(uf.groupCount());
		}
	}
}
```

LocalRunner経由（`run`、`test`、`toclip`、JavaCodeSubmitter、AtCoderEasyTest）では、importを書かずに`FastScanner`や`UnionFind`を使用しても、Java Compiler APIが一意なlibrary型を自動importします。コードの可読性とIDE補完のため、ローカル解答では通常どおり明示importする運用を推奨します。

IntelliJでは`File → New → AtCoder Library Solution`から雛形を作成できます。`lib.*`に対して補完、定義ジャンプ、Find Usages、Renameが利用できます。

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
Bundled library classes: lib.graph.Kruskal, lib.ds.UnionFind
```

`run`、`localtest`、`test`、`crosscheck`、`tomain`、`toclip`、`submit`はすべて同じバンドル処理を通るため、ローカル実行と提出コードが一致します。

`toclip d`はカレントディレクトリの`D.java`を短縮指定で解決します。`toclip D.java`のような明示指定も可能です。バンドル、`Main`化、`DEBUG=false`化を行った提出コードがそのままクリップボードへ入ります。

## libraryが見つからない場合

通常はリポジトリルートの`library/java/src`が自動検出されます。別の配置を使う場合だけ環境変数を指定します。

```powershell
$env:ATCODER_LIB_SRC = "C:\path\to\competitive-programming-java-library\src"
```

## バンドラの制約

- `import lib.some.Class;`または`import lib.some.*;`を使う
- `import static lib...`は使わない
- 本文中で`lib.ds.UnionFind`のような完全修飾参照を使わない
- 解答と依存クラスでトップレベル型の単純名を重複させない

ワイルドカードimportを使った場合も、javacが実際にシンボル解決したトップレベル型だけがインラインされます。バンドル後の元importは削除されず、`// import lib.some.*;`のようにコメントとして残ります。Main化、コンストラクタ・自己参照の変更、DEBUG切替、package/import範囲もJava Compiler APIのASTとシンボル情報に基づきます。

エラー時には未解決importのまま提出せず、runnerが停止します。

## 手貼りへのフォールバック

バンドラに問題がある場合は、次の順で切り戻せます。

1. `toclip`でコピーした、最後に成功した提出コードを使用
2. `library/java/src/lib/...`からログに表示されたクラスを手動展開
3. 従来の自己完結テンプレート`templates/TemplateCode.java`またはJava 17版を使用
