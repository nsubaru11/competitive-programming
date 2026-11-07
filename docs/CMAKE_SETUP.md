# 競技プログラミング用CMake設定

## CMake・CMakeLists.txt・.cmake

| 名前 | 意味 |
|---|---|
| CMake | ビルド手順を作るツール。WSLではcmakeコマンドを実行する |
| CMakeLists.txt | 各CMakeプロジェクトの入口となる設定ファイル |
| .cmakeファイル | 設定を分割して置くファイル。今回のLocalOptions.cmakeはCMakeLists.txtからincludeして読む |

## 解答の実行

GCC 15.2.0を共通利用する。コンテストごとのパス指定は不要。
解答は1ファイルで実行できるように書き、自作ライブラリの必要なコードは解答へコピペする。
親CMakeはlibrary/cppを読み込まず、そのヘッダー検索パスも追加しない。

1. CLionで.ide/clionを開く。プロジェクトツリーにはリポジトリ全体が表示される。
2. src/cpp/A.cppなどを開く。
3. main()左の実行アイコンからRun/Debugする。実行するファイルだけをビルドする。
4. 新しいsrc/cppフォルダを初めて作った場合は、保存してCtrl+Shift+AからReload CMake Projectを実行する。

既存のsrc/cpp内に追加した.cppは、そのフォルダの変更確認で登録する。IDEに未登録表示が残ればReloadする。
C++フォルダの探索は構成時だけ行い、毎回リポジトリ全体を走査しない。
登録対象は、サイトからコンテストまでが1〜3階層の現在の構成にあるsrc/cpp/*.cpp。
異なるコンテストのA.cppには別の実行ターゲット名が付く。

## CLionからCMakeへ追加する機能

未登録ファイルのエディタ右上にはAdd to CMake Project、新規作成時にはCMakeターゲットへの追加操作がある。
これはファイルをビルド対象へ登録する機能であり、自作ライブラリを読み込む機能ではない。
競プロでは問題ごとにmain()があるため、A.cppをB.cppの既存ターゲットへまとめず、別々の実行ターゲットにする。
今回のsrc/cpp内は自動登録するため、手動追加よりReloadを使い、二重に登録しない。

## 最初に一度確認するCLion設定

Ctrl+Alt+S → Build, Execution, Deployment → CMakeを開く。

| 項目 | WSL Debug | WSL Run |
|---|---|---|
| Toolchain | WSL | WSL |
| Build type | Debug | Run |
| Build directory | cmake-build-wsl-debug(.ide/clion内) | cmake-build-wsl-run(.ide/clion内) |
| CMake options | -G Ninja | -G Ninja |
| 主なオプション | -Og -g3 | -O2 -g3 -march=native |

どちらも有効にする。旧WSL JudgeはWSL Runに変更済み。
LOCAL・軽量な標準ライブラリ検査・警告・フレームポインタ保持は両方で有効。
両方ともテンプレートのdebug出力を使える。Runでも必要に応じてデバッガを起動できるが、最適化によって変数が見えにくくなることがある。
本番の速度・メモリ使用量の完全再現は目的にしない。NDEBUGやfast-mathは自動追加しない。
Apply → OK後、Reload CMake Projectを実行する。
古い-DCP_CONTEST指定は削除する。コンテストを移っても設定を書き換えない。

## 設定の場所

| ファイル | 役割 |
|---|---|
| .ide/clion/CMakeLists.txt | CLion用の入口。リポジトリのCMakeLists.txtを読み込むだけ |
| CMakeLists.txt | 解答とテンプレートだけを登録する |
| cmake/LocalOptions.cmake | 言語規格、ローカル実行・デバッグ用オプション、GCCの標準ライブラリの実行時検索場所 |
| scripts/list-cpp-directories.py | CMake構成時にC++用フォルダを見つける補助処理 |
| library/cpp/CMakeLists.txt | ライブラリ単独の開発・サンプル用。親からは読み込まない |
| .ide/clion/.idea/workspace.xml | このPCのWSL Debug / WSL Run設定 |

## ライブラリの開発

library/cppを別ウィンドウで開くと、サンプル4件をビルドできる。
そのプロジェクトの中だけでsrcをヘッダー検索場所にする。解答側へは公開しない。
cp::libraryという公開ターゲットと、解答側のtarget_link_librariesによる自作ライブラリ連携は削除した。
標準ライブラリのlibstdc++やstdc++expは自作ライブラリとは別物であり、使用するGCCに合わせて扱う。

## Python

Pythonの解答はCMakeでは実行しない。既存のExternal Toolsで現在のファイルを実行する。
CPython 3.13.7、PyPy 7.3.20、Codon 0.19.3はWSL側の既存環境を使い、追加インストールしない。
CMakeがフォルダ探索の補助に使うPythonは、解答を実行するPythonとは別の役割である。

- [CLion：CMakeへのファイル登録](https://www.jetbrains.com/help/clion/managing-cmake-project-files.html)
- [GCC：デバッグ情報と最適化](https://gcc.gnu.org/onlinedocs/gcc/Debugging-Options.html)
- [提出先と言語規格](CPP_JUDGE_ENVIRONMENTS.md)
