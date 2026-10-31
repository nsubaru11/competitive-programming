import static java.lang.Math.*;

import lib.io.*;

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
		int k = sc.nextInt();
		int len = 2 * n - k;
		int[] socks = new int[len];
		k--;
		for (int i = 0, j = 0, b = sc.nextInt() - 1; i < n; i++) {
			if (b != -1) {
				if (i == b) {
					socks[j++] = b;
					if (k-- > 0) b = sc.nextInt() - 1;
				} else {
					socks[j++] = i;
					socks[j++] = i;
				}
			}
		}
		int pairs = len / 2;
		int[] pre = new int[pairs];
		int[] suf = new int[pairs];
		pre[0] = socks[1] - socks[0];
		suf[pairs - 1] = socks[len - 1] - socks[len - 2];
		for (int i = 2, j = 1, j2 = pairs - 2; i < len - 1; i += 2) {
			pre[j] = socks[i + 1] - socks[i] + pre[j - 1];
			suf[j2] = socks[len - i - 1] - socks[len - i - 2] + suf[j2 + 1];
			j++;
			j2--;
		}
		long ans = min(pre[pairs - 1], suf[0]);
		for (int i = 0; i < pairs - 1; i++) {
			ans = min(ans, pre[i] + suf[i + 1]);
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
