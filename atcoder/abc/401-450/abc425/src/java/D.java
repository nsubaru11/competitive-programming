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
		int h = sc.nextInt();
		int w = sc.nextInt();
		int hw = h * w;
		char[] grid = sc.nextChars(hw);
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		boolean[] visited = new boolean[hw];
		for (int i = 0; i < hw; i++) {
			if (grid[i] == '.') continue;
			dq.add(i);
			visited[i] = true;
		}
		int ans = 0;
		while (!dq.isEmpty()) {
			ArrayDeque<Integer> dq2 = new ArrayDeque<>();
			ans += dq.size();
			for (int from : dq) {
				int i = from / w, j = from % w;
				for (int d = 0; d < 4; d++) {
					int ni = i + di[d], nj = j + dj[d];
					int to = ni * w + nj;
					if (!isValidRange(ni, nj, h, w) || visited[to] || grid[to] == '#') continue;
					int cnt = 0;
					for (int d2 = 0; d2 < 4; d2++) {
						int ni2 = ni + di[d2], nj2 = nj + dj[d2];
						int to2 = ni2 * w + nj2;
						if (!isValidRange(ni2, nj2, h, w)) continue;
						if (grid[to2] == '#') cnt++;
					}
					visited[to] = true;
					if (cnt == 1) dq2.add(to);
				}
			}
			for (int i : dq2) grid[i] = '#';
			dq = dq2;
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
