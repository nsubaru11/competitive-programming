import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.util.*;

import lib.io.*;

public final class E {

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

	// region initialization
	// ------------------------ 定数 ------------------------


	private static final int[] dij = new int[]{0, -1, 0, 1, -1, -1, 1, 1, -1, 0, 1, 0, -1, 1, 1, -1};


	// endregion

	// ------------------------ メインロジック ------------------------
	private static void solve() {
		int t = sc.nextInt();
		int[][] dir = {{0, 1, 2, 3}, {1, 0, 3, 2}, {3, 2, 1, 0}}; // A, B, C
		while (t-- > 0) {
			int h = sc.nextInt();
			int w = sc.nextInt();
			int hw = h * w;
			char[][] mirror = sc.nextCharMat(h, w);
			ArrayDeque<int[]> dq = new ArrayDeque<>(hw);
			dq.add(new int[]{0, 0, 2, 0}); // i, j, d, cost
			int ans = 0;
			int[][][] visited = new int[h][w][4];
			for (int[][] v : visited) {
				for (int[] d : v) {
					fill(d, Integer.MAX_VALUE);
				}
			}
			visited[0][0][2] = 0;
			while (!dq.isEmpty()) {
				int[] p = dq.pollFirst();
				int i = p[0], j = p[1], d = p[2], cost = p[3];
				if (i == h - 1 && j == w) {
					ans = cost;
					break;
				}
				int m = mirror[i][j] - 'A';
				for (int nd = 0; nd < 4; nd++) {
					if (abs(d - nd) == 2) continue;
					int ni = i + dij[nd], nj = j + dij[nd + 8];
					boolean flag = dir[m][d] == nd;
					int nc = cost + (flag ? 0 : 1);
					if (ni == h - 1 && nj == w) {
						if (flag) dq.addFirst(new int[]{ni, nj, nd, nc});
						else dq.addLast(new int[]{ni, nj, nd, nc});
					} else if (0 <= ni && ni < h && 0 <= nj && nj < w) {
						if (visited[ni][nj][nd] <= nc) continue;
						if (flag) dq.addFirst(new int[]{ni, nj, nd, nc});
						else dq.addLast(new int[]{ni, nj, nd, nc});
						visited[ni][nj][nd] = nc;
					}
				}
			}
			out.println(ans);
		}
	}

	// region main() and debug() methods
	// ------------------------ main() 関数 ------------------------
	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	// ------------------------ デバッグ用 ------------------------
	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			System.err.println(deepToString(args));
		}
	}
}
