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
		int n = sc.nextInt();
		Pair[] a = new Pair[n];
		for (int i = 0; i < n; i++) {
			a[i] = new Pair(sc.nextInt(), sc.nextInt());
		}
		int q = sc.nextInt();
		while (q-- > 0) {
			int t = sc.nextInt() - 1;
			int d = sc.nextInt();
			d += ((a[t].r - d) % a[t].q + a[t].q) % a[t].q;
			out.println(d);
		}
		out.flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static class Pair {
		int q;
		int r;

		Pair(int q, int r) {
			this.q = q;
			this.r = r;
		}
	}
}
