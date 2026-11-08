import lib.ds.segmenttree.*;
import lib.io.*;

import static java.util.Arrays.*;

public final class E2 {

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

	// region initialization
	// ------------------------ 定数 ------------------------


	private static final int[] dij = new int[]{0, -1, 0, 1, -1, -1, 1, 1, -1, 0, 1, 0, -1, 1, 1, -1};


	// endregion

	// ------------------------ メインロジック ------------------------
	private static void solve() {
		int n = sc.nextInt();
		int q = sc.nextInt();
		int[] arr = sc.nextInt(n);
		IntSegmentTree a = new IntSegmentTree(500001, Integer::sum, 0);
		LongSegmentTree b = new LongSegmentTree(500001, Long::sum, 0L);
		for (int k : arr) {
			a.add(k, 1);
			b.add(k, k);
		}
		while (q-- > 0) {
			char c = sc.nextChar();
			if (c == '1') {
				int x = sc.nextInt() - 1;
				int y = sc.nextInt();
				int prev = arr[x];
				a.add(prev, -1);
				b.add(prev, -prev);
				arr[x] = y;
				a.add(y, 1);
				b.add(y, y);
			} else {
				int l = sc.nextInt();
				int r = sc.nextInt();
				if (l > r) {
					out.println((long) l * n);
				} else {
					long ans = (long) a.query(0, l) * l + b.query(l, r + 1) + (long) a.query(r + 1, 500001) * r;
					out.println(ans);
				}
			}
		}
	}

	// region main() and debug() methods
	// ------------------------ main() 関数 ------------------------
	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	// ------------------------ デバッグ用 ------------------------
	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			System.err.println(deepToString(args));
		}
	}
}
