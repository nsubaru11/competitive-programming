import static java.lang.Math.*;

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
		int n = sc.nextInt();
		double ans = 0;
		int a = 0, b = 0;
		while (n-- > 0) {
			int c = sc.nextInt();
			int d = sc.nextInt();
			ans += sqrt(pow(a - c, 2) + pow(b - d, 2));
			a = c;
			b = d;
		}
		ans += sqrt(pow(a, 2) + pow(b, 2));
		out.println(ans, 6);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
