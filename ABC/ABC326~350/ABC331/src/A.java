import lib.io.*;

public final class A {

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
		int M = sc.nextInt();
		int D = sc.nextInt();
		int y = sc.nextInt();
		int m = sc.nextInt();
		int d = sc.nextInt();
		d++;
		if (d > D) {
			d -= D;
			m++;
		}
		if (m > M) {
			m -= M;
			y++;
		}
		out.print(y, m, d).println();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
