# Copilot Instructions

## プロジェクト概要

このプロジェクトは競技プログラミングの解答、解答テンプレート、ローカルランナーを管理します。再利用ライブラリは library/java/ にあります。

## コーディング規則

- **JavaDoc コメント**: 圧縮版のクラス以外の全てのクラス・メソッドでJavaDocコメントを記述すること。
- **定数の命名規則**: 定数はUPPER_SNAKE_CASEで記述すること。
- **インデント**: インデントにはタブキーを使用すること。
- **ビット演算子の使用**: 限りなく高速化なコードにするため、可能ならビット演算子を使用すること。
- **オーバーロードの作成**: 汎用ライブラリを作成するため、様々なオーバーロードを作成すること。
- **プリミティブ型版とジェネリクス型版の実装**: 高速に動作させるため、プリミティブ型版とジェネリクス型版の両方を実装すること。
- **多次元配列の圧縮**: 内部的に多次元配列を用いる場合、1次元に圧縮すること。

## ディレクトリ構成

- atcoder/、aoj/、codeforces/、icpc/、paiza/: コンテスト別の解答。言語ごとに src/java/ などへ配置する。
- templates/: 言語別の解答テンプレート (java/ は Java 24版と Java 17版) と提出用ヘッダー (header/)。
- library/java/src/lib/: importして利用する競技プログラミングライブラリ。
- library/java/test/: ライブラリの検証コード。
- library/java/docs/: ライブラリのドキュメント。
- library/cpp/: C++ 版のライブラリ。
- tools/local-runner/: ローカルランナーとJavaのバンドル・実行処理。
- tools/userscripts/、tools/shared/: UserScriptと共有処理。
- out/、logs/: 生成物。ソースとして管理しない。

## JDKバージョン

- 基本の開発・実行環境は JDK 24.0.2（Java 24）。
- templates/java/Class17.java と library/java/src/lib/io/compat17/ は Java 17互換を維持する。
- ライブラリを編集する場合は library/java/AGENTS.md と配下の指示に従う。
- 提出時は tools/local-runner による lib.* のバンドルを使用する。詳細は docs/LIBRARY_USAGE.md を参照する。
