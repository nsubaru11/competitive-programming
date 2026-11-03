import static java.util.Arrays.*;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.stream.*;

public class D {

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

	private static ArrayList<Integer> G;

	public static void solve() {
		int n = sc.nextInt();
		int[] a = new int[n];
		setAll(a, i -> sc.nextInt());
		int[] cnt = {0};
		G = new ArrayList<>();
		shellSort(a, n, cnt);
		int size = G.size();
		out.println(size);
		for (int i = size - 1; i > 0; i--) {
			out.print(G.get(i));
			out.print(' ');
		}
		out.println(G.get(0));
		out.println(cnt[0]);
		printArray(a);
	}

	private static void insertionSort(int[] a, int n, int g, int[] cnt) {
		for (int i = g; i < n; i++) {
			int v = a[i];
			int j = i - g;
			while (j >= 0 && a[j] > v) {
				a[j + g] = a[j];
				j -= g;
				cnt[0]++;
			}
			if (j + g < n) a[j + g] = v;
		}
	}

	private static void shellSort(int[] a, int n, int[] cnt) {
		cnt[0] = 0;
		G = new ArrayList<>();
		int h = 1;
		while (h <= n) {
			G.add(h);
			h = 3 * h + 1;
		}
		int size = G.size();
		for (int i = size - 1; i >= 0; i--) {
			insertionSort(a, n, G.get(i), cnt);
		}
	}

	private static void printArray(final int[] arr) {
		out.print(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			out.print(' ');
			out.print(arr[i]);
		}
		out.println();
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
