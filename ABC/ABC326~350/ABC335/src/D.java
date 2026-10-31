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
		int[][] ans = new int[n][n];
		for (int i = 0, k = 1; i < n / 2; i++) {
			for (int j = i; j < n - i; j++, k++) {
				ans[i][j] = k;
			}
			for (int j = i + 1; j < n - i; j++, k++) {
				ans[j][n - i - 1] = k;
			}
			for (int j = i + 1; j < n - i; j++, k++) {
				ans[n - i - 1][n - j - 1] = k;
			}
			for (int j = i + 1; j < n - i - 1; j++, k++) {
				ans[n - j - 1][i] = k;
			}
		}
		for (int i = 0; i < n; i++) {
			out.print(ans[i][0]);
			for (int j = 1; j < n; j++) {
				if (i == n / 2 && j == n / 2) out.print("", 'T');
				else out.print("", ans[i][j]);
			}
			out.println();
		}
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
