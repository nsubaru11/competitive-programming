# Competitive Programming

競技プログラミングの解答、ライブラリ、テンプレート、補助ツールのソースコードを管理するリポジトリです。

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

解答は基本的にコンテストごとの `src/<言語>/` に配置します。

## 使用環境

| 言語   | バージョン                                                       |
|--------|------------------------------------------------------------------|
| Java   | AtCoder・ICPC: 24.0.2 / Codeforces: 21 / AOJ: 11 / paiza: 18.0.2 |
| C++    | GCC 15.2.0（WSL、AtCoderはGNU C++23）                            |
| Python | CPython 3.13.7 / PyPy 3.11-v7.3.20 / Codon 0.19.3（WSL）         |

ライセンスは [0BSD](LICENSE) です。サブモジュールは各リポジトリのライセンスに従います。
