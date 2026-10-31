import static java.lang.Math.*;

import java.util.function.*;

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
		int h = sc.nextInt();
		int w = sc.nextInt();
		int hw = h * w;
		long[] a = sc.nextLong(hw);
		long[] ans = new long[1];
		boolean[] memo = new boolean[hw];
		BiFunction<BiFunction, Long[], Void> dfs = (self, ps) -> {
			int pos = (int) ps[0].longValue();
			long sum = ps[1];
			if (pos == hw) {
				ans[0] = max(ans[0], sum);
				return null;
			}
			if (memo[pos]) {
				ps[0]++;
				self.apply(self, ps);
				ps[0]--;
				return null;
			}
			int i = pos / w;
			int j = pos % w;
			ps[0]++;
			ps[1] ^= a[pos];
			self.apply(self, ps);
			ps[1] ^= a[pos];
			ps[0]--;
			if (i + 1 < h && !memo[pos + w]) {
				memo[pos] = memo[pos + w] = true;
				ps[0]++;
				self.apply(self, ps);
				ps[0]--;
				memo[pos] = memo[pos + w] = false;
			}
			if (j + 1 < w && !memo[pos + 1]) {
				memo[pos] = memo[pos + 1] = true;
				ps[0]++;
				self.apply(self, ps);
				ps[0]--;
				memo[pos] = memo[pos + 1] = false;
			}
			return null;
		};
		Long[] b = new Long[2];
		b[0] = 0L;
		b[1] = 0L;
		dfs.apply(dfs, b);
		out.println(ans);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
