# C++の互換性とローカル実行

確認日：2026-11-06

AtCoder基準のGCC 15.2.0を全サイトで共通利用します。
他サイト用コンパイラは追加しません。本番と同じ速度やOSの再現より、言語規格・条件コンパイル・使える機能の確認を重視します。

| サイト     | ローカルの言語規格        | 参考にした公式情報                                       |
|------------|---------------------------|----------------------------------------------------------|
| AtCoder    | GNU C++23                 | GCC 15.2.0 / C++23                                       |
| Codeforces | C++23                     | 公開されているGCC 14.2 / MSYS2のコマンド                 |
| AOJ        | GNU C++23                 | C++23 / GCC 11.4.1の稼働情報                             |
| paiza      | C++20                     | Clang 18.1.3 / C++20                                     |
| ICPC       | GNU C++20を既定とします。 | 2026横浜地区大会の公開値。大会・年度によって異なります。 |

言語規格を制限しても、GCC 15の標準ライブラリが古いGCCやClangと同じになるわけではありません。
これはコンパイラを共通利用する方針上の差です。
AtCoderのマクロATCODER・NOMINMAX・ONLINE_JUDGEは残し、CodeforcesもONLINE_JUDGEを定義します。
LOCALはローカルのテンプレートのdebugを使うため別途定義し、Debug/Runの両方で有効にします。

## ローカル向けの設定

DebugとRunのオプションは[CMAKE_SETUP.md](CMAKE_SETUP.md#最初に一度確認するclion設定)の表のとおりです。
LTO、本番OS用のスタック指定、静的リンクは自動追加しません。
AtCoderのconstexpr上限、modules、ゼロ初期化、pthread、OpenMP、stdc++expの主要設定は維持します。
GCCに問い合わせた標準ライブラリの場所を実行時検索パスに設定し、古いUbuntu標準libstdc++との混在を避けます。
外部ライブラリの全種類・全バージョンをインストールする機能ではありません。

自作C++ライブラリは解答へコピペし、単一ファイルで実行・提出します。CMakeの扱いは[CMAKE_SETUP.md](CMAKE_SETUP.md#解答の実行)を参照してください。

## 公式情報

- [AtCoder：言語とコンパイルコマンド](https://img.atcoder.jp/file/language-update/2025-10/language-list.html)
- [AtCoder：LTO削除の更新](https://atcoder.jp/posts/language_update20260616_jp?lang=ja)
- [Codeforces：公開コンパイルコマンド](https://codeforces.com/blog/entry/121114)
- [AOJ：System info](https://onlinejudge.u-aizu.ac.jp/system_info)
- [paiza：言語環境](https://paiza.jp/guide/language)
- [ICPC 2026横浜地区大会](https://icpc.jp/2026/regional/environment/compiler-options/)
