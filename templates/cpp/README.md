# C++テンプレート

ファイル名がCLionのテンプレート名になります。同期は[IDE_SETUP.md](../../docs/IDE_SETUP.md#テンプレートと共通設定の同期)を参照してください。

[Competitive.cpp](Competitive.cpp) はAtCoderではGNU C++23 / GCC 15.2.0を使う単一ファイルのテンプレートです。
テンプレート自身はC++20で使える構文にし、paizaやICPCのC++20設定でも利用できます。
ライブラリや追加のソースファイルを必要としません。

配置は「Runtime Setup & Constants → 前方宣言 → solve → Utilities → Entry Point & Debug」とします。
C++では関数を呼び出す前に宣言が必要なため、宣言だけを上部に置き、問題の処理を `solve()` に書きます。

## 入出力・定数

- `setup_io()` は `main()` の最初に呼び、Cとの入出力同期と、入力前の自動flushを無効にします。`cin/cout` と `scanf/printf` は混在させません。
- 通常の改行は `cout << '\n';` とし、毎回flushする `endl` を多用しません。対話問題では必要なタイミングで `cout << flush;` を使います。
- `cout` の浮動小数点出力は `long double` の往復変換に十分な有効桁数に設定します。整数の出力は変わりません。小数点以下の桁数を固定したい問題では別途 `fixed << setprecision(...)` を指定します。
- `MOD`、方向配列などは `constexpr` とします。`DI/DJ` の先頭4要素が上下左右、全8要素が斜めを含む8近傍です。
- `ll` は `long long`、`ld` は `long double` の別名です。`INF` は整数の番兵値なので問題の値域に合わせます。
- `<bits/stdc++.h>` はGCCのヘッダーです。別の標準ライブラリ・コンパイラへ移す場合は必要な標準ヘッダーへ置き換えます。

## 補助関数

```cpp
ll distance = INF;
if (chmin(distance, 10LL)) {
	// 更新時の処理
}
chmax(distance, 20LL);
debug(distance, vector{1, 2}, pair{3, 4});
```

`chmin/chmax` は参照先を更新し、更新したかを返します。配列の要素には `chmin(a[i], value)` と書きます。
代入先と値は、比較・代入して意図した値になる型を組み合わせます。整数型の代入先へ小数を渡す用途には使いません。
`min/max`、`swap`、絶対値、範囲操作などは標準ライブラリをそのまま使います。

`debug(a, b)` はカンマ区切り、`debugln(a, b)` は先頭に空行を置いた改行区切りで、標準エラー出力へ表示します。
標準エラー出力へ直接書き込み、数値・文字列・`vector`・`pair`・`tuple`・多次元配列などを表示できます。
C++23の `std::print` や範囲formatterには依存しません。
独自の型はストリーム出力に対応させます。行列を1行ずつ出す場合は `for (const auto& row : grid) debug(row);` と書きます。
`LOCAL` 定義時だけ `DEBUG = true` になります。WSL DebugとWSL Runの両方で定義します。通常の提出先では定義しません。
`DEBUG = false` のとき、デバッグ関数の出力処理はコンパイル時に除外されます。
呼び出し引数の計算も止めたい場合は、呼び出し側を `if constexpr (DEBUG)` で囲みます。

## コンパイル

```bash
"$HOME/.local/share/competitive-programming/runtimes/gcc-15.2.0/bin/g++" \
  -std=gnu++23 -O2 -Wall -Wextra Competitive.cpp -o main
./main
```

デバッグ用と通常のローカル実行用のオプションは [CMAKE_SETUP.md](../../docs/CMAKE_SETUP.md#最初に一度確認するclion設定) の表に合わせ、単独でコンパイルするときも `-DLOCAL` を付けます。
提出先ごとのコンパイル条件は [CPP_JUDGE_ENVIRONMENTS.md](../../docs/CPP_JUDGE_ENVIRONMENTS.md) を参照してください。
最適化レベルはビルド設定で指定し、テンプレートに `#pragma GCC optimize` やCPU命令を固定する設定は追加しません。

- [GCC：C/C++入出力の同期](https://gcc.gnu.org/onlinedocs/libstdc++/manual/io_and_c.html)
- [GCC 15.2：最適化オプション](https://gcc.gnu.org/onlinedocs/gcc-15.2.0/gcc/Optimize-Options.html)
