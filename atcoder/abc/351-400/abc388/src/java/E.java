import lib.io.*;
import lib.search.*;

public class E {

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
		int[] a = sc.nextInt(n);
		int ans = 0;
		for (int i = 0, j = n / 2; i < n / 2; i++) {
			int k = a[i];
			j = BinarySearch.lowerBound(j, n, idx -> {
				int id = (int) idx;
				return Integer.compare(a[id], k * 2);
			});
			j = j < 0 ? ~j : j;
			if (j++ >= n) break;
			ans++;
		}
		System.out.println(ans);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
