# IDEのプロジェクト設定

JetBrainsのIDEは、リポジトリのルートではなく、.ide内のIDEごとのフォルダを開く。
プロジェクト設定(.idea)がIDEごとに分かれるため、同時に開いても設定を上書きし合わない。
どのIDEでも、プロジェクトツリーにはリポジトリ全体が表示される。ソースの場所は共通。

| IDE           | 開くフォルダ | プロジェクト名          | 担当                      |
|---------------|--------------|-------------------------|---------------------------|
| IntelliJ IDEA | .ide/idea    | competitive-programming | Java、tools、library/java |
| CLion         | .ide/clion   | competitive-programming | C++(CMake)                |
| PyCharm       | .ide/pycharm | competitive-programming | Python                    |

ルートをIDEで開かない。開くとルートに.ideaが作られる。作られた場合はIDEを閉じてから削除する。他のIDEの設定には影響しない。
ほかのIDEを使う場合は、.ide/<IDE名>を作り、そこからリポジトリのルートを対象にする。

## 各フォルダの中身

| パス                                  | 内容                                                                                            | Git      |
|---------------------------------------|-------------------------------------------------------------------------------------------------|----------|
| .ide/idea/.idea/                      | IDEAの設定。Javaのモジュール一覧(modules.xml)、スコープ、検査プロファイル、ファイルテンプレート | 管理外   |
| .ide/idea/.idea/gradle.xml            | library/javaをGradleプロジェクトとしてリンクする設定。IDEが書く                                 | 管理外   |
| .ide/idea/competitive-programming.iml | リポジトリ全体を対象にするJavaモジュール。生成物を除外する                                      | 管理外   |
| .ide/clion/CMakeLists.txt             | CLion用の入口。リポジトリのCMakeLists.txtを読み込むだけ                                         | 管理対象 |
| .ide/clion/.idea/                     | CLionの設定。CMakeのプロファイル、プロジェクトのルート、除外フォルダ                            | 管理外   |
| .ide/clion/cmake-build-wsl-*/         | C++のビルド結果                                                                                 | 管理外   |
| .ide/pycharm/.idea/                   | PyCharmの設定。Python SDK(.venv)、リポジトリ全体を対象にするモジュール                          | 管理外   |

コンテストごとのJavaモジュール(*.iml)は、各コンテストのフォルダに置く。
codeStyles・inspectionProfiles・scopes・dictionariesは、各.ideaに同じ内容を置く。IDEで変更しても、他のIDEへは自動で反映されない。

改行コードはLFにそろえる(.bat・.cmd・.ps1だけCRLF)。.editorconfigのend_of_lineは、IDEで新しく作るファイルには効かない。そのため、codeStylesのLine separatorを「Unix and macOS (\n)」にしている(Settings → Editor → Code Style → Line separator)。
IDEは既存のファイルを開いたときの改行コードで保存し続ける。CRLFのファイルがあれば、ステータスバーの改行コードの表示か、File → File Properties → Line Separatorsで変える。フォルダを選ぶと配下のファイルをまとめて変えられる。

## library/javaのGradle連携

IntelliJ IDEAは、library/javaのbuild.gradle.ktsを読み込み、次の3つのモジュールを自動で作る。
IDEは、これらのモジュールとJUnitなどのライブラリの定義を.ideaではなくIDEのキャッシュ(%LOCALAPPDATA%\JetBrains\<IDE>\projects\)に保存する。.ide/idea/.idea/gradle.xmlやモジュールの定義を手で書かない。手で書くと、ライブラリの定義が保存されず、IDEでのビルドがJUnitを見つけられなくなることがある。

| モジュール   | 対象                | 依存                                |
|--------------|---------------------|-------------------------------------|
| library      | library/java全体    | なし。build・.gradle・outを除外する |
| library.main | library/java/src    | JDKだけ                             |
| library.test | library/java/test   | library.main、JUnit                 |

- リンクは、Gradleツールウィンドウ(右端の象のアイコン)の+(Link Gradle Project)で、library/java/build.gradle.ktsを選んで行う。モジュールがおかしくなったときは、libraryを右クリックしてUnlink Gradle Projectを選び、リンクし直す。
- コンテストのモジュール、ルートのモジュール、toolsのモジュールは、library.mainに依存する。解答から`lib.*`をimportできるのはこの依存による。
- JUnitなどの依存の版は、library/javaの`gradle/libs.versions.toml`で決まる。IDE側に版を書かない。
- build.gradle.ktsやlibs.versions.tomlを変えたら、Gradleツールウィンドウ(右端の象のアイコン)のReload All Gradle Projectsで読み込み直す。
- Settings → Build, Execution, Deployment → Build Tools → Gradleで、Gradle JVMをProject SDK(jdk24.0.2)、Build and run usingとRun tests usingをIntelliJ IDEAにする。リンクし直すと既定値(Gradle)に戻るため、設定し直す。コマンドラインのgradlewはJAVA_HOMEのJDKで動く。
- Run tests usingがGradleのままだと、IDEからのテストの実行がslowTestタスクにも渡され、「No matching tests found」で失敗する。
- テストの実行方法と網羅率の確認はlibrary/javaの[test/README.md](../library/java/test/README.md)を参照する。

## テンプレートと共通設定の同期

scripts/sync-jetbrains.pyが、次の2つをまとめて同期する。リポジトリからIDEへの一方向の同期であり、IDEでの編集をリポジトリへ取り込む機能はない。

| 対象                 | 同期元                                                                | 同期先                                                                        |
|----------------------|-----------------------------------------------------------------------|-------------------------------------------------------------------------------|
| ファイルテンプレート | templates/java・templates/python・templates/cpp                       | IntelliJ IDEA・PyCharm・CLionの、最新バージョンのIDE全体の設定(fileTemplates) |
| 共通設定             | .ide/idea/.ideaのcodeStyles・inspectionProfiles・scopes・dictionaries | 他の.ide/*/.idea                                                              |

- テンプレートはフォルダでIDEが決まり、ファイル名がテンプレート名になる。templates/javaのClass.javaだけはIDE標準のテンプレートを上書きするため、fileTemplates/internalに置く。READMEとtemplates/headerは同期しない。
- Java以外のテンプレートは、#や$を含む行をVelocityが解釈しないよう、そのまま出力される形に変換して書き込む。
- codeStyles・inspectionProfiles・scopesは.ide/ideaと同じ内容にそろえ、.ide/ideaにないファイルは削除する。コードスタイルなどはIntelliJ IDEAで変更してから同期する。
- dictionariesは全IDEの単語を合わせて、すべてに書き込む。単語がそろっていれば書き換えない。

実行はリポジトリのルートで `.venv\Scripts\python.exe scripts\sync-jetbrains.py` とする。

| オプション | 動作                                                                                                                                                                             |
|------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| --check    | 変更する内容を表示するだけで、書き込まない。変更があれば終了コード1                                                                                                              |
| --prune    | templatesにないテンプレートをIDE側から削除する。名前を変えたり削除したりしたテンプレートを消すときに使う。IDEで自分で作ったテンプレートも消えるため、先に--checkと併せて確認する |
| --force    | JetBrainsのIDEが起動していても書き込む。通常は、起動中だと書き込まずに終了する                                                                                                   |

IDEは起動中の設定を終了時に保存し直すため、IDEを閉じてから実行する。変更前の内容はout/jetbrains-sync-backup/backup.jsonに退避し、前回の退避は実行のたびに消す。

## 最初に一度行う設定

- 各IDEの最近のプロジェクトにリポジトリのルートが残っていれば削除する。起動時に前回のプロジェクトとして開かれるのを防ぐ。
- IntelliJ IDEA: .ide/ideaを開く。新しいコンテストのモジュールは.ide/idea/.idea/modules.xmlへ登録する。パスは$PROJECT_DIR$/../../から始める。モジュールの依存には`<orderEntry type="module" module-name="library.main" />`を入れる。
- CLion: .ide/clionを開き、CMakeの設定を[CMAKE_SETUP.md](CMAKE_SETUP.md)の表と照らし合わせる。
- PyCharm: .ide/pycharmを開く。pyproject.tomlからプロジェクト構成を自動生成するWorkspaceモードは無効にする。.ide/pycharm/.idea/pyProjectModel.xmlのusePyprojectTomlをfalseにする。Settings → Project Structureの切り替えと同じ設定である。有効にすると、モジュールが作り直されて除外設定が失われることがあり、パッケージの導入やpyproject.tomlへの追記も自動で提案される。

- [PyCharm：Workspaces and monorepos](https://www.jetbrains.com/help/pycharm/python-workspaces.html)
- [CLion：Change project root](https://www.jetbrains.com/help/clion/change-project-root-directory.html)
