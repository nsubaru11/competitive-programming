import lib.io.*;
import lib.search.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

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


	private static final int[] dij = new int[]{-1, -1, -1, 0, 0, 1, 1, 1, -1, 0, 1, -1, 1, -1, 0, 1};


	// endregion

	// ------------------------ メインロジック ------------------------
	private static void solve() {
		int n = sc.nextInt();
		int a = sc.nextInt();
		int b = sc.nextInt();
		int[] pa = new int[n + 1];
		int[] pb = new int[n + 1];
		for (int i = 0; i < n; i++) {
			if (sc.nextChar() == 'a') {
				pa[i + 1] = pa[i] + 1;
				pb[i + 1] = pb[i];
			} else {
				pb[i + 1] = pb[i] + 1;
				pa[i + 1] = pa[i];
			}
		}
		long ans = 0;
		for (int i = 0; i < n; i++) {
			int ai = ArrayBinarySearch.lowerBoundSearch(pa, i, n + 1, pa[i] + a);
			int bi = ArrayBinarySearch.upperBoundSearch(pb, i, n + 1, pb[i] + b - 1);
			if (ai < 0) break;
			if (bi < 0) bi = ~bi - 1;
			ans += max(0, bi - ai + 1);
		}
		out.println(ans);
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
