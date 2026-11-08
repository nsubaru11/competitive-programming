import lib.io.*;

import static java.lang.Math.*;

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

	private static void solve() {
		int n = sc.nextInt();
		int k = sc.nextInt();
		int d = sc.nextInt();
		int[] a = sc.nextInt(n);
		long[][] dp = new long[k][d];
		for (int i = 0; i < k; i++) for (int j = 0; j < d; j++) dp[i][j] = -1;
		dp[0][a[0] % d] = a[0];
		for (int x = 1; x < n; x++) {
			int b = a[x], r = b % d;
			for (int y = k - 1; y > 0; y--) {
				for (int z = 0; z < d; z++) {
					long t = dp[y - 1][(z - r + d) % d];
					if (t != -1) dp[y][z] = max(dp[y][z], t + b);
				}
			}
			dp[0][r] = max(dp[0][r], b);
		}
		out.println(dp[k - 1][0]);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
