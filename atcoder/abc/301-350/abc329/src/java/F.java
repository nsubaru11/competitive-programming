import java.util.*;

import lib.io.*;

import static java.util.Arrays.*;

public final class F {

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
		int q = sc.nextInt();
		HashSet<Integer>[] hs = new HashSet[n];
		for (int i = 0; i < n; i++) {
			int c = sc.nextInt();
			hs[i] = new HashSet<>();
			hs[i].add(c);
		}
		while (q-- > 0) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			HashSet<Integer> hsa = hs[a];
			HashSet<Integer> hsb = hs[b];
			if (hsa.size() < hsb.size()) {
				hsb.addAll(hsa);
				hsa.clear();
				out.println(hsb.size());
			} else {
				hsa.addAll(hsb);
				hsb.clear();
				out.println(hsa.size());
				hs[a] = hsb;
				hs[b] = hsa;
			}
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
