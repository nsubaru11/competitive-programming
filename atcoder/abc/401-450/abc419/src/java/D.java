import lib.io.*;

import static java.util.Arrays.*;

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
		int m = sc.nextInt();
		char[] s = sc.nextChars();
		char[] t = sc.nextChars();
		int[] st = new int[n];
		while (m-- > 0) {
			int l = sc.nextInt() - 1;
			int r = sc.nextInt();
			st[l]++;
			if (r < n) st[r]--;
		}
		parallelPrefix(st, Integer::sum);
		for (int i = 0; i < n; i++) {
			out.print((st[i] & 1) == 0 ? s[i] : t[i]);
		}
		out.println();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
