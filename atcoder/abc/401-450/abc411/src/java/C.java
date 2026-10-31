import java.util.function.*;

import lib.io.*;

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
		int cnt = 0;
		boolean[] isBlack = new boolean[n];
		Predicate<Integer> range = i -> i - 1 >= 0 && i + 1 < n;
		while (q-- > 0) {
			int a = sc.nextInt() - 1;
			if (range.test(a) && isBlack[a - 1] && isBlack[a] && isBlack[a + 1]) { // bbb
				cnt++;
			} else if (range.test(a) && isBlack[a - 1] && !isBlack[a] && isBlack[a + 1]) { // bwb
				cnt--;
			} else if (range.test(a) && !isBlack[a - 1] && !isBlack[a + 1] && !isBlack[a]) { // www
				cnt++;
			} else if (range.test(a) && !isBlack[a - 1] && !isBlack[a + 1] && isBlack[a]) { // wbw
				cnt--;
			} else if (a == n - 1 && isBlack[a] && a - 1 >= 0 && !isBlack[a - 1]) { // .wb
				cnt--;
			} else if (a == n - 1 && !isBlack[a] && a - 1 >= 0 && !isBlack[a - 1]) { // .ww
				cnt++;
			} else if (a == 0 && isBlack[a] && a + 1 < n && !isBlack[a + 1]) { // bw.
				cnt--;
			} else if (a == 0 && !isBlack[a] && a + 1 < n && !isBlack[a + 1]) { // ww.
				cnt++;
			} else if (n == 1) {
				cnt = isBlack[a] ? 0 : 1;
			}
			isBlack[a] = !isBlack[a];
			out.println(cnt);
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
