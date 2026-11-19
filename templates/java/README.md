# Javaテンプレート

ファイル名がIDEのテンプレート名になります。IDEAへの同期は[IDE_SETUP.md](../../docs/IDE_SETUP.md#テンプレートと共通設定の同期)を参照してください。

| ファイル       | IDEのテンプレート名       | 用途                                                     |
|----------------|---------------------------|----------------------------------------------------------|
| Class.java     | Class（標準のJavaクラス） | Java 24・AtCoderの解答                                   |
| Class17.java   | Class17                   | Java 17以降の解答                                        |
| AOJ.java       | AOJ                       | Java 11・標準Scanner/PrintWriter                         |
| ICPC.java      | ICPC                      | Java 24・libのFastScanner/FastPrinterを使います。        |
| Judge.java     | Judge                     | ライブラリの検証用。オンラインジャッジの問題を解きます。 |
| Example.java   | Example                   | ライブラリの利用例。最小のmainクラス                     |
| Test.java      | Test                      | ライブラリの単体テスト（JUnit Jupiter）                  |
| Benchmark.java | Benchmark                 | ライブラリのマイクロベンチマーク（JMH）                  |

Classは標準の「Java Class」テンプレートを上書きするため、`File → New → Java Class` でこの雛形が使われます。

Class・Class17・ICPC・Judgeは `lib.*` をimportします。libraryに依存できないモジュール（AOJ・paiza・Codeforces）では、importせず使う部分を解答へ貼り付けます。詳細は [LIBRARY_USAGE.md](../../docs/LIBRARY_USAGE.md#サイトごとの使い分け) を参照してください。

## ライブラリのテスト用

library/javaの `test/` の用途に合わせて4種類を用意します。

| テンプレート | 置き場所            | クラス名の例      | 実行方法                           |
|--------------|---------------------|-------------------|------------------------------------|
| Example      | `test/example/`     | `Example`         | mainを個別に実行します。           |
| Judge        | `test/judge/`       | `Judge`、`Judge2` | 問題の入力を標準入力から与えます。 |
| Test         | `test/unit/`        | `IntBITTest`      | JUnitで実行します。                |
| Benchmark    | JMHのソースフォルダ | `IntBITBenchmark` | JMHで実行します。                  |

Testはlibrary/javaのGradleが依存に持つJUnit Jupiterを使います。BenchmarkはJMHを導入した後に使います。導入前に作成するとimportが解決しません。

- Test：クラス名を `Test` にしません。`@Test` が自分自身のクラスを指してコンパイルエラーになります。次の3つの雛形を含み、どれも未実装のうちは `fail` で失敗します。不要なものは削除します。
    - 境界値：`@ParameterizedTest` と `@CsvSource` で1行1ケースの表にします。
    - 愚直解との照合：`@RepeatedTest` の回数をシードにし、配列などの単純な実装と同じ操作列を適用して比べます。失敗メッセージにシードと操作番号を含めます。
    - TLE検出：`@Tag("slow")` と `@Timeout` を付け、最大制約の入力で実行します。`./gradlew test` は実行せず、`./gradlew slowTest` だけが実行します。
- Benchmark：JMHはクラスを継承して計測コードを生成するため、`public` かつ `final` にしません。入力は `@Setup` でシードを固定して作り、結果は `Blackhole` で消費します。

## テンプレートの書き方

すべて `${NAME}` や `#if` を含むVelocityテンプレートとして保存します。そのままjavacへ渡さず、IDEで展開して使います。
テンプレート構文はコメントアウトせずに記述します。同期スクリプトはJavaのテンプレートをそのままコピーします。
`${NAME}` の置換や `#if` の条件分岐を処理するのは、新規ファイルを作成するときのIDEです。

先頭に `## @formatter:off` を置きます。`##` はVelocityの行コメントであり、生成したJavaファイルには残りません。
元ファイルの整形停止は `.editorconfig` の `[templates/**]` に指定した `ij_formatter_enabled = false` で行います。
この設定はテンプレートだけに適用され、生成した解答ファイルでは通常の整形を使えます。
構文エラーや検査を抑制するものではなく、通常のJavaとして開くとテンプレート構文はエラー表示されます。

importは「標準ライブラリなど → lib → static import」の順に並べます。自動整形の並び順と同じにします。

`#if (${NAME} == "E")` の分岐を維持し、クラス名が `E` ならラムダ式、それ以外なら `${NAME}::stringify` を生成します。
例えば `Main` では `Main::stringify` になります。
