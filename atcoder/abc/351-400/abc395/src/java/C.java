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
		int[] idx = new int[1000000];
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++) {
			int a = sc.nextInt() - 1;
			if (idx[a] > 0) {
				min = min(min, i - idx[a] + 1);
			}
			idx[a] = i;
		}
		out.println(min == Integer.MAX_VALUE ? -1 : min);
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
