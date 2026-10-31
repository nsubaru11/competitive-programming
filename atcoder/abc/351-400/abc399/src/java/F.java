import lib.io.*;

public class F {

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
		int[] a = sc.nextInt(n);
		int ans = 0;
		int mod = 998244353;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < n; j++) {
				int temp = 0;
				for (int d = 0; d < i && j + d < n; d++) {
					temp = (temp + a[j + d]) % mod;
				}
				ans += (int) modPow(temp, k, mod);
			}
		}
		out.println(ans);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	/**
	 * a ^ b % mod を求めます。
	 *
	 * @param a   底
	 * @param b   指数
	 * @param mod 割る値(素数)
	 * @return a ^ b % mod
	 */
	public static long modPow(long a, long b, long mod) {
		long ans = 1;
		b %= mod - 1;
		for (; b > 0; a = a * a % mod, b >>= 1) {
			if ((b & 1) == 1) ans = ans * a % mod;
		}
		return ans;
	}
}
