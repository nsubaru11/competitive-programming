import java.util.*;

import lib.io.*;

public class C {

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
		int m = sc.nextInt();
		long ans = (long) n * n;
		HashSet<Long> set = new HashSet<>(m * 9);

		while (m-- > 0) {
			int x = sc.nextInt() - 1;
			int y = sc.nextInt() - 1;
			add(set, x, y, n);
			for (int i = 1, j = 2; i <= 2; i++, j--) {
				add(set, x + i, y + j, n);
				add(set, x + i, y - j, n);
				add(set, x - i, y + j, n);
				add(set, x - i, y - j, n);
			}
		}
		System.out.println(ans - set.size());
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static void add(HashSet<Long> set, int x, int y, int n) {
		if (x < 0 || n <= x || y < 0 || n <= y) return;
		set.add((long) x * n + y);
	}
}
