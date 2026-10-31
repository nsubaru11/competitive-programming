import static java.lang.Math.*;

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
	private static final FastPrinter out = new FastPrinter(true);
	// endregion

	private static void solve() {
		int n = sc.nextInt();
		int m = sc.nextInt();
		HashSet<Long> h = new HashSet<>(m);
		int cnt = 0;
		while (m-- > 0) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			long k = ((a + b) * (a + b + 1L) >> 1) + min(a, b);
			if (h.contains(k)) {
				cnt++;
			} else if (a == b) {
				cnt++;
			}
			h.add(k);
		}
		out.println(cnt);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
