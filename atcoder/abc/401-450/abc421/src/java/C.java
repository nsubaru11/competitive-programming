import lib.ds.set.*;
import lib.io.*;

import static java.lang.Math.*;

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
		int n = sc.nextInt() * 2;
		IntTreap a1 = new IntTreap();
		IntTreap b1 = new IntTreap();
		IntTreap a2 = new IntTreap();
		IntTreap b2 = new IntTreap();
		for (int i = 0; i < n; i++) {
			char c = sc.nextChar();
			if (c == 'A') {
				a1.add(i);
				a2.add(i);
			} else {
				b1.add(i);
				b2.add(i);
			}
		}
		long ans_a = 0, ans_b = 0;
		boolean flag = false, flag2 = false;
		for (int i = 0; i < n - 1; i++) {
			if (flag) {
				Integer j = b1.ceiling(i);
				if (j == null) break;
				ans_a += j - i;
				b1.remove(j);
				a1.add(j);
			} else {
				Integer j = a1.ceiling(i);
				if (j == null) break;
				ans_a += j - i;
				a1.remove(j);
				b1.add(j);
			}
			flag = !flag;

			if (flag2) {
				Integer j = a2.ceiling(i);
				if (j == null) break;
				ans_b += j - i;
				a2.remove(j);
				b2.add(j);
			} else {
				Integer j = b2.ceiling(i);
				if (j == null) break;
				ans_b += j - i;
				b2.remove(j);
				a2.add(j);
			}
			flag2 = !flag2;
		}

		out.println(min(ans_a, ans_b));
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
