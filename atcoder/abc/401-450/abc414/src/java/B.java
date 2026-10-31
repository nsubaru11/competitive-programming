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
		long cnt = 0;
		StringBuilder sb = new StringBuilder(100);
		while (n-- > 0) {
			char c = sc.nextChar();
			long l = sc.nextLong();
			if (cnt + l > 100) {
				out.println("Too Long");
				return;
			}
			cnt += l;
			while (l-- > 0) {
				sb.append(c);
			}
		}
		out.println(sb);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
