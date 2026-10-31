import static java.lang.Math.*;

import lib.io.*;

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
		int[] a = sc.nextInt(n);
		int[] p = new int[n + 1];
		for (int i = 0; i < n; i++) {
			a[i] += p[i];
			p[i + 1] += p[i] + 1;
			p[min(n, i + a[i] + 1)]--;
			a[i] = max(0, a[i] - (n - i - 1));
		}
		out.print(a).flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
