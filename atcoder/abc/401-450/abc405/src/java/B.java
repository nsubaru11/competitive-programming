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


	private static final int[] dij = new int[]{0, -1, 0, 1, -1, -1, 1, 1, -1, 0, 1, 0, -1, 1, 1, -1};


	// endregion

	// ------------------------ メインロジック ------------------------
	private static void solve() {
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] a = new int[n];
		HashMap<Integer, Integer> map = new HashMap<>(n);
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
			map.put(a[i], map.getOrDefault(a[i], 0) + 1);
		}
		for (int i = 1; i <= n; i++) {
			if (map.size() < m) {
				out.println(i - 1);
				return;
			}
			int k = a[n - i];
			if (map.containsKey(k)) {
				map.put(k, map.get(k) - 1);
				if (map.get(k) == 0) map.remove(k);
			}
		}
		out.println(n);
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
