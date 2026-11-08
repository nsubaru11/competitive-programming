import lib.io.*;

import static java.lang.Math.*;

public class B {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter(64);
	// endregion

	private static void solve() {
		int n = sc.nextInt();
		int[] l = sc.nextInt(n);
		int a = 0, b = n;
		while (a <= b) {
			if (l[a] == l[b - 1] && l[a] == 1) break;
			if (l[a] == 0) a++;
			if (l[b - 1] == 0) b--;
		}
		out.println(max(b - a - 1, 0));
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
