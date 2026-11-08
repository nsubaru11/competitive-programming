import java.util.*;
import java.util.stream.*;

import lib.io.*;

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
		int h = sc.nextInt(), w = sc.nextInt(), k = sc.nextInt();
		int hw = h * w;
		boolean[] s = sc.nextBoolean(hw, '.');
		HashSet<Integer> hsI = new HashSet<>(hw);
		HashSet<Integer> hsJ = new HashSet<>(hw);
		for (int i = 0, ij = 0; i < h; i++) {
			for (int j = 0; j < w; j++, ij++) {
				if (!s[ij]) {
					hsI.add(i);
					hsJ.add(j);
				}
			}
		}
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		int[] visitedCnt = new int[hw];
		boolean[] ans = new boolean[hw];
		fill(visitedCnt, -1);
		for (int i = 0; i < h; i++) {
			if (hsI.contains(i)) continue;
			for (int j = 0; j < w; j++) {
				if (hsJ.contains(j)) continue;
				int ij = i * w + j;
				dq.add(ij);
				visitedCnt[ij] = 0;
				ans[ij] = true;
			}
		}
		while (!dq.isEmpty()) {
			int u = dq.poll();
			if (visitedCnt[u] == k) continue;
			int i = u / w, j = u % w;
			for (int d = 0; d < 4; d++) {
				int ni = i + di[d], nj = j + dj[d];
				if (!isValidRange(ni, nj, h, w)) continue;
				int v = ni * w + nj;
				if (!s[v]) continue;
				if (visitedCnt[u] + 1 <= k && visitedCnt[v] == -1) {
					dq.add(v);
					ans[v] = true;
					visitedCnt[v] = visitedCnt[u] + 1;
				}
			}
		}
		int cnt = 0;
		for (int i = 0; i < hw; i++) {
			if (ans[i]) cnt++;
		}
		out.println(cnt);
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

	private static int diff(final int a, final int b) {
		return a > b ? a - b : b - a;
	}

	private static long diff(final long a, final long b) {
		return a > b ? a - b : b - a;
	}

	private static double diff(final double a, final double b) {
		return a > b ? a - b : b - a;
	}
	// endregion

	// region < main & debug >
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
			else System.err.println(stream(args).map(D::stringify).collect(Collectors.joining("\n", "\n", "")));
		}
	}

	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			if (args == null) System.err.println("null");
			else if (args.getClass().getComponentType().isArray()) System.err.println(stringify(args));
			else System.err.println(stream(args).map(D::stringify).collect(Collectors.joining(", ", "", "")));
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
