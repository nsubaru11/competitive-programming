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
		long x = sc.nextInt();
		long y = sc.nextInt();
		long ans = 0;
		for (int i = 2; i < 10; i++) {
			ans = reverse(x + y);
			x = y;
			y = ans;
		}
		out.println(ans);
	}

	private static long reverse(long n) {
		long ans = 0;
		while (n > 0) {
			ans = ans * 10 + n % 10;
			n /= 10;
		}
		return ans;
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
