import static java.lang.Math.*;
import static java.util.Arrays.*;

import lib.io.*;

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


	private static final int[] dij = new int[]{-1, -1, -1, 0, 0, 1, 1, 1, -1, 0, 1, -1, 1, -1, 0, 1};


	// endregion

	// ------------------------ メインロジック ------------------------
	private static void solve() {
		int n = sc.nextInt();
		int[][] whb = new int[n][3];
		int sum = 0;
		for (int i = 0; i < n; i++) {
			whb[i][0] = sc.nextInt();
			whb[i][1] = sc.nextInt();
			whb[i][2] = sc.nextInt();
			sum += whb[i][0];
		}
		long[] dp = new long[sum / 2 + 1];
		fill(dp, Long.MIN_VALUE);
		dp[0] = 0;
		int curSum = 0;
		for (int i = 0; i < n; i++) {
			int w = whb[i][0];
			int h = whb[i][1];
			int b = whb[i][2];
			for (int j = min(curSum + w, sum / 2); j >= w; j--) dp[j] = max(dp[j] + b, dp[j - w] + h);
			for (int j = 0; j < min(w, sum / 2 + 1); j++) dp[j] = dp[j] + b;
			curSum += w;
		}
		long ans = 0;
		for (long v : dp) if (ans < v) ans = v;
		out.println(ans);
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
