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
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			int a = sc.nextInt();
			if (n == 2) {
				int last = sc.nextInt();
				out.println(a * 2 >= last ? 2 : -1);
				continue;
			}
			TreeSet<Integer> ts = new TreeSet<>();
			for (int i = 0; i < n - 2; i++) {
				ts.add(sc.nextInt());
			}
			int last = sc.nextInt();
			int cnt = 2;
			while (a * 2 < last) {
				Integer b = ts.floor(a * 2);
				if (b == null || b <= a) {
					cnt = -1;
					break;
				} else {
					a = b;
					cnt++;
				}
			}
			out.println(cnt);
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
