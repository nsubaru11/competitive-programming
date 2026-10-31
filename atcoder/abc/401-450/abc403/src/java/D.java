import static java.lang.Math.*;

import java.util.*;

import lib.io.*;

public class D {

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
		int d = sc.nextInt();
		HashMap<Integer, Integer> map = new HashMap<>(d);
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			map.put(a, map.getOrDefault(a, 0) + 1);
		}
		if (d == 0) {
			out.println(n - map.size());
			return;
		}
		int ans = 0;
		int[][] dp = new int[d][2];
		for (int i = 0; i < n; i++) {
			Integer a = map.get(i);
			if (a == null) continue;
			int e = i % d;
			int dp0 = dp[e][0];
			int dp1 = dp[e][1];
			dp[e][0] = dp1;
			dp[e][1] = min(dp0, dp1) + a;
			if (!map.containsKey(e + d)) {
				ans += min(dp[e][0], dp[e][1]);
				dp[e][0] = dp[e][1] = 0;
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
