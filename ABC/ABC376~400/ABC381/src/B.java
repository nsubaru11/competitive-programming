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
		boolean[] c = new boolean[26];
		char[] s = sc.nextChars();
		int t = s.length;
		if (t % 2 == 1) {
			System.out.println("No");
			return;
		}
		for (int i = 0; i < t; i += 2) {
			if (s[i] != s[i + 1] || c[s[i] - 'a']) {
				System.out.println("No");
				return;
			} else {
				c[s[i] - 'a'] = true;
			}
		}
		System.out.println("Yes");
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
