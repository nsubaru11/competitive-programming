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
		int l = sc.nextInt();
		int[] d = sc.nextInt(n - 1);
		if (l % 3 != 0) {
			out.println(0);
			return;
		}
		int[] p = new int[l];
		int prev = 0;
		p[0] = 1;
		for (int i = 0; i < n - 1; i++) {
			prev = (prev + d[i]) % l;
			p[prev]++;
		}
		long cnt = 0;
		int dl = l / 3;
		for (int i = 0; i < dl; i++) {
			int di = i + dl, d2i = i + dl * 2;
			cnt += (long) p[i] * p[di] * p[d2i];
		}
		out.println(cnt);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
