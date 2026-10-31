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
		int k = sc.nextInt();
		char[] s = sc.nextChars();
		int i = 0;
		while (i < n) {
			if (k == 1) {
				StringBuilder sb = new StringBuilder();
				while (i < n && s[i] == '0') {
					sb.append("0");
					i++;
				}
				i = print(s, i, 1, n);
				out.print(sb);
			} else {
				i = print(s, i, 0, n);
				i = print(s, i, 1, n);
			}
			k--;
		}
		out.println().flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static int print(char[] s, int i, int k, int n) {
		while (i < n && s[i] - '0' == k) {
			out.print(k);
			i++;
		}
		return i;
	}
}
