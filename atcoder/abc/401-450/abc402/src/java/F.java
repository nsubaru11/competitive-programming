import lib.io.*;

import static java.lang.Math.*;

public class F {

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

	private static int n, m;
	private static int[] a;
	private static long ans;

	private static void solve() {
		n = sc.nextInt();
		m = sc.nextInt();
		a = sc.nextInt(n * n);
		search(0, 0);
		out.println(ans);
	}

	private static void search(int idx, int score) {
		score = (score * (10 % m)) % m + a[idx] % m;
		score %= m;
		if (idx == n * n - 1) {
			ans = max(ans, score);
			return;
		}
		int i = idx / n;
		int j = idx % n;
		if (i < n - 1) search(idx + n, score);
		if (j < n - 1) search(idx + 1, score);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
