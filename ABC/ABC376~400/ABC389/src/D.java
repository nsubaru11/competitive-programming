import static java.lang.Math.*;

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

	//	private static final FastPrinter out = new FastPrinter();

	private static void solve() {
		long n = sc.nextInt();
		long ans = 0;
		for (double y = 0.5; y < n; y++) {
			int x = (int) (sqrt(n * n - y * y) + 0.5);
			if (x == 0) break;
			ans += (x - 1) * 4L;
		}
		System.out.println(ans + 1);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
