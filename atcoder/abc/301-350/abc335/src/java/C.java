import static java.util.Arrays.*;

import lib.io.*;

public final class C {

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
		int[] x = new int[n];
		int[] y = new int[n];
		setAll(x, i -> i + 1);
		while (q-- > 0) {
			int t = sc.nextInt();
			if (t == 1) {
				char c = sc.nextChar();
				int head = (n - cnt % n) % n;
				int tail = (head + n - 1) % n;
				switch (c) {
					case 'L':
						x[tail] = x[head] - 1;
						y[tail] = y[head];
						break;
					case 'R':
						x[tail] = x[head] + 1;
						y[tail] = y[head];
						break;
					case 'D':
						x[tail] = x[head];
						y[tail] = y[head] - 1;
						break;
					case 'U':
						x[tail] = x[head];
						y[tail] = y[head] + 1;
						break;
				}
				cnt++;
			} else {
				int p = sc.nextInt() - 1 - cnt;
				p = (p % n + n) % n;
				out.println(x[p], y[p], ' ');
			}
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
