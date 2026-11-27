import java.io.*;
import java.math.*;
import java.util.*;
import java.util.Map.*;
import java.util.function.*;
import java.util.stream.*;

import lib.ds.map.*;
import lib.io.*;
import lib.math.*;
import lib.util.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import static java.util.stream.Gatherers.*;

public final class F {

	// region < Runtime Setup & Constants >
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
		int n = sc.nextInt(), m = sc.nextInt();
		int[][] cv = sc.nextIntMat(n, 2);
		sort(cv, comparingInt(i -> i[0]));
		long sum = 0;
		for (int i = 0; i < n; i++) sum += cv[i][1];
		long[][] dp = new long[n + 1][m + 1];
		fill2D(dp, -1);
		dp[0][0] = 0;
		int prev = m;
		long[] ans = new long[m + 1];

		for (int i = n - 1; i >= 0; i--) {
			int c = cv[i][0], v = cv[i][1];
			int diff = prev - c;
			chmax(ans, c, sum);
			for (int j = 1; j <= n; j++) {
				for (int k = m; k >= 0; k--) {
					int x = floorDiv(m - k, j);
					chmax(ans, max(prev - x, c), dp[j][k] + sum);
					if (k + diff * j <= m) chmax(dp[j], k + diff * j, dp[j][k]);
					if (diff != 0) dp[j][k] = -1;
				}
			}
			for (int j = n - 1; j >= 0; j--) {
				for (int k = m; k >= 0; k--) {
					if (dp[j][k] != -1) chmax(dp[j + 1], k, dp[j][k] + v);
				}
			}
			prev = c;
			sum -= v;
		}

		for (int j = 1; j <= n; j++) {
			for (int k = m; k >= 0; k--) {
				int x = floorDiv(m - k, j);
				chmax(ans, max(prev - x, 0), dp[j][k]);
				if (k + prev * j <= m) chmax(dp[j], k + prev * j, dp[j][k]);
				if (prev != 0) dp[j][k] = -1;
			}
		}
		parallelPrefix(ans, Math::max);
		out.println(ans);
	}

	// region < Utilities >
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

	private static void fill2D(final char[][] a, final char v) {
		for (final char[] ai : a) fill(ai, v);
	}

	private static void fill2D(final int[][] a, final int v) {
		for (final int[] ai : a) fill(ai, v);
	}

	private static void fill2D(final long[][] a, final long v) {
		for (final long[] ai : a) fill(ai, v);
	}

	private static void fill2D(final double[][] a, final double v) {
		for (final double[] ai : a) fill(ai, v);
	}

	private static void fill3D(final char[][][] a, final char v) {
		for (final char[][] ai : a) fill2D(ai, v);
	}

	private static void fill3D(final int[][][] a, final int v) {
		for (final int[][] ai : a) fill2D(ai, v);
	}

	private static void fill3D(final long[][][] a, final long v) {
		for (final long[][] ai : a) fill2D(ai, v);
	}

	private static void fill3D(final double[][][] a, final double v) {
		for (final double[][] ai : a) fill2D(ai, v);
	}
	// endregion

	// region < Entry Point & Debug >
	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static void debugln(final Object... args) {
		if (DEBUG) {
			out.flush();
			if (args == null) System.err.println("null");
			else if (args.getClass().getComponentType().isArray()) System.err.println(stringify(args));
			else System.err.println(stream(args).map(F::stringify).collect(Collectors.joining("\n", "\n", "")));
		}
	}

	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			if (args == null) System.err.println("null");
			else if (args.getClass().getComponentType().isArray()) System.err.println(stringify(args));
			else System.err.println(stream(args).map(F::stringify).collect(Collectors.joining(", ", "", "")));
		}
	}

	private static String stringify(final Object obj) {
		return switch (obj) {
			case null -> "null";
			case int[][] arr -> "\n" + stream(arr).map(Arrays::toString).collect(Collectors.joining("\n"));
			case long[][] arr -> "\n" + stream(arr).map(Arrays::toString).collect(Collectors.joining("\n"));
			case char[][] arr -> "\n" + stream(arr).map(String::valueOf).collect(Collectors.joining("\n"));
			case Object[][] arr -> "\n" + stream(arr).map(Arrays::deepToString).collect(Collectors.joining("\n"));
			case int[] arr -> Arrays.toString(arr);
			case long[] arr -> Arrays.toString(arr);
			case double[] arr -> Arrays.toString(arr);
			case char[] arr -> Arrays.toString(arr);
			case boolean[] arr -> Arrays.toString(arr);
			case Object[] arr -> deepToString(arr);
			case Iterable<?> it -> {
				final StringJoiner sj = new StringJoiner(", ", "[", "]");
				for (final Object e : it) sj.add(stringify(e));
				yield sj.toString();
			}
			default -> obj.toString();
		};
	}
	// endregion
}
