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

	private static char[][] s;
	private static int h, w, d;

	private static void solve() {
		h = sc.nextInt();
		w = sc.nextInt();
		d = sc.nextInt();
		s = sc.nextCharMat(h, w);
		int ans = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (s[i][j] == '.') {
					for (int k = i; k < h; k++) {
						for (int l = (k == i) ? j + 1 : 0; l < w; l++) {
							if (s[k][l] == '.') {
								ans = max(ans, count(i, j, k, l));
							}
						}
					}
				}
			}
		}
		System.out.println(ans);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static int count(int i, int j, int k, int l) {
		int cnt = 0;
		for (int m = 0; m < h; m++) {
			for (int n = 0; n < w; n++) {
				if (s[m][n] == '.' && (abs(i - m) + abs(j - n) <= d || abs(k - m) + abs(l - n) <= d)) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}
