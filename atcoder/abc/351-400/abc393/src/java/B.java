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
		char[] c = sc.nextChars();
		int cnt = 0;
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 'A') {
				for (int j = i + 1; j < c.length; j++) {
					if (c[j] == 'B') {
						int k = j + j - i;
						if (k >= c.length) break;
						if (c[k] == 'C') cnt++;
					}
				}
			}
		}
		out.println(cnt);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
