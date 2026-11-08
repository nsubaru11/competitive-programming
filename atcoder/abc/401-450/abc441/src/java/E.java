import lib.ds.segmenttree.*;
import lib.io.*;

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
		char[] s = sc.nextChars();
		int[] ps = new int[n];
		int[] cnt = new int[2 * n + 1];
		cnt[n]++;
		ps[0] = n;
		for (int i = 0; i < n; i++) {
			if (i > 0) ps[i] += ps[i - 1];
			if (s[i] == 'A') ps[i]++;
			else if (s[i] == 'B') ps[i]--;
			cnt[ps[i]]++;
		}
		parallelPrefix(cnt, Integer::sum);
		long ans = 0;
		IntSegmentTree segmentTree = new IntSegmentTree(2 * n + 1, Integer::sum, 0);
		ans += (cnt[2 * n] - cnt[n]) - segmentTree.query(n + 1, 2 * n + 1);
		segmentTree.set(n, 1);
		for (int i = 0; i < n; i++) {
			int x = cnt[2 * n] - cnt[ps[i]];
			int y = segmentTree.query(ps[i] + 1, 2 * n + 1);
			ans += x - y;
			segmentTree.add(ps[i], 1);
		}
		out.println(ans);
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
	// endregion

	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			System.err.println(deepToString(args));
		}
	}

	// endregion
}
