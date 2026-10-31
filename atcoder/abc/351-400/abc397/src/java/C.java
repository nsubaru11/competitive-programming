import static java.lang.Math.*;

import java.util.*;

import lib.io.*;

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
		int[] a = sc.nextInt(n);
		int[] b = new int[n];
		int[] c = new int[n];
		HashSet<Integer> hs = new HashSet<>();
		b[0] = 1;
		hs.add(a[0]);
		for (int i = 1; i < n; i++) {
			if (!hs.contains(a[i])) {
				b[i] += b[i - 1] + 1;
				hs.add(a[i]);
			} else {
				b[i] = b[i - 1];
			}
		}
		hs.clear();
		hs.add(a[n - 1]);
		c[n - 1] = 1;
		for (int i = n - 2; i >= 0; i--) {
			if (!hs.contains(a[i])) {
				c[i] += c[i + 1] + 1;
				hs.add(a[i]);
			} else {
				c[i] = c[i + 1];
			}
		}
		int ans = 0;
		for (int i = 0; i < n - 1; i++) {
			ans = max(ans, b[i] + c[i + 1]);
		}
		out.println(ans);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
