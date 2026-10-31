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

	private static int n, m, count;

	private static void solve() {
		n = sc.nextInt();
		m = sc.nextInt();
		solve(new int[n], 0);
		System.out.println(count);
		out.flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static boolean solve(int[] a, int k) {
		int i = 1;
		int cnt = 0;
		while (true) {
			if (k == 0) {
				a[k] = i++;
				if (!solve(a, k + 1)) {
					return cnt > 0;
				}
			} else {
				a[k] = a[k - 1] + 9 + i++;
				if (k < n - 1 && !solve(a, k + 1)) {
					return cnt > 0;
				}
			}
			if (k == n - 1) {
				if (a[k] > m) {
					return cnt > 0;
				} else {
					out.print(a).println();
					count++;
				}
			}
			cnt++;
		}
	}
}
