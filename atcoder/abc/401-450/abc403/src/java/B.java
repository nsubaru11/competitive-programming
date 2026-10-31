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
		char[] t = sc.nextChars();
		char[] u = sc.nextChars();
		for (int i = 0; i < t.length; i++) {
			boolean flag = true;
			if (i + u.length > t.length) break;
			for (int j = 0; j < u.length; j++) {
				if (t[i + j] != '?' && t[i + j] != u[j]) {
					flag = false;
					break;
				}
			}
			if (flag) {
				out.println("Yes");
				return;
			}
		}
		out.println("No");
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
