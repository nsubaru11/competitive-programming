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
	private static final FastPrinter out = new FastPrinter(true);
	// endregion

	private static void solve() {
		int n = sc.nextInt();
		int m = sc.nextInt();
		char[][] s = sc.nextCharMat(n, n);
		char[][] t = sc.nextCharMat(m, m);
		for (int a = 0; a < n - m + 1; a++) {
			for (int b = 0; b < n - m + 1; b++) {
				boolean flag = true;
				for (int i = 0; i < m; i++) {
					for (int j = 0; j < m; j++) {
						flag &= s[a + i][b + j] == t[i][j];
					}
				}
				if (flag) out.print(a + 1, b + 1).println();
			}
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
