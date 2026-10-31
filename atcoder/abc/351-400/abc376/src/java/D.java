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
		int a = sc.nextInt(), b = sc.nextInt();
		char[] s = sc.nextChars();
		int cnt = 0;
		int cntb = 0;
		for (int i = 0; i < n; i++) {
			if (cnt < a + b) {
				if (s[i] == 'a') {
					out.println("Yes");
					cnt++;
				} else if (cntb < b && s[i] == 'b') {
					out.println("Yes");
					cnt++;
					cntb++;
				} else {
					out.println("No");
				}
			} else {
				out.println("No");
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
