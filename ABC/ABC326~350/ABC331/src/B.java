import static java.lang.Math.*;
import static java.util.Arrays.*;

import lib.io.*;

public final class B {

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
		int s = sc.nextInt();
		int m = sc.nextInt();
		int l = sc.nextInt();
		int[] dp = new int[n + 12];
		fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 0; i < n + 12; i++) {
			if (dp[i] == Integer.MAX_VALUE) continue;
			if (i + 6 < n + 12) dp[i + 6] = min(dp[i + 6], dp[i] + s);
			if (i + 8 < n + 12) dp[i + 8] = min(dp[i + 8], dp[i] + m);
			if (i + 12 < n + 12) dp[i + 12] = min(dp[i + 12], dp[i] + l);
		}
		int ans = Integer.MAX_VALUE;
		for (int i = n; i < n + 12; i++) {
			if (dp[i] == Integer.MAX_VALUE) continue;
			ans = min(ans, dp[i]);
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
