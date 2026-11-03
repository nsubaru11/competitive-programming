import static java.util.Arrays.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class C {

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

	public static void solve() {
		int n = sc.nextInt();
		String[] s = new String[n];
		setAll(s, i -> sc.next());
		String[] bubble = bubbleSort(s.clone(), n);
		String[] selection = selectionSort(s, n);
		printArray(bubble);
		out.println("Stable");
		printArray(selection);
		boolean f = true;
		for (int i = 0; i < n; i++) {
			if (!bubble[i].equals(selection[i])) {
				f = false;
				break;
			}
		}
		out.println(f ? "Stable" : "Not stable");
	}

	private static String[] bubbleSort(String[] s, int n) {
		boolean flag = true;
		while (flag) {
			flag = false;
			for (int i = n - 1; i >= 1; i--) {
				if (s[i].charAt(1) < s[i - 1].charAt(1)) {
					swap(s, i, i - 1);
					flag = true;
				}
			}
		}
		return s;
	}

	private static String[] selectionSort(String[] s, int n) {
		for (int i = 0; i < n - 1; i++) {
			int minJ = i;
			for (int j = i + 1; j < n; j++) {
				if (s[minJ].charAt(1) > s[j].charAt(1)) {
					minJ = j;
				}
			}
			if (i != minJ) {
				swap(s, i, minJ);
			}
		}
		return s;
	}

	private static void swap(String[] a, int i, int j) {
		String temp = a[j];
		a[j] = a[i];
		a[i] = temp;
	}

	private static void printArray(final String[] arr) {
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
			else System.err.println(stream(args).map(C::stringify).collect(Collectors.joining("\n", "\n", "")));
		}
	}

	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			if (args == null) System.err.println("null");
			else if (args.getClass().getComponentType().isArray()) System.err.println(stringify(args));
			else System.err.println(stream(args).map(C::stringify).collect(Collectors.joining(", ", "", "")));
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
