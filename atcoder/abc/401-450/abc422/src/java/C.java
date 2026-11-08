import lib.io.*;

import static java.lang.Math.*;

public class C {

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
		int t = sc.nextInt();
		while (t-- > 0) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			int minAC = min(a, c);
			long k = a + b + c - minAC * 2L;
			if (k >= minAC) {
				out.println(minAC);
			} else {
				long temp = minAC - k;
				out.println(k + ((temp + minAC * 2L) % 3 * 2) / 3 * 2);
			}
		}
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
