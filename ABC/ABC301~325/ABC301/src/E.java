import static java.lang.Math.*;
import static java.util.Arrays.*;

import lib.graph.*;
import lib.io.*;

public final class E {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter(64);
	// endregion

	private static void solve() {
		int h = sc.nextInt();
		int w = sc.nextInt();
		int hw = h * w;
		int t = sc.nextInt();
		char[] grid = sc.nextChars(hw);

		int oCnt = 0;
		for (char c : grid) if (c == 'o') oCnt++;
		int s = 0, g = 0;
		int[] o = new int[oCnt];
		for (int i = 0, j = 0; i < hw; i++) {
			char c = grid[i];
			if (c == 'o') o[j++] = i;
			else if (c == 'S') s = i;
			else if (c == 'G') g = i;
		}

		UndirectedGraph d = new UndirectedGraph(hw, hw * 2);
		for (int j = 0, ij = hw - w; j < w - 1; j++, ij++) {
			if (grid[ij] == '#') continue;
			if (grid[ij + 1] != '#') d.add(ij, ij + 1);
		}
		for (int i = 0, ij = 0; i < h - 1; i++, ij++) {
			for (int j = 0; j < w - 1; j++, ij++) {
				if (grid[ij] == '#') continue;
				if (grid[ij + 1] != '#') d.add(ij, ij + 1);
				if (grid[ij + w] != '#') d.add(ij, ij + w);
			}
			if (grid[ij] == '#') continue;
			if (grid[ij + w] != '#') d.add(ij, ij + w);
		}

		int[] distS = bfsDist(d, s);
		if (distS[g] == -1 || distS[g] > t) {
			out.println(-1);
			return;
		}
		int[] distG = bfsDist(d, g);
		int[][] distO = new int[oCnt][];
		for (int i = 0; i < oCnt; i++) distO[i] = bfsDist(d, o[i]);

		int[][] dp = new int[1 << oCnt][oCnt];
		for (int i = 0; i < oCnt; i++) {
			int v = distS[o[i]];
			if (v == -1) continue;
			dp[1 << i][i] = v;
		}
		for (int i = 1; i < 1 << oCnt; i++) {
			for (int j = 0, cur = i; cur > 0; j++, cur >>= 1) {
				if (dp[i][j] == 0) continue;
				for (int k = 0; k < oCnt; k++) {
					if ((i >> k & 1) == 1) continue;
					int idx = i | (1 << k);
					int v = distO[j][o[k]];
					if (v == -1) continue;
					if (dp[idx][k] == 0 || dp[idx][k] > dp[i][j] + v) {
						dp[idx][k] = dp[i][j] + v;
					}
				}
			}
		}

		int ans = 0;
		for (int i = 0; i < 1 << oCnt; i++) {
			for (int j = 0; j < oCnt; j++) {
				if (dp[i][j] == 0) continue;
				int v = distG[o[j]];
				if (v == -1) continue;
				if (dp[i][j] + v <= t) ans = max(ans, Integer.bitCount(i));
			}
		}
		out.println(ans);
	}

	// region < Utility Methods >
	private static boolean isValidRange(final int i, final int from, final int to) {
		return ((i - from) | (to - 1 - i)) >= 0;
	}

	private static boolean isValidRange(final int i, final int j, final int h, final int w) {
		return ((i | j | (h - 1 - i) | (w - 1 - j)) >>> 31) == 0;
	}

	private static void swap(final char[] a, final int i, final int j) {
		final char tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private static void swap(final int[] a, final int i, final int j) {
		final int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private static void swap(final long[] a, final int i, final int j) {
		final long tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private static boolean chmin(final char[] a, final int i, final char v) {
		if (a[i] <= v) return false;
		a[i] = v;
		return true;
	}

	private static boolean chmin(final int[] a, final int i, final int v) {
		if (a[i] <= v) return false;
		a[i] = v;
		return true;
	}

	private static boolean chmin(final long[] a, final int i, final long v) {
		if (a[i] <= v) return false;
		a[i] = v;
		return true;
	}

	private static boolean chmax(final char[] a, final int i, final char v) {
		if (a[i] >= v) return false;
		a[i] = v;
		return true;
	}

	private static boolean chmax(final int[] a, final int i, final int v) {
		if (a[i] >= v) return false;
		a[i] = v;
		return true;
	}

	private static boolean chmax(final long[] a, final int i, final long v) {
		if (a[i] >= v) return false;
		a[i] = v;
		return true;
	}

	private static int min(int... a) {
		int len = a.length;
		int min = a[0];
		for (int i = 1; i < len; i++) if (min > a[i]) min = a[i];
		return min;
	}

	private static int max(int... a) {
		int len = a.length;
		int max = a[0];
		for (int i = 1; i < len; i++) if (max < a[i]) max = a[i];
		return max;
	}

	private static long min(long... a) {
		int len = a.length;
		long min = a[0];
		for (int i = 1; i < len; i++) if (min > a[i]) min = a[i];
		return min;
	}

	private static long max(long... a) {
		int len = a.length;
		long max = a[0];
		for (int i = 1; i < len; i++) if (max < a[i]) max = a[i];
		return max;
	}

	private static double min(double... a) {
		int len = a.length;
		double min = a[0];
		for (int i = 1; i < len; i++) if (min > a[i]) min = a[i];
		return min;
	}

	private static double max(double... a) {
		int len = a.length;
		double max = a[0];
		for (int i = 1; i < len; i++) if (max < a[i]) max = a[i];
		return max;
	}

	private static long lModPow(long a, long b, final long mod) {
		if (b == 0) return 1;
		long ans = 1;
		for (a %= mod; b > 1; b >>= 1) {
			if ((b & 1) == 1) ans = ans * a % mod;
			a = a * a % mod;
		}
		return ans * a % mod;
	}

	private static int iModPow(int a, int b, final int mod) {
		if (b == 0) return 1;
		int ans = 1;
		for (a %= mod; b > 1; b >>= 1) {
			if ((b & 1) == 1) ans = (int) ((long) ans * a % mod);
			a = (int) ((long) a * a % mod);
		}
		return (int) ((long) ans * a % mod);
	}

	private static long lcmLong(final long x, final long y) {
		return x == 0 || y == 0 ? 0 : x / gcdLong(x, y) * y;
	}

	private static int lcmInt(final int x, final int y) {
		return x == 0 || y == 0 ? 0 : x / gcdInt(x, y) * y;
	}

	private static long gcdLong(long a, long b) {
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

	private static int gcdInt(int a, int b) {
		a = abs(a);
		b = abs(b);
		if (a == 0) return b;
		if (b == 0) return a;
		int commonShift = Integer.numberOfTrailingZeros(a | b);
		a >>= Integer.numberOfTrailingZeros(a);
		while (b != 0) {
			b >>= Integer.numberOfTrailingZeros(b);
			if (a > b) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			b -= a;
		}
		return a << commonShift;
	}
	// endregion

	// region < I/O & Debug >
	private static int[] bfsDist(final UndirectedGraph g, final int s) {
		final long[] d = BFS.dist(g, s);
		final int[] res = new int[d.length];
		for (int i = 0; i < d.length; i++) res[i] = d[i] == Long.MAX_VALUE ? -1 : (int) d[i];
		return res;
	}

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
