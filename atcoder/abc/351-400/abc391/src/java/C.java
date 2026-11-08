import lib.io.*;

import static java.util.Arrays.*;

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
		int q = sc.nextInt();
		int[] hole = new int[n];
		int[] pigeon = new int[n];
		fill(hole, 1);
		setAll(pigeon, i -> i);
		int ans = 0;
		while (q-- > 0) {
			int x = sc.nextInt();
			if (x == 1) {
				int p = sc.nextInt() - 1;
				int h = sc.nextInt() - 1;
				if (hole[pigeon[p]] == 2) ans--;
				if (hole[h] == 1) ans++;
				hole[pigeon[p]]--;
				pigeon[p] = h;
				hole[h]++;
			} else {
				out.println(ans);
			}
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
}
