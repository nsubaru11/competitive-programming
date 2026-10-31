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
		String s = sc.next();
		long n = s.length();
		int q = sc.nextInt();
		while (q-- > 0) {
			long k = sc.nextLong() - 1;
			long l = k % n;
			long r = k / n;
			int cnt = Long.bitCount(r) % 2;
			char c = s.charAt((int) l);
			if (cnt == 1) {
				if (Character.isUpperCase(c)) {
					c = Character.toLowerCase(c);
				} else {
					c = Character.toUpperCase(c);
				}
			}
			out.print(c, "");
		}
		out.println().flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
