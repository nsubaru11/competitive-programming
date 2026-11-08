import java.util.*;

import lib.io.*;

import static java.util.Arrays.*;

public final class E {

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
		int[] table = new int[1_000_001];
		for (int i = 2; i < 1_000_001; i++) {
			if (table[i] != 0) continue;
			for (int j = i; j < 1_000_001; j += i) {
				table[j]++;
			}
		}
		List<Long> num400 = new ArrayList<>();
		for (int i = 0; i < 1_000_001; i++) {
			if (table[i] == 2) num400.add((long) i * i);
		}
		while (q-- > 0) {
			long a = sc.nextLong();
			int c = Collections.binarySearch(num400, a);
			if (c < 0) c = ~c - 1;
			out.println(num400.get(c));
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
