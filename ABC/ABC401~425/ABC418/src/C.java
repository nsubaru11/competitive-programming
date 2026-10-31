import static java.util.Arrays.*;

import lib.io.*;
import lib.search.*;

public class C {

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

	private static void solve() {
		int n = sc.nextInt();
		int q = sc.nextInt();
		long[] a = sc.nextSortedLong(n);
		parallelPrefix(a, Long::sum);
		while (q-- > 0) {
			int b = sc.nextInt();
			int k = BinarySearch.lowerBoundSearch(0, n, i -> {
				int idx = (int) i;
				long v = idx > 0 ? a[idx] - a[idx - 1] : a[idx];
				return Long.signum(v - b);
			}); // b より小さい個数
			if (k == ~n) {
				out.println(-1);
				continue;
			}
			if (k < 0) k = ~k;
			long x = (long) (b - 1) * (n - k) + (k > 0 ? a[k - 1] : 0) + 1;
			out.println(x);
		}
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
