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
		if (n < k) {
			out.println(1);
			return;
		}
		int mod = 1000000000;
		int[] a = new int[n + 1];
		a[0] = 1;
		for (int i = 1; i < k; i++) {
			a[i] = mod(a[i - 1] + 1, mod);
		}
		for (int i = k; i < n + 1; i++) {
			if (i - k - 1 >= 0) {
				a[i] = mod(a[i - 1] - a[i - k - 1], mod);
			} else {
				a[i] = mod(a[i - 1], mod);
			}
			if (i < n) {
				a[i] = mod(a[i] + a[i - 1], mod);
			}
		}
		out.println(a[n]);
	}

	private static int mod(int n, int mod) {
		return n >= 0 ? n % mod : (n % mod + mod) % mod;
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
