import java.util.*;

import lib.io.*;

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


	private static final int[] dij = new int[]{0, -1, 0, 1, -1, -1, 1, 1, -1, 0, 1, 0, -1, 1, 1, -1};


	// endregion

	// ------------------------ メインロジック ------------------------
	private static void solve() {
		int n = sc.nextInt();
		int m = sc.nextInt();
		HashSet<Long> hs = new HashSet<>();
		while (m-- > 0) {
			int r = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			long s1 = (long) r * n + c;
			long s2 = s1 + 1;
			long s3 = s1 + n;
			long s4 = s3 + 1;
			if (hs.contains(s1) || hs.contains(s2) || hs.contains(s3) || hs.contains(s4)) continue;
			hs.add(s1);
			hs.add(s2);
			hs.add(s3);
			hs.add(s4);
		}
		out.println(hs.size() / 4);
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
