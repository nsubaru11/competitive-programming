import static java.lang.Math.*;
import static java.util.Arrays.*;

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
	private static final FastPrinter out = new FastPrinter();
	// endregion

	private static void solve() {
		int n = sc.nextInt();
		int[] ab = new int[n << 1];
		int s = 0;
		for (int i = 0; i < n; i++) {
			int idx = i << 1;
			ab[idx] = sc.nextInt();
			ab[idx + 1] = sc.nextInt();
			s += ab[idx + 1];
		}
		if (s % 3 != 0) {
			out.println(-1);
			return;
		}
		int INF = Integer.MAX_VALUE;
		int m = s / 3;
		int size = (m + 1) * (m + 1);
		int[] dp = new int[size];
		fill(dp, INF);
		dp[0] = 0;
		for (int i = 1; i <= n; i++) {
			int idx = (i - 1) << 1;
			int a = ab[idx];
			int b = ab[idx + 1];
			for (int x = m, index = size - 1; x >= 0; x--) {
				for (int y = m; y >= 0; y--, index--) {
					int s1 = 0, s2 = 0, s3 = 0;
					if (a == 1) {
						s2 = s3 = 1;
					} else if (a == 2) {
						s1 = s3 = 1;
					} else {
						s1 = s2 = 1;
					}
					if (dp[index] != INF) {
						dp[index] += s3;
					}
					int k = (x - b) * (m + 1) + y;
					if (x >= b && dp[k] != INF) {
						dp[index] = min(dp[index], dp[k] + s1);
					}
					k = index - b;
					if (y >= b && dp[k] != INF) {
						dp[index] = min(dp[index], dp[k] + s2);
					}
				}
			}
		}
		int ans = dp[size - 1];
		out.println(ans == INF ? -1 : ans);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
