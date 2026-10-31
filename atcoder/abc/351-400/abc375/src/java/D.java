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
	private static final FastPrinter out = new FastPrinter(64);
	// endregion

	private static void solve() {
		Pair[] pair = new Pair[26];
		String s = sc.next();
		long ans = 0;
		for (int i = 0; i < s.length(); i++) {
			int index = s.charAt(i) - 'A';
			if (pair[index] == null) {
				pair[index] = new Pair(1, i);
			} else {
				ans += pair[index].cnt * (i - 1) - pair[index].sum;
				pair[index].cnt++;
				pair[index].sum += i;
			}
		}
		out.println(ans);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static final class Pair {
		long cnt, sum;

		public Pair(long cnt, long sum) {
			this.cnt = cnt;
			this.sum = sum;
		}
	}
}
