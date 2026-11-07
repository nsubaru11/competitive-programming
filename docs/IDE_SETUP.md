# IDEのプロジェクト設定

JetBrainsのIDEは、リポジトリのルートではなく、.ide内のIDEごとのフォルダを開く。
プロジェクト設定(.idea)がIDEごとに分かれるため、同時に開いても設定を上書きし合わない。
どのIDEでも、プロジェクトツリーにはリポジトリ全体が表示される。ソースの場所は共通。

| IDE | 開くフォルダ | プロジェクト名 | 担当 |
|---|---|---|---|
| IntelliJ IDEA | .ide/idea | competitive-programming | Java、tools、library/java |
| CLion | .ide/clion | competitive-programming | C++(CMake) |
| PyCharm | .ide/pycharm | competitive-programming | Python |

ルートをIDEで開かない。開くとルートに.ideaが作られる。作られた場合はIDEを閉じてから削除する。他のIDEの設定には影響しない。
ほかのIDEを使う場合は、.ide/<IDE名>を作り、そこからリポジトリのルートを対象にする。

## 各フォルダの中身

| パス | 内容 | Git |
|---|---|---|
| .ide/idea/.idea/ | IDEAの設定。Javaのモジュール一覧(modules.xml)、スコープ、検査プロファイル、ファイルテンプレート | 管理外 |
| .ide/idea/competitive-programming.iml | リポジトリ全体を対象にするJavaモジュール。生成物を除外する | 管理外 |
| .ide/clion/CMakeLists.txt | CLion用の入口。リポジトリのCMakeLists.txtを読み込むだけ | 管理対象 |
| .ide/clion/.idea/ | CLionの設定。CMakeのプロファイル、プロジェクトのルート、除外フォルダ | 管理外 |
| .ide/clion/cmake-build-wsl-*/ | C++のビルド結果 | 管理外 |
| .ide/pycharm/.idea/ | PyCharmの設定。Python SDK(.venv)、リポジトリ全体を対象にするモジュール | 管理外 |

コンテストごとのJavaモジュール(*.iml)は、各コンテストのフォルダに置く。
codeStyles・inspectionProfiles・scopes・dictionariesは、各.ideaに同じ内容を置く。IDEで変更しても、他のIDEへは自動で反映されない。
scripts/sync-ide-project-settings.pyが、.ide/ideaを正として他の.ide/*/.ideaへ写す。codeStyles・inspectionProfiles・scopesは.ide/ideaと同じ内容にそろえ、.ide/ideaにないファイルは削除する。dictionariesは全IDEの単語を合わせて、すべてに書き込む。
コードスタイルなどはIntelliJ IDEAで変更してから同期する。IDEを閉じた状態で実行する。反映状況だけ確認する場合は--checkを指定する。変更前の内容はout/ide-settings-backupsに退避する。
ファイルテンプレートは、scripts/sync-jetbrains-templates.pyが.ide/idea/.idea/fileTemplatesとIDE側の設定へ同期する。

## 最初に一度行う設定

- 各IDEの最近のプロジェクトにリポジトリのルートが残っていれば削除する。起動時に前回のプロジェクトとして開かれるのを防ぐ。
- IntelliJ IDEA: .ide/ideaを開く。新しいコンテストのモジュールは.ide/idea/.idea/modules.xmlへ登録する。パスは$PROJECT_DIR$/../../から始める。
- CLion: .ide/clionを開き、CMakeの設定を[CMAKE_SETUP.md](CMAKE_SETUP.md)の表と照らし合わせる。
- PyCharm: .ide/pycharmを開く。pyproject.tomlからプロジェクト構成を自動生成するWorkspaceを有効にすると、モジュールが作り直される。除外設定が失われていないか確認する。

- [PyCharm：Workspaces and monorepos](https://www.jetbrains.com/help/pycharm/python-workspaces.html)
- [CLion：Change project root](https://www.jetbrains.com/help/clion/change-project-root-directory.html)
