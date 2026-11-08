import lib.io.*;

import static java.lang.Math.*;

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
		int s1 = sc.nextChar() - 'A';
		int s2 = sc.nextChar() - 'A';
		int t1 = sc.nextChar() - 'A';
		int t2 = sc.nextChar() - 'A';
		int ds = abs(s1 - s2);
		int dt = abs(t1 - t2);
		if (ds == 4) ds = 1;
		else if (ds == 3) ds = 2;
		if (dt == 4) dt = 1;
		else if (dt == 3) dt = 2;
		out.println(ds == dt);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
