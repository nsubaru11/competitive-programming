import lib.io.*;

import static java.lang.Math.*;

public final class C2 {

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
		sc.nextInt();
		int k = sc.nextInt();
		if (k == 1) {
			out.println(0);
			return;
		}
		int[] socks = sc.nextInt(k);
		int pairs = k / 2;
		int[] suf = new int[pairs];
		suf[pairs - 1] = socks[k - 1] - socks[k - 2];
		for (int i = k - 3, s = pairs - 2; i > 0; i -= 2, s--) {
			suf[s] = socks[i] - socks[i - 1] + suf[s + 1];
		}
		int ans = suf[0], pre = 0;
		int p = 0;
		for (int i = 0; i < pairs - 1; i++, p += 2) {
			pre = socks[p + 1] - socks[p] + pre;
			ans = min(ans, pre + suf[i + 1]);
		}
		out.println(min(pre + socks[p + 1] - socks[p], ans));
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
