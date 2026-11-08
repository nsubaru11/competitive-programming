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
		int[] pyramid = new int[n];
		pyramid[0] = 1;
		sc.nextInt();
		for (int i = 1; i < n; i++) {
			int a = sc.nextInt();
			pyramid[i] = min(pyramid[i - 1] + 1, a);
		}
		int ans = 1;
		pyramid[n - 1] = 1;
		for (int i = n - 2; i >= 0; i--) {
			pyramid[i] = min(pyramid[i], pyramid[i + 1] + 1);
			ans = max(ans, pyramid[i]);
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
