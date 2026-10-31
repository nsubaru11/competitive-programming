import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.util.*;

import lib.io.*;

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


	private static final int[] dij = new int[]{0, -1, 0, 1, -1, -1, 1, 1, -1, 0, 1, 0, -1, 1, 1, -1};


	// endregion

	// ------------------------ メインロジック ------------------------
	private static void solve() {
		int h = sc.nextInt();
		int w = sc.nextInt();
		int n = sc.nextInt();
		HashMap<Integer, Integer> hm = new HashMap<>(h * w);
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				hm.put(sc.nextInt(), i);
			}
		}
		int max = 0;
		int[] cnt = new int[h];
		for (int i = 0; i < n; i++) {
			int b = sc.nextInt();
			if (!hm.containsKey(b)) continue;
			cnt[hm.get(b)]++;
			max = max(max, cnt[hm.get(b)]);
		}
		out.println(max);
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
