import java.util.*;

import lib.io.*;

import static java.util.Arrays.*;

public final class B {

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
		int m = sc.nextInt();
		HashSet<BitSet> hs = new HashSet<>();
		char[][] s = sc.nextCharMat(n, n);
		for (int i = 0; i <= n - m; i++) {
			for (int j = 0; j <= n - m; j++) {
				BitSet bs = new BitSet(m * m);
				for (int k = 0; k < m; k++) {
					for (int l = 0; l < m; l++) {
						if (s[i + k][j + l] == '#') bs.set(k * m + l);
					}
				}
				hs.add(bs);
			}
		}
		out.println(hs.size());
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
