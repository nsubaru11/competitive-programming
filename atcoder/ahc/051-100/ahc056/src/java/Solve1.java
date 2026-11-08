import java.util.*;

import lib.io.*;

import static java.util.Arrays.*;

public final class Solve1 {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();
	// endregion

	// ------------------------ 定数 ------------------------


	// private static final int MOD = 1_000_000_007;
	private static final int[][] dij = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}}; // 左、上、右、下
	// private static final int[][] dij = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};


	private static final int L = 0, U = 1, R = 2, D = 3;

	// ------------------------ メインロジック ------------------------
	private static void solve() {
		// init
		int n = sc.nextInt(); // 次元数
		int k = sc.nextInt(); // 目的地の数
		int t = sc.nextInt(); // 最大ステップ数
		int[] wall = new int[n * n]; // grid[i][j]に面している壁のフラグ
		for (int i = 0; i < n; i++) {
			wall[i] |= 1 << U;
			wall[n * (n - 1) + i] |= 1 << D;
			wall[i * n] |= 1 << L;
			wall[(i + 1) * n - 1] |= 1 << R;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1; j++) {
				char v = sc.nextChar();
				if (v == '0') continue;
				wall[i * n + j] |= 1 << R;
				wall[i * n + j + 1] |= 1 << L;
			}
		}
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n; j++) {
				char h = sc.nextChar();
				if (h == '0') continue;
				wall[i * n + j] |= 1 << D;
				wall[(i + 1) * n + j] |= 1 << U;
			}
		}
		int[] point = new int[k]; // 目的地のリスト
		for (int i = 0; i < k; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			point[i] = x * n + y;
		}

		// bfs
		ArrayList<Integer> path = new ArrayList<>();
		path.add(point[0]);
		int[] dest = new int[n * n];
		for (int k2 = 0; k2 < k - 1; k2++) {
			ArrayDeque<Integer> dq = new ArrayDeque<>();
			int from = point[k2], to = point[k2 + 1];
			fill(dest, -1);
			dest[from] = from;
			dq.add(from);
			outer:
			while (!dq.isEmpty()) {
				from = dq.poll();
				for (int d = 0; d < 4; d++) {
					if ((wall[from] >> d & 1) == 1) continue; // 方向`d`が壁の時
					int next = from + dij[d][0] * n + dij[d][1]; // 移動先
					if (dest[next] >= 0) continue; // 既に探索済みの時
					dest[next] = from;
					if (next == to) break outer; // 目的地の場合
					dq.add(next);
				}
			}
			ArrayList<Integer> root = new ArrayList<>();
			int p = to;
			while (dest[p] != p) {
				root.add(p);
				p = dest[p];
			}
			for (int i = root.size() - 1; i >= 0; i--) path.add(root.get(i));
		}

		// 出力準備
		int size = path.size();
		out.print(1, size, size - 1).println();
		for (int i = 0; i < n; i++) out.print('0').printRepeat(" 0", n - 1).println();
		for (int p = 0; p < size - 1; p++) {
			int cur = path.get(p);
			int nij = path.get(p + 1);
			int s = p + 1;
			char d;
			if (cur - 1 == nij) {
				d = 'L';
			} else if (cur - n == nij) {
				d = 'U';
			} else if (cur + 1 == nij) {
				d = 'R';
			} else {
				d = 'D';
			}
			out.print(0, p, 0, s, d).println();
		}
	}

	// ------------------------ main() 関数 ------------------------
	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	// ------------------------ デバッグ用 ------------------------
	@SuppressWarnings("unused")
	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			System.err.println(deepToString(args));
		}
	}
}
