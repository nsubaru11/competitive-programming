import static java.lang.Math.*;

import lib.io.*;

public class B {

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
		int l = 1, r = 2;
		int ans = 0;
		while (q-- > 0) {
			char h = sc.nextChar();
			int t = sc.nextInt();
			if (h == 'L') {
				if (r < t) {
					if (l < r) {
						ans += n - t + l;
					} else {
						ans += abs(t - l);
					}
				} else {
					if (l < r) {
						ans += abs(t - l);
					} else {
						ans += n - l + t;
					}
				}
				l = t;
			} else {
				if (l < t) {
					if (r < l) {
						ans += n - t + r;
					} else {
						ans += abs(t - r);
					}
				} else {
					if (r < l) {
						ans += abs(t - r);
					} else {
						ans += n - r + t;
					}
				}
				r = t;
			}
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
