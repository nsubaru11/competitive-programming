import static java.util.Arrays.*;

import lib.ds.segmenttree.*;
import lib.io.*;

public final class E {

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
		SegmentTree<Pair> a = new SegmentTree<>(500001, Pair::sum, null);
		for (int k : arr) {
			Pair p = a.get(k);
			a.set(k, p == null ? new Pair(1, k) : new Pair(p.i + 1, p.j + k));
		}
		while (q-- > 0) {
			char c = sc.nextChar();
			if (c == '1') {
				int x = sc.nextInt() - 1;
				int y = sc.nextInt();
				int prev = arr[x];
				Pair p1 = a.get(prev);
				a.set(prev, p1.i == 1 ? null : new Pair(p1.i - 1, p1.j - prev));
				arr[x] = y;
				Pair p2 = a.get(y);
				a.set(y, p2 == null ? new Pair(1, y) : new Pair(p2.i + 1, p2.j + y));
			} else {
				int l = sc.nextInt();
				int r = sc.nextInt();
				if (l > r) {
					out.println((long) l * n);
				} else {
					Pair pl = a.query(0, l);
					Pair pc = a.query(l, r + 1);
					Pair pr = a.query(r + 1, 500001);
					long ans = 0;
					if (pl != null) ans += pl.i * l;
					if (pc != null) ans += pc.j;
					if (pr != null) ans += pr.i * r;
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
	// endregion

	private record Pair(long i, long j) {

		public static Pair sum(final Pair a, final Pair b) {
			if (a != null && b != null) {
				return new Pair(a.i() + b.i(), a.j() + b.j());
			} else if (a != null) {
				return new Pair(a.i(), a.j());
			} else if (b != null) {
				return new Pair(b.i(), b.j());
			}
			return null;
		}

		public boolean equals(final Object o) {
			if (this == o) return true;
			if (!(o instanceof Pair(long i1, long j1))) return false;
			return i == i1 && j == j1;
		}
	}
}
