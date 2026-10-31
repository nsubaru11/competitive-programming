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
		String s = sc.next();
		int len = s.length();
		double ans = 0;
		for (int i = 0; i < len; i++) {
			for (int j = len - 1; j > i + 1; j--) {
				int x = 0;
				if (s.charAt(i) == s.charAt(j) && s.charAt(i) == 't') {
					for (int k = i; k < j + 1; k++) {
						if (s.charAt(k) == 't') x++;
					}
					ans = max(ans, (double) (x - 2) / (j - i - 1));
				}
			}
		}
		out.println(ans, 10);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
