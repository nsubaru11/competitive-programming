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
		int[] p = sc.nextInt(n);
		int[] q = new int[n];
		int[] q2 = new int[n];
		for (int i = 0; i < n; i++) {
			q[i] = sc.nextInt() - 1;
			q2[q[i]] = i;
		}
		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			ans.add(q[p[q2[i]] - 1] + 1);
		}
		out.print(ans.toArray()).println().flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
