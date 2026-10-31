import static java.util.Arrays.*;

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
	private static final FastPrinter out = new FastPrinter(512);
	// endregion

	private static void solve() {
		int n = sc.nextInt();
		String[] s = new String[51];
		fill(s, "");
		for (int i = 0; i < n; i++) {
			String str = sc.next();
			s[str.length()] = str;
		}
		for (int i = 0; i < 51; i++) {
			out.print(s[i]);
		}
		out.println();
		out.flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
