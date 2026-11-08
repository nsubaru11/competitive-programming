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
		int n = sc.nextInt();
		int m = 2000;
		int[][] cloud = new int[m + 2][m + 2];
		int[][] use = new int[m + 2][m + 2];
		for (int i = 0; i < n; i++) {
			int u = sc.nextInt();
			int d = sc.nextInt();
			int l = sc.nextInt();
			int r = sc.nextInt();
			cloud[u][l]++;
			cloud[u][r + 1]--;
			cloud[d + 1][l]--;
			cloud[d + 1][r + 1]++;
			use[u][l] += i;
			use[u][r + 1] -= i;
			use[d + 1][l] -= i;
			use[d + 1][r + 1] += i;
		}
		int[] cnt = new int[n];
		int sum = 0;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= m; j++) {
				cloud[i][j] += cloud[i][j - 1] + cloud[i - 1][j] - cloud[i - 1][j - 1];
				use[i][j] += use[i][j - 1] + use[i - 1][j] - use[i - 1][j - 1];
				if (cloud[i][j] > 0) {
					sum++;
					if (cloud[i][j] == 1) {
						cnt[use[i][j]]++;
					}
				}
			}
		}
		int k = m * m - sum;
		out.println(cnt, c -> k + c);
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
