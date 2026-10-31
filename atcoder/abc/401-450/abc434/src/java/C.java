import static java.lang.Math.*;
import static java.util.Arrays.*;

import lib.io.*;

public final class C {

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
		int q = sc.nextInt();
		final int MAX = 1000000000, MIN = 1;
		outer:
		while (q-- > 0) {
			int n = sc.nextInt();
			int h = sc.nextInt();
			int[][] tlu = sc.nextIntMat(n, 3);
			int ct = 0, cl = h, cu = h;
			for (int i = 0; i < n; i++) {
				int t = tlu[i][0];
				int l = tlu[i][1];
				int u = tlu[i][2];
				int dt = t - ct;
				int minL = max(cl - dt, MIN);
				int maxU = (int) min((long) cu + dt, MAX);
				if (u < minL || maxU < l) {
					out.println(false);
					continue outer;
				}
				ct = t;
				cl = max(l, minL);
				cu = min(u, maxU);
			}
			out.println(true);
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
