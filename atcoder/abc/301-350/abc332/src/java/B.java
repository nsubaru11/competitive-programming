import lib.io.*;

import static java.lang.Math.*;

public final class B {

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
		int k = sc.nextInt();
		int G = sc.nextInt();
		int M = sc.nextInt();
		int g = 0, m = 0;
		while (k-- > 0) {
			if (g == G) g = 0;
			else if (m == 0) m = M;
			else {
				int m2 = m;
				m = max(0, m - (G - g));
				g = min(G, m2);
			}
		}
		out.print(g, m).println();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
