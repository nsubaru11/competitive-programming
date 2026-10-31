import lib.io.*;

public final class B {

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
		char c = sc.nextChar();
		while (c == 'A') {
			if (sc.hasNext()) c = sc.nextChar();
			else break;
		}
		while (c == 'B') {
			if (sc.hasNext()) c = sc.nextChar();
			else break;
			if (c == 'A') {
				out.println(false);
				return;
			}
		}
		while (c == 'C') {
			if (sc.hasNext()) c = sc.nextChar();
			else break;
			if (c == 'B' || c == 'A') {
				out.println(false);
				return;
			}
		}
		out.println(!sc.hasNext());
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
