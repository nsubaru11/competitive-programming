import static java.lang.Math.*;

import lib.io.*;

public class C2 {

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
		int max = 1;
		char c = sc.nextChar();
		n--;
		while (n > 0) {
			int cnt1 = 0;
			while (c == '1' && n-- > 0) {
				cnt1++;
				c = sc.nextChar();
			}
			int cnt = 0;
			while (c == '/' && n-- > 0) {
				cnt++;
				c = sc.nextChar();
			}
			int cnt2 = 0;
			while (c == '2' && n-- > 0) {
				cnt2++;
				c = sc.nextChar();
				if (n == 0) cnt2++;
			}
			max = max(max, cnt == 1 ? min(cnt1, cnt2) * 2 + 1 : 1);
		}
		System.out.println(max);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
