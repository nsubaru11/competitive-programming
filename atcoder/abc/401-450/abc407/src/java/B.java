import java.util.stream.*;

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
	private static final FastPrinter out = new FastPrinter();
	// endregion

	private static void solve() {
		int x = sc.nextInt();
		int y = sc.nextInt();
		int cnt = (int) IntStream.range(0, 36).filter(i -> i % 6 + i / 6 + 2 >= x || abs(i % 6 - i / 6) >= y).count();
		out.println(cnt / 36.0, 9);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
