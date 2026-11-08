import lib.io.*;

import static java.util.Arrays.*;

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
		char[][] c = new char[n][n];
		for (int i = 0; i < n; i++) {
			fill(c[i], '#');
		}
		for (int i = 1; i <= n / 2; i += 2) {
			for (int j = i; j < n - i; j++) {
				c[i][j] = '.';
				c[j][i] = '.';
				c[n - i - 1][j] = '.';
				c[j][n - i - 1] = '.';
			}
		}
		out.printChars(c);
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
