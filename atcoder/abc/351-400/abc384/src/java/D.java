import java.util.*;

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
		// input
		int n = sc.nextInt();
		long s = sc.nextLong();
		int[] a = sc.nextInt(n);

		// s % sum(a)に対し、尺取り法
		s %= Arrays.stream(a).asLongStream().sum();
		long sum = 0;
		for (int i = 0, j = 0; i < n; i++) {
			while (s > sum) {
				sum += a[j];
				j++;
				if (j == n) j = 0;
			}
			if (s == sum) {
				out.println("Yes").flush();
				return;
			}
			sum -= a[i];
		}
		out.println("No").flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
