import java.util.*;

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
		int[] a = new int[n];
		Arrays.setAll(a, i -> i + 1);
		int head = 0;
		while (q-- > 0) {
			int t = sc.nextInt();
			switch (t) {
				case 1:
					int p = sc.nextInt() - 1;
					int x = sc.nextInt();
					a[(head + p) % n] = x;
					break;
				case 2:
					p = sc.nextInt() - 1;
					out.println(a[(head + p) % n]);
					break;
				case 3:
					int k = sc.nextInt();
					head = (head + k) % n;
					break;
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
