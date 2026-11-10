# Java テンプレート

ファイル名がIDEのテンプレート名になる。IntelliJ IDEAへの同期は[IDE_SETUP.md](../../docs/IDE_SETUP.md#テンプレートと共通設定の同期)を参照する。

| ファイル       | IDEのテンプレート名       | 用途                                               |
|----------------|---------------------------|----------------------------------------------------|
| Class.java     | Class（標準のJavaクラス） | Java 24・AtCoderの解答                             |
| Class17.java   | Class17                   | Java 17以降の解答                                  |
| AOJ.java       | AOJ                       | Java 11・標準Scanner/PrintWriter                   |
| ICPC.java      | ICPC                      | Java 24・libのFastScanner/FastPrinterを使う        |
| Judge.java     | Judge                     | ライブラリの検証用。オンラインジャッジの問題を解く |
| Example.java   | Example                   | ライブラリの利用例。最小のmainクラス               |
| Test.java      | Test                      | ライブラリの単体テスト（JUnit Jupiter）            |
| Benchmark.java | Benchmark                 | ライブラリのマイクロベンチマーク（JMH）            |

Classは標準の「Java Class」テンプレートを上書きするため、`File → New → Java Class` でこの雛形が使われる。

## ライブラリのテスト用

library/javaの `test/` の用途に合わせて4種類を用意する。

| テンプレート | 置き場所            | クラス名の例      | 実行方法                       |
|--------------|---------------------|-------------------|--------------------------------|
| Example      | `test/example/`     | `Example`         | mainを個別に実行する           |
| Judge        | `test/judge/`       | `Judge`、`Judge2` | 問題の入力を標準入力から与える |
| Test         | `test/unit/`        | `IntBITTest`      | JUnitで実行する                |
| Benchmark    | JMHのソースフォルダ | `IntBITBenchmark` | JMHで実行する                  |

Testはlibrary/javaのGradleが依存に持つJUnit Jupiterを使う。BenchmarkはJMHを導入した後に使う。導入前に作成するとimportが解決しない。

- Test: クラス名を `Test` にしない。`@Test` が自分自身のクラスを指してコンパイルエラーになる。次の3つの雛形を含み、どれも未実装のうちは `fail` で失敗する。不要なものは削除する。
    - 境界値: `@ParameterizedTest` と `@CsvSource` で1行1ケースの表にする。
    - 愚直解との照合: `@RepeatedTest` の回数をシードにし、配列などの単純な実装と同じ操作列を適用して比べる。失敗メッセージにシードと操作番号を含める。
    - TLE検出: `@Tag("slow")` と `@Timeout` を付け、最大制約の入力で実行する。`./gradlew test` は実行せず、`./gradlew slowTest` だけが実行する。
- Benchmark: JMHはクラスを継承して計測コードを生成するため、`public` かつ `final` にしない。入力は `@Setup` でシードを固定して作り、結果は `Blackhole` で消費する。

## テンプレートの書き方

すべて `${NAME}` や `#if` を含むVelocityテンプレートとして保存する。そのままjavacへ渡さず、IDEで展開して使う。
テンプレート構文はコメントアウトせずに記述する。同期スクリプトはJavaのテンプレートをそのままコピーする。
`${NAME}` の置換や `#if` の条件分岐を処理するのは、新規ファイルを作成するときのIDEである。

先頭に `## @formatter:off` を置く。`##` はVelocityの行コメントであり、生成したJavaファイルには残らない。
元ファイルの整形停止は `.editorconfig` の `[templates/**]` に指定した `ij_formatter_enabled = false` で行う。
この設定はテンプレートだけに適用され、生成した解答ファイルでは通常の整形を使える。
構文エラーや検査を抑制するものではなく、通常のJavaとして開くとテンプレート構文はエラー表示される。

importは「標準ライブラリなど → lib → static import」の順に並べる。自動整形の並び順と同じにする。

`#if (${NAME} == "E")` の分岐を維持し、クラス名が `E` ならラムダ式、それ以外なら `${NAME}::stringify` を生成する。
例えば `Main` では `Main::stringify` になる。
