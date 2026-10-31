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

	private static char[][] s;
	private static int h, w;

	private static void solve() {
		h = sc.nextInt();
		w = sc.nextInt();
		int k = sc.nextInt();
		s = sc.nextCharMat(h, w);
		int cnt = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (s[i][j] == '.') {
					cnt += count(i, j, k, new boolean[h][w]);
				}
			}
		}
		System.out.println(cnt);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static int count(int i, int j, int k, boolean[][] used) {
		if (used[i][j]) {
			return 0;
		}
		if (k == 0) {
			return 1;
		}
		int cnt = 0;
		used[i][j] = true;
		if (i + 1 < h && s[i + 1][j] == '.') {
			cnt += count(i + 1, j, k - 1, used);
		}
		if (i - 1 >= 0 && s[i - 1][j] == '.') {
			cnt += count(i - 1, j, k - 1, used);
		}
		if (j + 1 < w && s[i][j + 1] == '.') {
			cnt += count(i, j + 1, k - 1, used);
		}
		if (j - 1 >= 0 && s[i][j - 1] == '.') {
			cnt += count(i, j - 1, k - 1, used);
		}
		used[i][j] = false;
		return cnt;
	}
}
