import lib.io.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

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
		int[][] a = new int[m][];
		for (int i = 0; i < m; i++) {
			int k = sc.nextInt();
			a[i] = new int[k];
			setAll(a[i], j -> sc.nextInt() - 1);
		}
		int[] day = sc.nextIntInverseMapping(n);
		int[] ans = new int[n];
		for (int i = 0; i < m; i++) {
			int last = -1;
			for (int j = 0; j < a[i].length; j++) {
				last = max(last, day[a[i][j]]);
			}
			out.println();
			ans[last]++;
		}
		out.println(ans[0]);
		for (int i = 1; i < n; i++) {
			ans[i] += ans[i - 1];
			out.println(ans[i]);
		}
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
