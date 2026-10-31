import lib.io.*;

public final class E {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter(true);
	// endregion

	private static void solve() {
		int n = sc.nextInt();
		int m = 32 - Integer.numberOfLeadingZeros(n - 1);
		out.println(m);
		for (int i = 1; i <= m; i++) {
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) != 0) {
					out.print(' ').print(j + 1);
				}
			}
			out.println();
		}
		int k = (1 << m) - 1;
		char[] s = sc.nextChars(m);
		for (int i = 0; i < m; i++) {
			if (s[i] == '1') k &= i + 1;
		}
		int x = 32 - Integer.numberOfLeadingZeros(k - 1);
		out.println(x + 1);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
