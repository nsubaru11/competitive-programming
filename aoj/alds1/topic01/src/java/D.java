import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public final class D {

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
		int n = sc.nextInt();
		int ans = Integer.MIN_VALUE;
		int mn = sc.nextInt();
		while (n-- > 1) {
			int v = sc.nextInt();
			ans = max(ans, v - mn);
			mn = min(mn, v);
		}
		out.println(ans);
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
