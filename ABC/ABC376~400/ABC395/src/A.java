import java.util.stream.*;

import lib.io.*;

public class A {

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
		int n = sc.nextInt();
		int[] a = sc.nextInt(n);
		out.println(IntStream.range(1, n).allMatch(i -> a[i - 1] < a[i]) ? "Yes" : "No");
		out.flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
