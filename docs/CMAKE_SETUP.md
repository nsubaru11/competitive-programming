# 競技プログラミング用CMake設定

## CMake・CMakeLists.txt・.cmake

| 名前           | 意味                                                                                    |
|----------------|-----------------------------------------------------------------------------------------|
| CMake          | ビルド手順を作るツール。WSLではcmakeコマンドを実行します。                              |
| CMakeLists.txt | 各CMakeプロジェクトの入口となる設定ファイル                                             |
| .cmakeファイル | 設定を分割して置くファイル。LocalOptions.cmakeはCMakeLists.txtからincludeして読みます。 |

## 解答の実行

GCC 15.2.0を共通利用します。コンテストごとのパス指定は不要です。
解答は1ファイルで実行できるように書き、自作ライブラリの必要なコードは解答へコピペします。
親CMakeはlibrary/cppを読み込まず、そのヘッダー検索パスも追加しません。

1. CLionで.ide/clionを開きます。プロジェクトツリーにはリポジトリ全体が表示されます。
2. src/cpp/A.cppなどを開きます。
3. main()左の実行アイコンからRun/Debugします。実行するファイルだけをビルドします。
4. 新しいsrc/cppフォルダを初めて作った場合は、保存してCtrl+Shift+AからReload CMake Projectを実行します。

既存のsrc/cpp内に追加した.cppは、そのフォルダの変更確認で登録します。IDEに未登録表示が残ればReloadします。
C++フォルダの探索は構成時だけ行い、毎回リポジトリ全体を走査しません。
サイトのフォルダから `src` までが 3 階層以内にある `src/cpp/*.cpp` を登録します。
異なるコンテストのA.cppには別の実行ターゲット名が付きます。

### 実行ターゲットの名前

名前はサイト名・コンテストや問題集・問題名を組み合わせます。
`solution_`・`src/cpp`・拡張子の`.cpp`は含めず、区切りやハイフンなどは`_`に変換します。
コンテスト名や問題名に含まれる分類は、サイトごとの構成に合わせて省きます。

| サイト     | ソースの相対パスの例                        | 実行ターゲット名               |
|------------|---------------------------------------------|--------------------------------|
| AtCoder    | atcoder/abc/051-100/abc059/src/cpp/B.cpp    | atcoder_abc059_B               |
| AtCoder    | atcoder/other/dp/src/cpp/A.cpp              | atcoder_dp_A                   |
| AOJ        | aoj/alds1/topic01/src/cpp/A.cpp             | aoj_alds1_topic01_A            |
| Codeforces | codeforces/div3/round1103/src/cpp/A.cpp     | codeforces_round1103_A         |
| ICPC       | icpc/prelim/prelim2025/src/cpp/A.cpp        | icpc_prelim2025_A              |
| ICPC JAG   | icpc/jag/prelim/jagprelim2025/src/cpp/A.cpp | icpc_jagprelim2025_A           |
| paiza      | paiza/skill-check/a/src/cpp/A060.cpp        | paiza_skill_check_A060         |
| paiza      | paiza/level-up/binary-search/src/cpp/A.cpp  | paiza_level_up_binary_search_A |

- AtCoderのabc・agc・ahc・arc・awcは、末尾が同じ分類名と数字からなるコンテスト名の場合、分類と番号範囲を省きます。other直下のコンテストはotherを省きます。
- Codeforcesはroundと数字からなるコンテスト名の場合、div1〜div4を省きます。
- ICPCはprelimと数字からなるコンテスト名（末尾に-mockなどがあってもよい）の場合、prelimの分類を省きます。JAGも同様にjag/prelimを省きます。
- paizaのskill-checkは、問題名がランクの大文字と数字からなる場合、ランクのフォルダ名を省きます。
- AOJや上記以外の構成は、識別に必要な階層をそのまま残します。

短縮や記号の変換によって名前が重複した場合は、両方のソースのパスを示してCMakeの構成を停止します。
テンプレートのターゲット名は`template_cpp`とします。
命名規則の変更後はReload CMake Projectを実行し、新しい名前のCMake ApplicationからRun/Debugします。

### 実行設定の自動フォルダ分け

.ide/clionからCMakeを読み込むと、scripts/sync-clion-run-configurations.pyが実行設定を生成します。
Reload CMake Projectのたびに、実行設定をサイト・コンテストや問題集ごとのフォルダへ振り分けます。
例えばatcoder_abc059_A・B・Cは「atcoder / abc059」、paiza_skill_check_A060などは「paiza / skill-check」にまとまります。
AOJは「aoj / alds1 / topic01」などとします。スラッシュを含む名前のフォルダ1段であり、多段の階層ではありません。
template_cppはフォルダに入れません。

- 生成先は.ide/clion/.idea/runConfigurations/cp_<ターゲット名>.xml、生成ファイルの一覧は.ide/clion/.idea/cp-run-configurations.jsonとします。どちらもGitの管理外。
- 初回は、同じ名前・ターゲットの保存済み実行設定を引き継ぎます。新規ターゲットはCMake Applicationのテンプレートを使い、なければ通常のBuild付き設定を作ります。
- 以後はフォルダの割り当てだけを更新し、引数・入力リダイレクト・環境変数・プロファイル・起動前処理などの個別設定を保持します。workspace.xmlには書き込みません。
- 解答を削除・移動した場合は、自動生成した不要な設定だけを削除します。独自に作成・改名した実行設定は削除しません。
- WSL DebugとWSL Runの同時Reloadは、ロックと一時ファイルを使って直列に同期します。同じ内容のファイルは書き換えません。

初回は実行設定の編集画面をOKで閉じ、Reload CMake Projectの完了後に開き直して確認します。
反映されない場合はCLionでプロジェクトを開き直します。
自動同期を無効にする場合は、CMake optionsに`-DCP_CLION_RUN_CONFIGURATIONS=OFF`を追加します。生成済みの設定は残ります。
リポジトリのルートから直接CMakeを構成した場合は、CLionの実行設定を生成しません。

実行設定の同期に失敗しても、CMakeの構成は止めずに警告だけを出します。異常終了で.ide/clion/.idea/cp-run-configurations.lockが残っても、60秒を過ぎると次回の同期が取り除きます。

## CLionからCMakeへ追加する機能

未登録ファイルのエディタ右上にはAdd to CMake Project、新規作成時にはCMakeターゲットへの追加操作があります。
これはファイルをビルド対象へ登録する機能であり、自作ライブラリを読み込む機能ではありません。
競プロでは問題ごとにmain()があるため、A.cppをB.cppの既存ターゲットへまとめず、別々の実行ターゲットにします。
src/cpp内は自動登録するため、手動追加よりReloadを使い、二重に登録しません。

## 最初に一度確認するCLion設定

Ctrl+Alt+S → Build, Execution, Deployment → CMakeを開きます。

| 項目            | WSL Debug                             | WSL Run                             |
|-----------------|---------------------------------------|-------------------------------------|
| Toolchain       | WSL                                   | WSL                                 |
| Build type      | Debug                                 | Run                                 |
| Build directory | cmake-build-wsl-debug（.ide/clion内） | cmake-build-wsl-run（.ide/clion内） |
| CMake options   | -G Ninja                              | -G Ninja                            |
| 主なオプション  | -Og -g3                               | -O2 -g3 -march=native               |

どちらも有効にします。
LOCAL・軽量な標準ライブラリ検査・警告・フレームポインタ保持は両方で有効です。
両方ともテンプレートのdebug出力を使えます。Runでも必要に応じてデバッガを起動できるが、最適化によって変数が見えにくくなることがあります。
本番の速度・メモリ使用量の完全再現は目的にしません。NDEBUGやfast-mathは自動追加しません。
Apply → OK後、Reload CMake Projectを実行します。
コンテストを移っても設定を書き換えません。

## 設定の場所

| ファイル                                 | 役割                                                                              |
|------------------------------------------|-----------------------------------------------------------------------------------|
| .ide/clion/CMakeLists.txt                | CLion用の入口。リポジトリのCMakeLists.txtを読み込むだけ                           |
| CMakeLists.txt                           | 解答とテンプレートだけを登録します。                                              |
| cmake/LocalOptions.cmake                 | 言語規格、ローカル実行・デバッグ用オプション、GCCの標準ライブラリの実行時検索パス |
| scripts/list-cpp-directories.py          | CMake構成時にC++用フォルダを見つける補助処理                                      |
| scripts/sync-clion-run-configurations.py | CLionの実行設定を生成し、サイト・コンテストごとのフォルダへ振り分けます。         |
| library/cpp/CMakeLists.txt               | ライブラリ単独の開発・サンプル用。親からは読み込みません。                        |
| .ide/clion/.idea/workspace.xml           | このPCのWSL Debug / WSL Run設定                                                   |

## ライブラリの開発

library/cppを別ウィンドウで開くと、test/exampleのサンプルをビルドできます。
そのプロジェクトの中だけでsrcをヘッダー検索パスにします。解答側へは公開しません。
cp::libraryという公開ターゲットや、解答側のtarget_link_librariesによる自作ライブラリ連携は持ちません。
標準ライブラリのlibstdc++やstdc++expは自作ライブラリとは別物であり、使用するGCCに合わせて扱います。

## Python

Pythonの解答はCMakeでは実行しません。既存のExternal Toolsで現在のファイルを実行します。
CPython・PyPy・CodonはWSL側の既存環境を使い、追加インストールしません。バージョンは[README.md](../README.md)の使用環境を参照します。
CMakeがフォルダ探索の補助に使うPythonは、解答を実行するPythonとは別の役割です。

- [CLion：CMakeへのファイル登録](https://www.jetbrains.com/help/clion/managing-cmake-project-files.html)
- [GCC：デバッグ情報と最適化](https://gcc.gnu.org/onlinedocs/gcc/Debugging-Options.html)
- [提出先と言語規格](CPP_JUDGE_ENVIRONMENTS.md)
