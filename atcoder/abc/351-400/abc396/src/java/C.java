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
		int m = sc.nextInt();
		int[] b = sc.nextSortedInt(n);
		int[] w = sc.nextSortedInt(m);
		long ans = 0;
		int i = 0;
		for (; i < n && i < m; i++) {
			if (b[n - i - 1] + w[m - i - 1] < 0) break;
			if (w[m - i - 1] < 0) break;
			ans += b[n - i - 1] + w[m - i - 1];
		}
		while (i < n && b[n - i - 1] >= 0) {
			ans += b[n - i++ - 1];
		}
		out.println(ans);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
