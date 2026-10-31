import static java.util.Arrays.*;

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
		int m = sc.nextInt();
		int[][] xa = new int[m][2];
		for (int i = 0; i < m; i++) {
			xa[i][0] = sc.nextInt();
		}
		for (int i = 0; i < m; i++) {
			xa[i][1] = sc.nextInt();
		}
		sort(xa, (o1, o2) -> o2[0] - o1[0]);
		long ans = 0;
		for (int i = 0; i < m; i++) {
			int x = xa[i][0];
			int a = xa[i][1];
			if (n - x == a + 1) {
				ans += (long) (n - x) * (n - x + 1) / 2;
				n = x - 1;
			} else if (n - x >= a) {
				ans += (long) ((long) 2 * (n - x) - a + 1) * a / 2;
				n -= a;
			} else {
				System.out.println(-1);
				return;
			}
			if (n == 0) {
				break;
			}
		}
		System.out.println(n > 0 ? -1 : ans);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
