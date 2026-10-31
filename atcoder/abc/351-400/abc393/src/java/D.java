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
	private static final FastPrinter out = new FastPrinter(true);
	// endregion

	private static void solve() {
		int n = sc.nextInt();
		char[] c = new char[n];
		long sum = 0;
		int cnt = 0, prev = 0;
		for (int i = 0; i < n; i++) {
			if ((c[i] = sc.nextChar()) == '1') {
				if (cnt >= 1) {
					sum += (long) cnt * (i - prev - 1);
				}
				prev = i;
				cnt++;
			}
		}
		int cnt2 = 0;
		for (int i = n - 1; i >= 0; i--) {
			if (c[i] == '1') {
				if (cnt2 >= 1) {
					long temp = sum - (long) (cnt - cnt2) * (prev - i - 1) + (long) cnt2 * (prev - i - 1);
					if (temp <= sum) {
						sum = temp;
					} else {
						break;
					}
				}
				prev = i;
				cnt2++;
			}
		}
		out.println(sum);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
