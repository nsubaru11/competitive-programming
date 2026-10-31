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
		int[] a = sc.nextInt(n);
		Pair[] b = new Pair[m];
		for (int i = 0; i < m; i++) {
			int x = sc.nextInt();
			b[i] = new Pair(x, i);
		}
		Arrays.sort(b, (o1, o2) -> o2.val - o1.val);
		for (int i = 0, j = 0; i < m; i++) {
			if (j < n && a[j] <= b[i].val) {
				b[i].val = j + 1;
			} else {
				while (j < n && a[j] > b[i].val) {
					j++;
				}
				b[i].val = j >= n ? -1 : j + 1;
			}
		}
		Arrays.sort(b, (o1, o2) -> o1.idx - o2.idx);
		for (int i = 0; i < m; i++) {
			out.println(b[i].val);
		}
		out.flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static class Pair {
		int val, idx;

		Pair(int val, int idx) {
			this.val = val;
			this.idx = idx;
		}
	}
}
