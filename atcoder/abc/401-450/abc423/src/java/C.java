import lib.io.*;

import static java.lang.Math.*;

public class C {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter(64);
	// endregion

	private static void solve() {
		int n = sc.nextInt();
		int r = sc.nextInt();
		int a = -1, b = -1;
		int open = 0;
		for (int i = 0; i < n; i++) {
			int l = sc.nextInt();
			if (l == 0) {
				open++;
				if (a == -1) a = i;
				b = i;
			}
		}
		if (open == 0) {
			out.println(0);
			return;
		}
		a = min(a, r);
		b = max(b, r - 1);
		int ans = b - a + 1;
		ans <<= 1;
		out.println(ans - open);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
