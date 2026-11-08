import lib.io.*;

public class D {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		long n = sc.nextLong();
		int len = String.valueOf(n).length();
		int mod = 998244353;
		long r = (long) modPow(10L, len, mod);
		long ans = (n % mod * ((long) modPow(r, n, mod) - 1L)) % mod * (long) modPow((r - 1L), mod - 2L, mod) % mod;
		System.out.println(ans);
	}

	private static double modPow(long a, long b, long mod) {
		long ans = 1;
		boolean f = b < 0;
		if (f) b = -b;
		b %= mod - 1;
		for (; b > 0; a = a * a % mod, b >>= 1) {
			if ((b & 1) == 1)
				ans = ans * a % mod;
		}
		return f ? (double) 1 / ans : ans;
	}
}
