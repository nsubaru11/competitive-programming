# C++テンプレート

ファイル名がCLionのテンプレート名になる。同期は[IDE_SETUP.md](../../docs/IDE_SETUP.md#テンプレートと共通設定の同期)を参照する。

[Competitive.cpp](Competitive.cpp) はAtCoderではGNU C++23 / GCC 15.2.0を使う単一ファイルのテンプレートである。
テンプレート自身はC++20で使える構文にし、paizaやICPCのC++20設定でも利用できる。
ライブラリや追加のソースファイルを必要としない。

配置は「Runtime Setup & Constants → 前方宣言 → solve → Utilities → Entry Point & Debug」とする。
C++では関数を呼び出す前に宣言が必要なため、宣言だけを上部に置き、問題の処理を `solve()` に書く。

## 入出力・定数

- `setup_io()` は `main()` の最初に呼び、Cとの入出力同期と、入力前の自動flushを無効にする。`cin/cout` と `scanf/printf` は混在させない。
- 通常の改行は `cout << '\n';` とし、毎回flushする `endl` を多用しない。対話問題では必要なタイミングで `cout << flush;` を使う。
- `cout` の浮動小数点出力は `long double` の往復変換に十分な有効桁数に設定する。整数の出力は変わらない。小数点以下の桁数を固定したい問題では別途 `fixed << setprecision(...)` を指定する。
- `MOD`、方向配列などは `constexpr` とする。`DI/DJ` の先頭4要素が上下左右、全8要素が斜めを含む8近傍である。
- `ll` は `long long`、`ld` は `long double` の別名である。`INF` は整数の番兵値なので問題の値域に合わせる。
- `<bits/stdc++.h>` はGCCのヘッダーである。別の標準ライブラリ・コンパイラへ移す場合は必要な標準ヘッダーへ置き換える。

## 補助関数

```cpp
ll distance = INF;
if (chmin(distance, 10LL)) {
	// 更新時の処理
}
chmax(distance, 20LL);
debug(distance, vector{1, 2}, pair{3, 4});
```

`chmin/chmax` は参照先を更新し、更新したかを返す。配列の要素には `chmin(a[i], value)` と書く。
代入先と値は、比較・代入して意図した値になる型を組み合わせる。整数型の代入先へ小数を渡す用途には使わない。
`min/max`、`swap`、絶対値、範囲操作などは標準ライブラリをそのまま使う。

`debug(a, b)` はカンマ区切り、`debugln(a, b)` は先頭に空行を置いた改行区切りで、標準エラー出力へ表示する。
標準エラー出力へ直接書き込み、数値・文字列・`vector`・`pair`・`tuple`・多次元配列などを表示できる。
C++23の `std::print` や範囲formatterには依存しない。
独自の型はストリーム出力に対応させる。行列を1行ずつ出す場合は `for (const auto& row : grid) debug(row);` と書く。
`LOCAL` 定義時だけ `DEBUG = true` になる。WSL DebugとWSL Runの両方で定義する。通常の提出先では定義しない。
`DEBUG = false` のとき、デバッグ関数の出力処理はコンパイル時に除外される。
呼び出し引数の計算も止めたい場合は、呼び出し側を `if constexpr (DEBUG)` で囲む。

## コンパイル

```bash
"$HOME/.local/share/competitive-programming/runtimes/gcc-15.2.0/bin/g++" \
  -std=gnu++23 -O2 -Wall -Wextra Competitive.cpp -o main
./main
```

デバッグ用と通常のローカル実行用のオプションは [CMAKE_SETUP.md](../../docs/CMAKE_SETUP.md#最初に一度確認するclion設定) の表に合わせ、単独でコンパイルするときも `-DLOCAL` を付ける。
提出先ごとのコンパイル条件は [CPP_JUDGE_ENVIRONMENTS.md](../../docs/CPP_JUDGE_ENVIRONMENTS.md) を参照。
最適化レベルはビルド設定で指定し、テンプレートに `#pragma GCC optimize` やCPU命令を固定する設定は追加しない。

- [GCC：C/C++入出力の同期](https://gcc.gnu.org/onlinedocs/libstdc++/manual/io_and_c.html)
- [GCC 15.2：最適化オプション](https://gcc.gnu.org/onlinedocs/gcc-15.2.0/gcc/Optimize-Options.html)
