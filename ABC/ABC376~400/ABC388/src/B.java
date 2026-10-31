import static java.lang.Math.*;

import lib.io.*;

public class B {

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
		int[][] tl = sc.nextIntMat(n, 2);
		for (int i = 1; i <= d; i++) {
			int max = 0;
			for (int j = 0; j < n; j++) {
				max = max(max, tl[j][0] * (tl[j][1] + i));
			}
			out.println(max);
		}
		out.flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
