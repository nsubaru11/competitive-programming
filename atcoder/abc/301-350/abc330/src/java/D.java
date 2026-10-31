import lib.io.*;

public final class D {

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
		int[] row = new int[n];
		int[] col = new int[n];
		char[] grid = new char[n * n];
		for (int i = 0, k = 0; i < n; i++) {
			for (int j = 0; j < n; j++, k++) {
				char c = sc.nextChar();
				grid[k] = c;
				if (c == 'o') {
					col[i]++;
					row[j]++;
				}
			}
		}
		long ans = 0;
		for (int i = 0, k = 0; i < n; i++) {
			for (int j = 0; j < n; j++, k++) {
				if (grid[k] == 'o') {
					ans += (long) (row[j] - 1) * (col[i] - 1);
				}
			}
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
