import lib.io.*;

import static java.lang.Math.*;

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
		int m = sc.nextInt();
		int cnt1 = 0, cnt2 = 0, ans = 0;
		while (n-- > 0) {
			char c = sc.nextChar();
			if (c == '0') {
				ans = max(ans, max(0, cnt1 - m) + cnt2);
				cnt1 = cnt2 = 0;
			} else if (c == '1') {
				cnt1++;
			} else if (c == '2') {
				cnt2++;
			}
		}
		ans = max(ans, max(0, cnt1 - m) + cnt2);
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
