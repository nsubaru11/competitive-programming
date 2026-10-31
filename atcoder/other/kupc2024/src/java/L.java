import lib.io.*;

public class L {

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

	//	private static final FastPrinter out = new FastPrinter();

	private static void solve() {
		int n = sc.nextInt();
		int[] p = sc.nextInt(n - 1);
		int r = 0;
		int L = 1;
		int R = n;
		int i = R;
		while (r != -1) {
			System.out.println("?" + i);
			r = sc.nextInt();
			if (r == 1) {
				System.out.println("!" + i);
			} else if (r == 2) {
				System.out.println("!" + (i - 1));
			}
			R--;
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
