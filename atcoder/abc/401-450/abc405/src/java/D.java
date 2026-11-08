import java.util.*;

import lib.io.*;

import static java.util.Arrays.*;

public final class D {

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
		int h = sc.nextInt();
		int w = sc.nextInt();
		int hw = h * w;
		char[] grid = sc.nextChars(hw);
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		for (int i = 0; i < hw; i++) if (grid[i] == 'E') dq.add(i);
		while (!dq.isEmpty()) {
			int p = dq.poll();
			int i = p / w, j = p % w;
			for (int d = 0; d < 4; d++) {
				int ni = dij[d] + i, nj = dij[d + 8] + j;
				int nij = ni * w + nj;
				if (0 <= ni && ni < h && 0 <= nj && nj < w && grid[nij] == '.') {
					switch (d) {
						case 0:
							grid[nij] = '>';
							break;
						case 1:
							grid[nij] = 'v';
							break;
						case 2:
							grid[nij] = '<';
							break;
						case 3:
							grid[nij] = '^';
							break;
					}
					dq.add(nij);
				}
			}
		}
		for (int i = 0; i < hw; i += w) {
			out.printChars(grid, i, i + w).println();
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
