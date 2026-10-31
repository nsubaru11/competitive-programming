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
		boolean login = false;
		int cnt = 0;
		while (n-- > 0) {
			String s = sc.next();
			switch (s) {
				case "login":
					login = true;
					break;
				case "logout":
					login = false;
					break;
				case "private":
					cnt += login ? 0 : 1;
					break;
				case "public":
					break;
			}
		}
		out.println(cnt);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
