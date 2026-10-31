import lib.io.*;

public class E {

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
		int mod = 998244353;
		long n = sc.nextLong() % mod;
		int ans = (int) ((long) n - n * (n + 1) / 2);
		out.println(ans);
	}

	public static long modPow(long n, long k, long mod) {
		long res = 1;
		n %= mod;
		k %= mod;
		for (; k > 0; k >>= 1, n = n * n % mod) {
			if ((k & 1) == 1) res = res * n % mod;
		}
		return res;
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
