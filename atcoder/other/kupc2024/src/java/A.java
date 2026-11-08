import lib.io.*;

import static java.lang.Math.*;

public class A {

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

	//	private static final FastPrinter out = new FastPrinter();

	private static void solve() {
		int n = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int t = 0, t2 = Integer.MAX_VALUE;
		while (n-- > 0) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			if (y < b) {
				t = max(t, ceilInt(a - x, y - b));
			} else if (y > b) {
				t2 = min(t2, floorInt(a - x, y - b));
			} else {
				if (x > a) {
					System.out.println("No");
					return;
				}
			}
		}
		System.out.println(t2 >= t ? "Yes" : "No");
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static int ceilInt(int a, int b) {
		return a * b <= 0 ? a / b : (a + b - 1) / b;
	}

	private static int floorInt(int a, int b) {
		return a * b < 0 ? (a - b + 1) / b : a / b;
	}
}
