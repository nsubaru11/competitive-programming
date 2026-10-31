import lib.io.*;

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
		int h = sc.nextInt();
		int w = sc.nextInt();
		char[][] s = sc.nextCharMat(h, w);
		for (int i = 0; i < h; i++)
			for (int j = 0; j < w; j++)
				if (s[i][j] == '#' && !check(s, i, j, h, w)) {
					out.println("No");
					return;
				}
		out.println("Yes");
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static boolean check(char[][] a, int i, int j, int h, int w) {
		int cnt = 0;
		if (i - 1 >= 0 && a[i - 1][j] == '#') cnt++;
		if (i + 1 < h && a[i + 1][j] == '#') cnt++;
		if (j - 1 >= 0 && a[i][j - 1] == '#') cnt++;
		if (j + 1 < w && a[i][j + 1] == '#') cnt++;
		return cnt == 2 || cnt == 4;
	}
}
