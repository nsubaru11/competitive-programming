import lib.io.*;
import lib.search.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public final class E {

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
		int[][] ab = sc.nextIntMat(n, 2);
		sort(ab, (u, v) -> u[0] != v[0] ? u[0] - v[0] : v[1] - u[1]);
		int[] dp = new int[n];
		int len = 0;
		for (int i = 0; i < n; i++) {
			final int v = ab[i][1];
			int pos = ArrayBinarySearch.upperBoundSearch(dp, 0, len, v);
			if (pos < 0) {
				pos = ~pos;
				dp[pos] = v;
			}
			if (pos == len) len++;
		}
		out.println(len);
	}

	// region < Utility Methods >
	private static boolean isValidRange(final int i, final int j, final int h, final int w) {
		return 0 <= i && i < h && 0 <= j && j < w;
	}

	private static long modPow(long a, long b, final long mod) {
		long ans = 1;
		for (a %= mod; b > 0; a = a * a % mod, b >>= 1) {
			if ((b & 1) == 1) ans = ans * a % mod;
		}
		return ans;
	}

	private static long floorLong(final long a, final long b) {
		return a < 0 ? (a - b + 1) / b : a / b;
	}

	private static int floorInt(final int a, final int b) {
		return a < 0 ? (a - b + 1) / b : a / b;
	}

	private static long ceilLong(final long a, final long b) {
		return a < 0 ? a / b : (a + b - 1) / b;
	}

	private static long ceilInt(final int a, final int b) {
		return a < 0 ? a / b : (a + b - 1) / b;
	}

	private static long LCM(final long x, final long y) {
		return x == 0 || y == 0 ? 0 : x * (y / GCD(x, y));
	}

	public static long GCD(long a, long b) {
		a = abs(a);
		b = abs(b);
		if (a == 0) return b;
		if (b == 0) return a;
		int commonShift = Long.numberOfTrailingZeros(a | b);
		a >>= Long.numberOfTrailingZeros(a);
		while (b != 0) {
			b >>= Long.numberOfTrailingZeros(b);
			if (a > b) {
				long tmp = a;
				a = b;
				b = tmp;
			}
			b -= a;
		}
		return a << commonShift;
	}

	// region < I/O & Debug >
	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			System.err.println(deepToString(args));
		}
	}

	// endregion
}
