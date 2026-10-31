import java.util.*;

import lib.io.*;

public class B {

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
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 1; i < n; i++)
			a[i] = sc.nextInt();
		Arrays.parallelPrefix(a, Integer::sum);
		for (int i = 0; i < n - 1; i++) {
			out.print(a[i + 1] - a[i]);
			for (int j = i + 2; j < n; j++) {
				out.print(" ");
				out.print(a[j] - a[i]);
			}
			out.println();
		}
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
