import static java.lang.Math.*;

import lib.io.*;

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
		int n = sc.nextInt();
		int i_max = Integer.MIN_VALUE;
		int j_max = Integer.MIN_VALUE;
		int i_min = Integer.MAX_VALUE;
		int j_min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int r = sc.nextInt();
			int c = sc.nextInt();
			i_max = max(i_max, r);
			i_min = min(i_min, r);
			j_max = max(j_max, c);
			j_min = min(j_min, c);
		}
		out.println(max((i_max - i_min + 1) / 2, (j_max - j_min + 1) / 2));
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
