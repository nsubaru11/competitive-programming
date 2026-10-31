import static java.util.Arrays.*;

import lib.io.*;
import lib.search.*;

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

	private static void solve() {
		int n = sc.nextInt();
		long[] a = new long[n];
		long[] b = new long[n];
		for (int i = 0; i < n; i++) {
			a[i] = b[i] = sc.nextInt();
		}
		sort(a);
		parallelPrefix(a, Long::sum);
		for (int i = 0; i < n; i++) {
			long k = b[i];
			int j = BinarySearch.upperBoundSearch(0, n, l -> {
				int idx = (int) l;
				return Long.signum(idx > 0 ? a[idx] - a[idx - 1] - k : a[idx] - k);
			});
			b[i] = a[n - 1] - a[j];
		}
		out.println(b, ' ');
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
