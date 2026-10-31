import static java.lang.Math.*;

import lib.io.*;

public class E {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter(true);
	// endregion

	private static void solve() {
		int n = sc.nextInt();
		int x = sc.nextInt();
		int[][] dp = new int[3][x + 1];
		for (int i = 1; i <= n; i++) {
			int v = sc.nextInt() - 1;
			int a = sc.nextInt();
			int c = sc.nextInt();
			for (int j = x; j >= c; j--) {
				dp[v][j] = max(dp[v][j - c] + a, dp[v][j]);
			}
		}
		int ans = 0;
		for (int i = 0; i <= x; i++) {
			for (int j = 0; j <= x - i; j++) {
				int k = x - i - j;
				ans = max(ans, min(dp[0][i], min(dp[1][j], dp[2][k])));
			}
		}
		out.println(ans);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
