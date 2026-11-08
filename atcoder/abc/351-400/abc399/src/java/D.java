import lib.io.*;

import static java.lang.Math.*;

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

	private static void solve() {
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			int max = n << 1;
			int[] a = new int[max];
			int[] lastIndex = new int[n];
			for (int i = 0; i < max; i++) {
				a[i] = sc.nextInt() - 1;
				lastIndex[a[i]] = i;
			}
			int cnt = 0;
			for (int i = 0; i < max - 1; i++) {
				if (a[i] == a[i + 1]) {
					i++;
					continue;
				}
				int idx1 = lastIndex[a[i]];
				int idx2 = lastIndex[a[i + 1]];
				if (abs(idx1 - idx2) == 1 && idx2 - (i + 1) > 1) {
					cnt++;
				}
			}
			out.println(cnt);
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
