import static java.util.Arrays.*;

import lib.io.*;

public final class C {

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
		int[] a = new int[n];
		fill(a, -1);
		int s = -1;
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt() - 1;
			if (x == -2) s = i;
			else a[x] = i;
		}
		out.print(s + 1);
		while (a[s] != -1) {
			s = a[s];
			out.print(' ').print(s + 1);
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
