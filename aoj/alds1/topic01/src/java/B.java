import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public final class B {

	// region < Constants & Globals >
	private static final boolean DEBUG = false;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out, false);
	// endregion

	private static void solve() {
		long a = sc.nextLong(), b = sc.nextLong();
		out.println(gcd(a, b));
	}

	/**
	 * binary GCD により2つの {@code long} の最大公約数を返します。
	 */
	public static long gcd(long a, long b) {
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

	// region < main & debug >
	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			sc.close();
			out.close();
		}
	}

	private static void debugln(final Object... args) {
		if (DEBUG) {
			out.flush();
			if (args == null) System.err.println("null");
			else if (args.getClass().getComponentType().isArray()) System.err.println(stringify(args));
			else System.err.println(stream(args).map(B::stringify).collect(Collectors.joining("\n", "\n", "")));
		}
	}

	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			if (args == null) System.err.println("null");
			else if (args.getClass().getComponentType().isArray()) System.err.println(stringify(args));
			else System.err.println(stream(args).map(B::stringify).collect(Collectors.joining(", ", "", "")));
		}
	}

	private static String stringify(final Object obj) {
		if (obj == null) return "null";
		else if (obj instanceof int[][]) {
			int[][] arr = (int[][]) obj;
			return "\n" + stream(arr).map(Arrays::toString).collect(Collectors.joining("\n"));
		} else if (obj instanceof long[][]) {
			long[][] arr = (long[][]) obj;
			return "\n" + stream(arr).map(Arrays::toString).collect(Collectors.joining("\n"));
		} else if (obj instanceof char[][]) {
			char[][] arr = (char[][]) obj;
			return "\n" + stream(arr).map(String::valueOf).collect(Collectors.joining("\n"));
		} else if (obj instanceof Object[][]) {
			Object[][] arr = (Object[][]) obj;
			return "\n" + stream(arr).map(Arrays::deepToString).collect(Collectors.joining("\n"));
		} else if (obj instanceof int[]) {
			int[] arr = (int[]) obj;
			return Arrays.toString(arr);
		} else if (obj instanceof long[]) {
			long[] arr = (long[]) obj;
			return Arrays.toString(arr);
		} else if (obj instanceof double[]) {
			double[] arr = (double[]) obj;
			return Arrays.toString(arr);
		} else if (obj instanceof char[]) {
			char[] arr = (char[]) obj;
			return Arrays.toString(arr);
		} else if (obj instanceof boolean[]) {
			boolean[] arr = (boolean[]) obj;
			return Arrays.toString(arr);
		} else if (obj instanceof Object[]) {
			Object[] arr = (Object[]) obj;
			return deepToString(arr);
		} else if (obj instanceof Iterable<?>) {
			Iterable<?> it = (Iterable<?>) obj;
			final StringJoiner sj = new StringJoiner(", ", "[", "]");
			for (final Object e : it) sj.add(stringify(e));
			return sj.toString();
		}
		return obj.toString();
	}
	// endregion
}
