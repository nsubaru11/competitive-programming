import lib.io.*;

public class D {

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
			int m = sc.nextInt();
			int[] a = sc.nextSortedInt(n);
			int[] b = sc.nextSortedInt(n);
			int l = 0, r = n - 1;
			long ans = 0;
			for (int i = 0; i < n; i++) {
				int il = (a[i] + b[l]) % m;
				int ir = (a[i] + b[r]) % m;
				if (il < ir) {
					ans += il;
					l++;
				} else {
					ans += ir;
					r--;
				}
			}
			out.println(ans);
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
