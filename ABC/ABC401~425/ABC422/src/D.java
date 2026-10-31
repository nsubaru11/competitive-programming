import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.util.*;

import lib.io.*;

public class D {

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
		int k = sc.nextInt();
		int m = 1 << n;
		long[] a = new long[m];
		fill(a, k / m);
		int l = k % m;
		for (int i = 0, j = 0; i < l; i += 2, j = j + 4 > m ? 2 : j + 4) {
			a[j]++;
			if (i + 1 < l) a[m - j - 1]++;
		}
		//		out.println(stream(a).sum());
		long[] ans = Arrays.copyOf(a, m);
		long u = 0;
		while (m > 1) {
			long max = Long.MIN_VALUE;
			long min = Long.MAX_VALUE;
			for (int i = 0; i < m; i += 2) {
				max = Long.max(max, Long.max(a[i], a[i + 1]));
				min = Long.min(min, Long.min(a[i], a[i + 1]));
				a[i / 2] = a[i] + a[i + 1];
			}
			u = max(u, max - min);
			m >>= 1;
		}
		out.println(u);
		out.println(ans, ' ');
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
