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


	private static final int[] dij = new int[]{0, -1, 0, 1, -1, -1, 1, 1, -1, 0, 1, 0, -1, 1, 1, -1};


	// endregion

	// ------------------------ メインロジック ------------------------
	private static void solve() {
		int n = sc.nextInt();
		int m = sc.nextInt();
		long[] a = sc.nextSortedLong(n);
		long[] b = sc.nextSortedLong(m);
		long sum = 0;
		for (long b2 : b) sum += b2;
		long ans = 0;
		long pSum = 0;
		int i = 0;
		for (long v : a) {
			while (i < m && b[i] < v) {
				pSum += b[i];
				i++;
			}
			long left = (v * i - pSum) % MOD;
			long right = (sum - pSum - v * (m - i)) % MOD;
			ans += right + left;
			ans %= MOD;
			debug(left, right, ans);
		}
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
