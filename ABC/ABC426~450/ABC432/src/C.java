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
		int n = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int[] a = new int[n];
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) min = min(min, a[i] = sc.nextInt());
		long cnt = 0;
		for (int i = 0; i < n; i++) {
			if (a[i] == min) {
				cnt += min;
				continue;
			}
			long k = (long) (a[i] - min) * y;
			if (k % (y - x) != 0) {
				out.println(-1);
				return;
			}
			k /= y - x;
			if (a[i] < k) {
				out.println(-1);
				return;
			}
			cnt += a[i] - k;
		}
		out.println(cnt);
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
