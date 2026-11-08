import lib.io.*;

import static java.util.Arrays.*;

public final class A {

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

	// region initialization
	// ------------------------ 定数 ------------------------


	private static final int[] dij = new int[]{0, -1, 0, 1, -1, -1, 1, 1, -1, 0, 1, 0, -1, 1, 1, -1};


	// endregion

	// ------------------------ メインロジック ------------------------
	private static void solve() {
		int r = sc.nextInt();
		int x = sc.nextInt();
		if (x == 1) {
			out.println(1600 <= r && r < 3000);
		} else {
			out.println(1200 <= r && r < 2400);
		}
	}

	// region main() and debug() methods
	// ------------------------ main() 関数 ------------------------
	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	// ------------------------ デバッグ用 ------------------------
	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			System.err.println(deepToString(args));
		}
	}
}
