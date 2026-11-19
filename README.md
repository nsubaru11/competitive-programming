![Java 24](https://img.shields.io/badge/Java-24-ED8B00?style=flat-square)
![C++23](https://img.shields.io/badge/C%2B%2B-23-00599C?style=flat-square)
![Python 3.13](https://img.shields.io/badge/Python-3.13-3776AB?style=flat-square)
[![License: 0BSD](https://img.shields.io/badge/license-0BSD-blue?style=flat-square)](./LICENSE)

# Competitive Programming

競技プログラミングの解答、ライブラリ、テンプレート、補助ツールのソースコードを管理するリポジトリです。

- Author: nsubaru（[AtCoder](https://atcoder.jp/users/nsubaru) / [Codeforces](https://codeforces.com/profile/nsubaru)）

## 関連リポジトリ

次のリポジトリは、submoduleとして管理しています。

- [Java Library](https://github.com/nsubaru11/competitive-programming-java-library)：Javaのアルゴリズム・データ構造（`library/java`）
- [C++ Library](https://github.com/nsubaru11/competitive-programming-cpp-library)：C++のアルゴリズム・データ構造（`library/cpp`）
- [Tools](https://github.com/nsubaru11/competitive-programming-tools)：解答を手元で実行・判定・提出する Local Runner と、ジャッジサイトの補助機能を足す CP Toolkit（`tools`）

## フォルダ構成

```text
competitive-programming/
├── atcoder/          # AtCoderの解答
├── aoj/              # Aizu Online Judgeの解答
├── codeforces/       # Codeforcesの解答
├── icpc/             # ICPC・JAGの解答
├── paiza/            # paizaの解答
├── library/
│   ├── java/         # Javaライブラリ（submodule）
│   └── cpp/          # C++ライブラリ（submodule）
├── templates/        # 言語別テンプレート・提出用ヘッダー
├── tools/            # 補助ツール（submodule）
│   ├── shared/       # 共通TypeScript
│   ├── local-runner/
│   └── userscripts/
├── scripts/          # リポジトリ管理用スクリプト
├── cmake/            # CMakeの設定
├── .ide/             # IDEごとのプロジェクト（各IDEはここから開く）
└── docs/             # 設定・仕様の資料
```

## 使用環境

| 言語   | バージョン                                                       |
|--------|------------------------------------------------------------------|
| Java   | AtCoder・ICPC: 24.0.2 / Codeforces: 21 / AOJ: 11 / paiza: 18.0.2 |
| C++    | GCC 15.2.0（WSL、AtCoderはGNU C++23）                            |
| Python | CPython 3.13.7 / PyPy 3.11-v7.3.20 / Codon 0.19.3（WSL）         |

ライセンスは [0BSD](LICENSE) です。各submoduleには、それぞれのリポジトリのライセンスが適用されます。
