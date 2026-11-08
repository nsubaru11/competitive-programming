import java.util.*;

import lib.io.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public final class Main {

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

	private static final long t;
	private static final int N, M;
	private static final int[] target;

	static {
		t = System.currentTimeMillis();
		N = sc.nextInt();
		M = sc.nextInt();
		target = new int[M];
		for (int i = 0; i < M; i++) {
			target[i] = sc.nextInt() * N + sc.nextInt();
		}
	}

	private static void solve() {
		char[] ans1 = solve1();
		char[] ans2 = solve2();
		char[] ans = ans1.length < ans2.length ? ans1 : ans2;
		for (int i = 0; i <= 50; i++) {
			char[] temp = solve3(i);
			if (ans.length > temp.length) ans = temp;
		}
		out.println(ans);
	}

	private static char[] solve1() {
		int i = target[0] / N, j = target[0] % N;
		ArrayList<Character> alc = new ArrayList<>();
		for (int k = 1; k < M; k++) {
			int ti = target[k] / N, tj = target[k] % N;
			int di = abs(ti - i), dj = abs(tj - j);
			char lr = j < tj ? 'R' : 'L';
			while (dj-- > 0) {
				alc.add(lr);
			}
			char ud = i < ti ? 'D' : 'U';
			while (di-- > 0) {
				alc.add(ud);
			}
			i = ti;
			j = tj;
		}
		char[] ans = new char[alc.size()];
		for (int x = 0; x < ans.length; x++) ans[x] = alc.get(x);
		return ans;
	}

	private static char[] solve2() {
		int i = target[0] / N, j = target[0] % N;
		HashSet<Integer> ali = new HashSet<>();
		for (int k = 1; k < M; k++) {
			int ti = target[k] / N, tj = target[k] % N;
			ali.add(ti * N + j);
			ali.add(ti * N + tj);
			i = ti;
			j = tj;
		}
		boolean[] polish = new boolean[N * N];
		ArrayList<Character> alc = new ArrayList<>();
		i = target[0] / N;
		j = target[0] % N;
		for (int k = 1; k < M; k++) {
			int ti = target[k] / N, tj = target[k] % N;
			int dfi = abs(i - ti), dfj = abs(j - tj);
			for (int d = 0, ud = i < ti ? 3 : 1; d < dfi; ) {
				alc.add(op[ud]);
				do {
					i += di[ud];
					d++;
				} while (d < dfi && polish[i * N + j]);
				if (!ali.contains(i * N + j) && !polish[i * N + j]) {
					polish[i * N + j] = true;
					alc.add('P');
				}
			}

			for (int d = 0, lr = j < tj ? 2 : 0; d < dfj; ) {
				alc.add(op[lr]);
				do {
					j += dj[lr];
					d++;
				} while (d < dfj && polish[i * N + j]);
				if (!ali.contains(i * N + j) && !polish[i * N + j]) {
					polish[i * N + j] = true;
					alc.add('P');
				}
			}
		}
		char[] ans = new char[alc.size()];
		for (int x = 0; x < ans.length; x++) ans[x] = alc.get(x);
		return ans;
	}

	private static char[] solve3(int r) {
		int i = target[0] / N, j = target[0] % N;
		HashSet<Integer> ali = new HashSet<>();
		for (int k = 1; k < M; k++) {
			int ti = target[k] / N, tj = target[k] % N;
			ali.add(ti * N + j);
			ali.add(ti * N + tj);
			i = ti;
			j = tj;
		}
		Random random = new Random();
		boolean[] polish = new boolean[N * N];
		ArrayList<Character> alc = new ArrayList<>();
		i = target[0] / N;
		j = target[0] % N;
		for (int k = 1; k < M; k++) {
			int ti = target[k] / N, tj = target[k] % N;
			int dfi = abs(i - ti), dfj = abs(j - tj);
			for (int d = 0, ud = i < ti ? 3 : 1; d < dfi; ) {
				alc.add(op[ud]);
				do {
					i += di[ud];
					d++;
				} while (d < dfi && polish[i * N + j]);
				if (!ali.contains(i * N + j) && !polish[i * N + j] && random.nextFloat() * 100 <= r) {
					polish[i * N + j] = true;
					alc.add('P');
				}
			}

			for (int d = 0, lr = j < tj ? 2 : 0; d < dfj; ) {
				alc.add(op[lr]);
				do {
					j += dj[lr];
					d++;
				} while (d < dfj && polish[i * N + j]);
				if (!ali.contains(i * N + j) && !polish[i * N + j] && random.nextFloat() * 100 <= r) {
					polish[i * N + j] = true;
					alc.add('P');
				}
			}
		}
		char[] ans = new char[alc.size()];
		for (int x = 0; x < ans.length; x++) ans[x] = alc.get(x);
		return ans;
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

	private static int min(final int a, final int b) {
		return Math.min(a, b);
	}

	private static int min(final int a, final int b, final int c) {
		return Math.min(a, Math.min(b, c));
	}

	private static int min(int... a) {
		int len = a.length;
		int min = a[0];
		for (int i = 1; i < len; i++) if (min > a[i]) min = a[i];
		return min;
	}

	private static int max(final int a, final int b) {
		return Math.max(a, b);
	}

	private static int max(final int a, final int b, final int c) {
		return Math.max(a, Math.max(b, c));
	}

	private static int max(int... a) {
		int len = a.length;
		int max = a[0];
		for (int i = 1; i < len; i++) if (max < a[i]) max = a[i];
		return max;
	}

	private static long min(final long a, final long b) {
		return Math.min(a, b);
	}

	private static long min(final long a, final long b, final long c) {
		return Math.min(a, Math.min(b, c));
	}

	private static long min(long... a) {
		int len = a.length;
		long min = a[0];
		for (int i = 1; i < len; i++) if (min > a[i]) min = a[i];
		return min;
	}

	private static long max(final long a, final long b) {
		return Math.max(a, b);
	}

	private static long max(final long a, final long b, final long c) {
		return Math.max(a, Math.max(b, c));
	}

	private static long max(long... a) {
		int len = a.length;
		long max = a[0];
		for (int i = 1; i < len; i++) if (max < a[i]) max = a[i];
		return max;
	}

	private static double min(final double a, final double b) {
		return Math.min(a, b);
	}

	private static double min(final double a, final double b, final double c) {
		return Math.min(a, Math.min(b, c));
	}

	private static double min(double... a) {
		int len = a.length;
		double min = a[0];
		for (int i = 1; i < len; i++) if (min > a[i]) min = a[i];
		return min;
	}

	private static double max(final double a, final double b) {
		return Math.max(a, b);
	}

	private static double max(final double a, final double b, final double c) {
		return Math.max(a, Math.max(b, c));
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

	private static void compression(final int[] a, final int len) {
		final int[] b = copyOf(a, len);
		sort(b);
		int n = 1;
		for (int i = 1; i < len; i++) {
			if (b[i] == b[i - 1]) continue;
			b[n++] = b[i];
		}
		for (int i = 0; i < len; i++) {
			a[i] = binarySearch(b, 0, n, a[i]);
		}
	}

	private static void compression(final int[][] a, final int n, final int m) {
		final int len = n * m;
		final int[] b = new int[len];
		for (int i = 0, j = 0; i < n; i++, j += m) {
			System.arraycopy(a[i], 0, b, j, m);
		}
		sort(b);
		int nm = 1;
		for (int i = 1; i < len; i++) {
			if (b[i] == b[i - 1]) continue;
			b[nm++] = b[i];
		}
		for (int i = 0; i < n; i++) {
			int[] ai = a[i];
			for (int j = 0; j < m; j++) {
				ai[j] = binarySearch(b, 0, nm, ai[j]);
			}
		}
	}

	private static void compression(final int[][] a, final int n) {
		int len = 0;
		for (int i = 0; i < n; i++) len += a[i].length;
		final int[] b = new int[len];
		for (int i = 0, j = 0; i < n; i++) {
			int m = a[i].length;
			System.arraycopy(a[i], 0, b, j, m);
			j += m;
		}
		sort(b);
		int nm = 1;
		for (int i = 1; i < len; i++) {
			if (b[i] == b[i - 1]) continue;
			b[nm++] = b[i];
		}
		for (int i = 0; i < n; i++) {
			int[] ai = a[i];
			for (int j = 0, m = ai.length; j < m; j++) {
				ai[j] = binarySearch(b, 0, nm, ai[j]);
			}
		}
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
