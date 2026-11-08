import lib.io.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public class D2 {

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
		int[] d = new int[m + 2];
		fill(d, m + 1);
		d[m + 1] = m;
		while (n-- > 0) {
			int l = sc.nextInt();
			int r = sc.nextInt();
			d[l] = min(d[l], r - 1);
		}
		long ans = 0;
		for (int i = m; i >= 1; i--) {
			d[i] = min(d[i], d[i + 1]);
			ans += d[i] - i + 1;
		}
		System.out.println(ans);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
