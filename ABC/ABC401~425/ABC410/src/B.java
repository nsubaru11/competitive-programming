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
		int q = sc.nextInt();
		int[] b = new int[n];
		int[] ans = new int[q];
		for (int i = 0; i < q; i++) {
			int x = sc.nextInt();
			if (x >= 1) {
				b[x - 1]++;
				ans[i] = x;
			} else {
				int minIdx = 0;
				for (int j = 1; j < n; j++) {
					if (b[minIdx] > b[j]) minIdx = j;
				}
				b[minIdx]++;
				ans[i] = minIdx + 1;
			}
		}
		out.print(ans);
		out.println();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
