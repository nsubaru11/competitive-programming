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
		char[] c = sc.nextChars(n * n);
		for (int i = 0; i < n / 2; i++) {
			int k = i % 4 + 1;
			while (k-- > 0) {
				for (int j = i; j < n - i - 1; j++) {
					int x1 = n - i - 1;
					int y1 = n - j - 1;
					char temp = c[y1 * n + i];
					c[y1 * n + i] = c[x1 * n + y1];
					c[x1 * n + y1] = c[j * n + x1];
					c[j * n + x1] = c[i * n + j];
					c[i * n + j] = temp;
				}
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				out.print(c[i * n + j]);
			}
			out.println();
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
