import java.util.*;

import lib.io.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public final class D {

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
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			int[] r = sc.nextInt(n);
			TreeMap<Integer, List<Integer>> tm = new TreeMap<>();
			for (int i = 0; i < n; i++) {
				tm.computeIfAbsent(r[i], _ -> new ArrayList<>()).add(i);
			}
			boolean[] add = new boolean[n];
			for (int i : tm.firstEntry().getValue()) {
				add[i] = true;
			}
			ArrayDeque<Integer> dq = new ArrayDeque<>(tm.firstEntry().getValue());
			int[] p = new int[n];
			fill(p, -1);
			while (!dq.isEmpty()) {
				int i = dq.poll();
				boolean left = i == 0 || p[i - 1] == -1;
				boolean right = i == n - 1 || p[i + 1] == -1;
				if (left && right) {
					p[i] = r[i];
					if (i != 0 && !add[i - 1]) {
						dq.add(i - 1);
						add[i - 1] = true;
					}
					if (i != n - 1 && !add[i + 1]) {
						dq.add(i + 1);
						add[i + 1] = true;
					}
				} else if (left) {
					if (abs(p[i + 1] - r[i]) <= 1) p[i] = r[i];
					else {
						p[i] = min(n, p[i + 1] + 1);
					}
					if (i != 0 && !add[i - 1]) {
						dq.add(i - 1);
						add[i - 1] = true;
					}
				} else if (right) {
					if (abs(p[i - 1] - r[i]) <= 1) p[i] = r[i];
					else {
						p[i] = min(n, p[i - 1] + 1);
					}
					if (i != n - 1 && !add[i + 1]) {
						dq.add(i + 1);
						add[i + 1] = true;
					}
				} else {
					if (abs(p[i + 1] - p[i - 1]) == 2) {
						p[i] = min(n, min(p[i - 1], p[i + 1]) + 1);
					} else {
						if (p[i - 1] == r[i] || p[i + 1] == r[i]) p[i] = r[i];
						else p[i] = min(n, min(p[i - 1], p[i + 1]) + 1);
					}
				}
				List<Integer> list = tm.remove(p[i] + 1);
				if (list == null) continue;
				for (int j : list) {
					if (!add[j]) {
						dq.add(j);
						add[j] = true;
					}
				}
			}
			long ans = 0;
			for (int i = 0; i < n; i++) {
				ans += r[i] - p[i];
			}
			debug(p);
			out.println(ans);
		}
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
	// endregion

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
