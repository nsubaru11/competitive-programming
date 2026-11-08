import lib.io.*;

import static java.lang.Math.*;

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
		char[][] s = sc.nextCharMat(n, n);
		char[][] t = sc.nextCharMat(n, n);
		int cnt = 0;
		int cnt2 = 1;
		int cnt3 = 2;
		int cnt4 = 3;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (s[i][j] != t[i][j]) {
					cnt++;
				}
				if (s[n - j - 1][i] != t[i][j]) {
					cnt2++;
				}
				if (s[n - i - 1][n - j - 1] != t[i][j]) {
					cnt3++;
				}
				if (s[j][n - i - 1] != t[i][j]) {
					cnt4++;
				}
			}
		}
		out.println(min(cnt, min(cnt2, min(cnt3, cnt4))));
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
