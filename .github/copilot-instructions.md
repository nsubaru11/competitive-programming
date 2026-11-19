# Copilot Instructions

## プロジェクト概要

このプロジェクトは競技プログラミングの解答、解答テンプレート、ローカルランナーを管理する。再利用ライブラリは `library/java/` にある。

## コーディング規則

- JavaDoc コメント: 圧縮版のクラス以外の全てのクラス・メソッドでJavaDocコメントを記述する。
- 定数の命名規則: 定数はUPPER_SNAKE_CASEで記述する。
- インデント: インデントにはタブキーを使用する。
- ビット演算子の使用: コードを高速化するため、可能ならビット演算子を使用する。
- オーバーロードの作成: 汎用ライブラリを作成するため、様々なオーバーロードを作成する。
- プリミティブ型版とジェネリクス型版の実装: 高速に動作させるため、プリミティブ型版とジェネリクス型版の両方を実装する。
- 多次元配列の圧縮: 内部的に多次元配列を用いる場合、1次元に圧縮する。

文書を書くときは [AGENTS.md の「文書の書き方」](../AGENTS.md#文書の書き方)に従う。

## ディレクトリ構成

- `atcoder/`、`aoj/`、`codeforces/`、`icpc/`、`paiza/`：コンテスト別・言語別の解答（`src/java/` など）
- templates/: 言語別の解答テンプレート（`java/` は Java 24 版と Java 17 版）と提出用ヘッダー（`header/`）
- library/java/src/lib/: import して利用する競技プログラミングライブラリ
- library/java/test/: ライブラリの検証コード
- library/java/docs/: ライブラリのドキュメント
- library/cpp/: C++ 版のライブラリ
- tools/local-runner/: Java・C++・C・Python の実行と Java のバンドルを行うローカルランナー
- tools/userscripts/、tools/shared/: UserScript と共有処理
- out/、logs/: ソース管理対象外の生成物

## JDKバージョン

- 基本の開発・実行環境は JDK 24.0.2（Java 24）とする。
- templates/java/Class17.java と library/java/src/lib/io/compat17/ は Java 17互換を維持する。
- ライブラリを編集する場合は library/java/AGENTS.md と配下の指示に従う。
- 提出時は tools/local-runner による lib.* のバンドルを使用する。詳細は docs/LIBRARY_USAGE.md を参照する。
