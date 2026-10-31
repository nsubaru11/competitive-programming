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
	private static final FastPrinter out = new FastPrinter();
	// endregion

	private static void solve() {
		char[][] s = new char[8][8];
		int row = 0, column = 0;
		for (int i = 0; i < 8; i++) {
			boolean f = true;
			for (int j = 0; j < 8; j++) {
				s[i][j] = sc.nextChar();
				if (s[i][j] == '#') {
					f = false;
				}
			}
			if (f) row++;
		}
		for (int i = 0; i < 8; i++) {
			boolean f = true;
			for (int j = 0; j < 8; j++) {
				if (s[j][i] == '#') {
					f = false;
					break;
				}
			}
			if (f) column++;
		}
		System.out.println(row * column);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
