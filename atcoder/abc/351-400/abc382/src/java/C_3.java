import lib.io.*;
import lib.search.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public class C_3 {

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
		int m = sc.nextInt();
		int[] c = new int[n];
		setAll(c, i -> min(sc.nextInt(), i > 0 ? c[i - 1] : Integer.MAX_VALUE));
		while (m-- > 0) {
			int b = sc.nextInt();
			int ans = BinarySearch.lowerBound(0, n, idx -> {
				int i = (int) idx;
				return b < c[i] ? -1 : b == c[i] ? 0 : 1;
			});
			ans = ans < 0 ? ~ans : ans;
			out.println(ans >= n ? -1 : ans + 1);
		}
		out.flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
