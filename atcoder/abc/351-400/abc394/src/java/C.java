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
		char[] s = sc.nextChars();
		for (int i = 0; i + 1 < s.length; i++) {
			if (s[i] == 'W' && s[i + 1] == 'A') {
				s[i] = 'A';
				s[i + 1] = 'C';
				i -= 2;
				i = max(i, -1);
			}
		}
		out.printChars(s);
		out.println();
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
