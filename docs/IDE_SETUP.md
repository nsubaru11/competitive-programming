# IDEのプロジェクト設定

JetBrains の IDE では、`.ide` 内にある各 IDE 用のフォルダを開きます。
プロジェクト設定（.idea）がIDEごとに分かれるため、同時に開いても設定を上書きし合いません。
どの IDE でもプロジェクトツリーにリポジトリ全体が表示され、同じソースを編集できます。

| IDE     | 開くフォルダ | プロジェクト名          | 担当                      |
|---------|--------------|-------------------------|---------------------------|
| IDEA    | .ide/idea    | competitive-programming | Java、tools、library/java |
| CLion   | .ide/clion   | competitive-programming | C++（CMake）              |
| PyCharm | .ide/pycharm | competitive-programming | Python                    |

ルートを IDE で開くと、そこに `.idea` が作られます。作られた場合はIDEを閉じてから削除します。他のIDEの設定には影響しません。
ほかのIDEを使う場合は、.ide/<IDE名>を作り、そこからリポジトリのルートを対象にします。

## 各フォルダの中身

| パス                                  | 内容                                                                                              | Git      |
|---------------------------------------|---------------------------------------------------------------------------------------------------|----------|
| .ide/idea/.idea/                      | IDEAの設定。Javaのモジュール一覧（modules.xml）、コードスタイル、検査プロファイル、スコープ、辞書 | 管理外   |
| .ide/idea/.idea/gradle.xml            | library/javaをGradleプロジェクトとしてリンクする設定。IDEが書きます。                             | 管理外   |
| .ide/idea/competitive-programming.iml | リポジトリ全体を対象にするJavaモジュール。生成物を除外します。                                    | 管理外   |
| .ide/clion/CMakeLists.txt             | CLion用の入口。[CMAKE_SETUP.md](CMAKE_SETUP.md#設定の場所)を参照してください。                    | 管理対象 |
| .ide/clion/.idea/                     | CLionの設定。CMakeのプロファイル、プロジェクトのルート、除外フォルダ                              | 管理外   |
| .ide/clion/cmake-build-wsl-*/         | C++のビルド結果                                                                                   | 管理外   |
| .ide/pycharm/.idea/                   | PyCharmの設定。Python SDK（.venv）、リポジトリ全体を対象にするモジュール                          | 管理外   |

コンテストごとのJavaモジュール（*.iml）は、各コンテストのフォルダに置きます。
codeStyles・inspectionProfiles・scopes・dictionariesは、各.ideaに同じ内容を置きます。IDEで変更しても、他のIDEへは自動で反映されません。

改行コードはLFにそろえます（.bat・.cmd・.ps1だけCRLF）。.editorconfigのend_of_lineは、IDEで新しく作るファイルには効きません。そのため、codeStylesのLine separatorを「Unix and macOS (\n)」にしています（Settings → Editor → Code Style → Line separator）。
IDEは既存のファイルを開いたときの改行コードで保存し続けます。CRLFのファイルがあれば、ステータスバーの改行コードの表示か、File → File Properties → Line Separatorsで変えます。フォルダを選ぶと配下のファイルをまとめて変えられます。

## コード整形の方針

C++とPythonも、Javaに近い書式にそろえます。

- インデントは4幅のタブ、行の長さは120。折り返した行の字下げは、Java・C++・Pythonが8（タブ2つ分）、CMake・TypeScriptなどそれ以外が4（タブ1つ分）。括弧の位置には揃えません。
- 波括弧は文の末尾に置き、`} else {` の形にします。`case` は字下げし、C++のnamespaceは字下げしません。
- 三項演算子を折り返すときは、`?` と `:` を行頭に置きます。
- CMakeとPowerShellは、.editorconfigのタブ指定を受け取らないため、codeStylesに直接タブを指定しています。TOMLは空白4つ（uvの形式に合わせる）とし、uv.lockは整形しません。
- .editorconfigの`ij_java_*`や`ij_python_*`は、その言語のプラグインがないIDEでは「The property is not supported」と警告されます。整形には影響しないため、検査EditorConfigKeyCorrectnessを無効にして出しません。
- C++の整形は.clang-formatを正とします。CLionのコードスタイル（Project.xml）は、clang-formatを使えないときの補助で、同じ内容にそろえます。
- C++のポインタと参照は、`T* a`、`T& a` の形にします。
- C++の命名は標準ライブラリに合わせ、関数・変数・conceptはスネークケース、クラスはパスカルケースにします。
- 過去のコンテストで書いた解答は、基本的に整形し直しません。PythonとC++の解答はファイル数が少ないため、整形の対象にします。
- Reformat Codeのダイアログは、Rearrange entriesをオン、Optimize importsをオフ、Code cleanupをオンにします。この設定はIDEごとに保存され、リポジトリでは共有できないため、使うIDEそれぞれで合わせます。

## library/javaのGradle連携

IDEAは、library/javaのbuild.gradle.ktsを読み込み、次の3つのモジュールを自動で作ります。
IDEは、これらのモジュールとJUnitなどのライブラリの定義を.ideaではなくIDEのキャッシュ（%LOCALAPPDATA%\JetBrains\<IDE>\projects\）に保存します。.ide/idea/.idea/gradle.xmlやモジュールの定義は手で書かないでください。手で書くと、ライブラリの定義が保存されず、IDEでのビルドがJUnitを見つけられなくなることがあります。

| モジュール   | 対象              | 依存                                    |
|--------------|-------------------|-----------------------------------------|
| library      | library/java全体  | なし。build・.gradle・outを除外します。 |
| library.main | library/java/src  | JDKだけ                                 |
| library.test | library/java/test | library.main、JUnit                     |

- リンクは、Gradleツールウィンドウ（右端の象のアイコン）の+（Link Gradle Project）で、library/java/build.gradle.ktsを選んで行います。モジュールがおかしくなったときは、libraryを右クリックしてUnlink Gradle Projectを選び、リンクし直します。
- AtCoderのコンテストのモジュール、ルートのモジュール、toolsのモジュールは、library.mainに依存します。解答から`lib.*`をimportできるのはこの依存によります。ICPCのモジュールは、libを使う場合に依存を入れます。AOJ（JDK 11）・paiza（JDK 18）・Codeforces（JDK 21）のモジュールは、JDK 24のlibraryをビルドできないため依存しません。
- JUnitなどの依存のバージョンは、library/javaの`gradle/libs.versions.toml`で決まります。IDE側にバージョンを書きません。
- build.gradle.ktsやlibs.versions.tomlを変えたら、Gradleツールウィンドウ（右端の象のアイコン）のReload All Gradle Projectsで読み込み直します。
- Settings → Build, Execution, Deployment → Build Tools → Gradleで、Gradle JVMをProject SDK（jdk24.0.2）、Build and run usingとRun tests usingを「IntelliJ IDEA」にします。リンクし直すと既定値（Gradle）に戻るため、設定し直します。コマンドラインのgradlewはJAVA_HOMEのJDKで動きます。
- Run tests usingがGradleのままだと、IDEからのテストの実行がslowTestタスクにも渡され、「No matching tests found」で失敗します。
- テストの実行方法と網羅率の確認はlibrary/javaの[test/README.md](../library/java/test/README.md)を参照してください。

## テンプレートと共通設定の同期

scripts/sync-jetbrains.pyが、次の2つをまとめて同期します。リポジトリからIDEへの一方向の同期であり、IDEでの編集をリポジトリへ取り込む機能はありません。

| 対象                 | 同期元                                                                | 同期先                                                                 |
|----------------------|-----------------------------------------------------------------------|------------------------------------------------------------------------|
| ファイルテンプレート | templates/java・templates/python・templates/cpp                       | IDEA・PyCharm・CLionの、最新バージョンのIDE全体の設定（fileTemplates） |
| 共通設定             | .ide/idea/.ideaのcodeStyles・inspectionProfiles・scopes・dictionaries | 他の.ide/*/.idea                                                       |

- テンプレートはフォルダでIDEが決まり、ファイル名がテンプレート名になります。templates/javaのClass.javaだけはIDE標準のテンプレートを上書きするため、fileTemplates/internalに置きます。READMEとtemplates/headerは同期しません。
- Java以外のテンプレートは、#や$を含む行をVelocityが解釈しないよう、そのまま出力される形に変換して書き込みます。
- codeStyles・inspectionProfiles・scopesは.ide/ideaと同じ内容にそろえ、.ide/ideaにないファイルは削除します。コードスタイルなどはIntelliJ IDEAで変更してから同期します。
- dictionariesは全IDEの単語を合わせて、すべてに書き込みます。単語がそろっていれば書き換えません。

実行はリポジトリのルートで `.venv\Scripts\python.exe scripts\sync-jetbrains.py` とします。

| オプション | 動作                                                                                                                                                                                       |
|------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| --check    | 変更する内容を表示するだけで、書き込みません。変更があれば終了コード1                                                                                                                      |
| --prune    | templatesにないテンプレートをIDE側から削除します。名前を変えたり削除したりしたテンプレートを消すときに使います。IDEで自分で作ったテンプレートも消えるため、先に--checkと併せて確認します。 |
| --force    | JetBrainsのIDEが起動していても書き込みます。通常は、起動中だと書き込まずに終了します。                                                                                                     |

IDEは起動中の設定を終了時に保存し直すため、IDEを閉じてから実行します。変更前の内容はout/jetbrains-sync-backup/backup.jsonに退避し、前回の退避は実行のたびに消します。

## External Tools

IDEAの `%APPDATA%\JetBrains\IntelliJIdea<バージョン>\tools\Competitive Programming.xml` に、Local Runnerを呼ぶ4件を登録します。Programは `C:\Windows\System32\cmd.exe`、Working directoryは `$FileDir$` とし、Open console for tool outputをオンにします。`$ProjectFileDir$` は `.ide/idea` を指します。

Argumentsは次の形にします。`<コマンド>` と `<引数>` は下の表に従います。

```text
/d /e:on /v:off /c set "FORCE_COLOR=1" & "$ProjectFileDir$\..\..\.competitive-programming\competitive-programming-tools\local-runner\bin\<コマンド>.cmd" <引数>
```

| ツール                   | コマンド    | 引数                                       |
|--------------------------|-------------|--------------------------------------------|
| CP: サンプルテスト       | `test`      | `"$FileNameWithoutExtension$" --lang=java` |
| CP: 提出                 | `submit`    | `"$FileNameWithoutExtension$" --lang=java` |
| CP: 提出用コードをコピー | `toclip`    | `"$FileName$"`                             |
| CP: ローカルテスト       | `localtest` | `"$FileName$"`                             |

Runコンソールでも色を出すため、`FORCE_COLOR=1` をこのcmdの子プロセスだけに設定します。空でない `NO_COLOR` が設定されている場合は無色になります。このExternal Tools設定は `scripts/sync-jetbrains.py` の同期対象ではありません。

## 最初に一度行う設定

- 各IDEの最近のプロジェクトにリポジトリのルートが残っていれば削除します。起動時に前回のプロジェクトとして開かれるのを防ぎます。
- IDEA：.ide/ideaを開きます。新しいAtCoderのJavaモジュールは、リポジトリのルートで`python scripts/new-atcoder-module.py abc484`のように用意します。引数を省略すると、モジュール名の入力を求めます。モジュール名は大文字で入力しても構いません。
- スクリプトはフォルダの実態を優先します。コンテストのフォルダ、`src/java`、source root付きの`.iml`のうち、無いものだけを作り、既にあるものは上書きせずに使います。そのうえで、.ide/idea/.idea/modules.xmlに登録が無ければ登録します。フォルダが別の場所へ移っていて古い登録が残っている場合や、`src/java`が無いのに登録が残っている場合は、その登録を一度消して登録し直します。IDEAに登録が反映されない場合は、プロジェクトを再読み込みします。
- 置き場所は、`abc`・`arc`・`agc`・`awc`・`ahc`ならコンテスト番号から50回ごとのフォルダ（1回目から始まり、例：abc484は`atcoder/abc/451-500`）、それ以外は`atcoder/other`です。数値prefixのコンテスト名は、AWCを4桁、その他を3桁にそろえます。Javaファイルは`src/java`でIDEAのFile → New → Java Classから作成し、IDEAのテンプレートを使います。
- AtCoder以外（ICPC、AOJ、paiza、Codeforces）の新しいコンテストのモジュールは、.ide/idea/.idea/modules.xmlへ手動で登録します。パスは$PROJECT_DIR$/../../から始めます。
- JDK 24を使うモジュール（AtCoder、ICPC）は、JDKをプロジェクト設定から継承します（`<orderEntry type="inheritedJdk" />`）。libを使うモジュールには、依存として`<orderEntry type="module" module-name="library.main" />`を入れます。AtCoderのモジュールはスクリプトがどちらも入れます。AOJ・paiza・Codeforcesのモジュールは言語レベルがJDK 24より低いため、JDKを明示し、libへの依存も入れません。
- CLion：.ide/clionを開き、CMakeの設定を[CMAKE_SETUP.md](CMAKE_SETUP.md)の表と照らし合わせます。
- CLion：EditorConfigプラグイン（JetBrains公式）を入れます。IDEAとPyCharmには同梱されているが、CLionには入っていないため、入れないと.editorconfigが無視されます。Settings → Plugins → MarketplaceでEditorConfigを検索してInstallし、IDEを再起動します。導入後、PythonやCMakeのファイルを開き、右下の字下げ表示が「Tab」になることを確かめます。
- PyCharm：.ide/pycharmを開きます。pyproject.tomlからプロジェクト構成を自動生成するWorkspaceモードは無効にします。.ide/pycharm/.idea/pyProjectModel.xmlのusePyprojectTomlをfalseにします。Settings → Project Structureの切り替えと同じ設定です。有効にすると、モジュールが作り直されて除外設定が失われることがあり、パッケージの導入やpyproject.tomlへの追記も自動で提案されます。

- [PyCharm: Workspaces and monorepos](https://www.jetbrains.com/help/pycharm/python-workspaces.html)
- [CLion: Change project root](https://www.jetbrains.com/help/clion/change-project-root-directory.html)

## AtCoderのコンテスト名と教材のファイル名

コンテストのフォルダ名はAtCoderのURLの `/contests/` 以降の公式IDに合わせます。AtCoderのJavaの `.iml` 名はフォルダ名とそろえます。AtCoder以外は、モジュール名が重複しないよう、サイト名を頭に付けます（例：`aoj-alds1-topic01`、`icpc-prelim2020`、`paiza-skill-check-a`、`codeforces-round1103`）。移動・改名時は `.ide/idea/.idea/modules.xml` の参照も更新します。コンテスト専用のローカル別名は増やしません。

- `atcoder/other/codequeen2024-final-N9tn8QqD`・`codequeen2025-final-Public` は各年の決勝の公式IDを使います。
- `atcoder/ahc/001-050/ahc040` は3桁の開催番号にそろえます。
- `atcoder/ahc/intro-heuristics` は公式IDを使います。
- サークル内のバーチャルコンテストである `wcpc2025`・`wcpc20260917`・`wcpc2026first` は独自名を維持します。

典型90問のJavaは、問題番号を3桁で残した `Q001.java`〜`Q090.java` とし、クラス名も `Q001`〜`Q090` にそろえます。`Q` は、数字から始まるクラス名を避けるために付けます。

ABSは公式コンテストIDの `abs` をフォルダ名に使い、ファイル名・クラス名は公式問題一覧の記号（`PracticeA`・`ABC086A` など）に合わせます。`No0` のような独自の通し番号は使いません。同じ問題の別実装は `ABC081B_2.java` のように末尾へ `_2` などを付けて区別します。

Local Runnerでは、たとえば `test typical90_a Q001.java` のように正式なタスクIDと実際のファイル名を指定します。

## 解答の別実装のファイル名

別実装は、問題記号の後ろに `_` と識別名を付けます。数字なら `A_2.java`、解法の特徴なら `C_deque.java`・`A_mountain.java` のようにします。Javaのクラス名もファイル名に合わせます。`A2.java` のように番号を直接続けた別実装名は使いません。`A01`・`Q001` など、問題自体の番号は維持します。

Local Runnerの `test`・`submit` は、ファイル名から拡張子を除き、最後の `_` 以降だけを除いて問題記号を決めます。`test C_deque.java` と `test c_deque` はどちらもC問題で `C_deque.java` を実行します。`A15_1.java` はA15問題、`D_2.java` はD問題として扱います。識別名に複数の `_` を使うと最後のものだけが除かれるため、問題を推定して使う識別名は `_` を増やさずに付けます。
