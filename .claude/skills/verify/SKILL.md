---
name: verify
description: "変更した場所ごとの検証コマンドの一覧。Java の解答とライブラリ、tools（Local Runner、UserScript）、C++、Python の変更を確かめるときに使う。"
---

# 検証の方法

変更した場所に合う行だけを実行する。時間のかかるビルドと全件の実行は、頼まれたときだけ行う。確かめていない項目は、そう報告する。

## Java

| 変更した場所       | 実行する場所            | コマンド                                                                  |
|--------------------|-------------------------|---------------------------------------------------------------------------|
| 解答               | 本体のルート            | `javac -encoding UTF-8 -cp library/java/src -d <一時フォルダ> <ファイル>` |
| 解答（サンプル）   | コンテストの `src/java` | `test a`                                                                  |
| `library/java/src` | `library/java`          | `.\gradlew.bat test`                                                      |
| `lib.io.compat17`  | `library/java`          | `.\gradlew.bat check`                                                     |

- `javac` は JDK 24.0.2（`C:\Program Files\java\jdk-24.0.2\bin`）を使う。PATH 上の `java` は JDK 25。
- 解答はデフォルトパッケージの同名クラスが多いので、1ファイルずつコンパイルする。出力先はリポジトリの外の一時フォルダにする。
- `gradlew` は `JAVA_HOME`（JDK 24.0.2）で動く。
- `test a` は AtCoder のサンプルで実行し、AC か WA かを判定する。`submit` は実行しない。ランナーは WSL に常駐し、Windows の環境変数は届かない。

## tools

[tools/AGENTS.md](../../../tools/AGENTS.md) の「検証」に従う。

## C++

`library/cpp` を、CLion 付属の `g++`・`cmake`・`ninja`（`%LOCALAPPDATA%\Programs\CLion 2\bin`）でビルドする。構成は [CMAKE_SETUP.md](../../../docs/CMAKE_SETUP.md) にある。

## Python

- 静的解析：ルートの `.venv`（uv で管理）。`uv sync` のあと `uv run python <ファイル>`。
- 実行：WSL の CPython・PyPy・Codon。
- 報告には、どちらの環境で確かめたかを書く。
