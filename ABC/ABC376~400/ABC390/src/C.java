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
	private static final FastPrinter out = new FastPrinter(true);
	// endregion

	private static void solve() {
		int h = sc.nextInt();
		int w = sc.nextInt();
		char[][] grid = sc.nextCharMat(h, w);
		int a = h - 1, b = 0;
		int c = w - 1, d = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (grid[i][j] == '#') {
					a = min(a, i);
					b = max(b, i);
					c = min(c, j);
					d = max(d, j);
				}
			}
		}
		for (int i = a; i < b + 1; i++) {
			for (int j = c; j < d + 1; j++) {
				if (grid[i][j] == '.') {
					out.println("No");
					return;
				}
			}
		}
		out.println("Yes");
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
