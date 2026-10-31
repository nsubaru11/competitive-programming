import static java.util.Arrays.*;

import java.util.*;

import lib.ds.unionfind.*;
import lib.io.*;

public final class D {

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
		int x = sc.nextInt();
		int y = sc.nextInt();
		ArrayList<Rectangle> rects = new ArrayList<>();
		rects.add(new Rectangle(0, 0, x - 1, y - 1));
		for (int i = 0; i < n; i++) {
			char c = sc.nextChar();
			int a = sc.nextInt();
			int b = sc.nextInt();
			int size = rects.size();
			if (c == 'X') {
				for (int j = 0; j < size; j++) {
					Rectangle r = rects.get(j);
					long x1 = r.x1, y1 = r.y1, x2 = r.x2, y2 = r.y2;
					if (x1 < a && a <= x2) {
						r.y1 -= b;
						r.y2 -= b;
						r.x2 = a - 1;
						rects.add(new Rectangle(a, y1 + b, x2, y2 + b));
					} else if (x1 < a) {
						r.y1 -= b;
						r.y2 -= b;
					} else {
						r.y1 += b;
						r.y2 += b;
					}
				}
			} else {
				for (int j = 0; j < size; j++) {
					Rectangle r = rects.get(j);
					long x1 = r.x1, y1 = r.y1, x2 = r.x2, y2 = r.y2;
					if (y1 < a && a <= y2) {
						r.x1 -= b;
						r.x2 -= b;
						r.y2 = a - 1;
						rects.add(new Rectangle(x1 + b, a, x2 + b, y2));
					} else if (y1 < a) {
						r.x1 -= b;
						r.x2 -= b;
					} else {
						r.x1 += b;
						r.x2 += b;
					}
				}
			}
		}

		int size = rects.size();
		UnionFind uf = new UnionFind(size);
		for (int i = 0; i < size; i++) {
			Rectangle ri = rects.get(i);
			for (int j = 0; j < size; j++) {
				if (i == j) continue;
				Rectangle rj = rects.get(j);
				boolean xFlag = (ri.x1 <= rj.x1 && rj.x1 <= ri.x2) || (ri.x1 <= rj.x2 && rj.x2 <= ri.x2);
				boolean yFlag = (ri.y1 <= rj.y1 && rj.y1 <= ri.y2) || (ri.y1 <= rj.y2 && rj.y2 <= ri.y2);
				if (xFlag && ((ri.y2 + 1 == rj.y1) || (ri.y1 == rj.y2 + 1))) uf.union(i, j);
				if (yFlag && ((ri.x2 + 1 == rj.x1) || (ri.x1 == rj.x2 + 1))) uf.union(i, j);
			}
		}

		int groupCnt = uf.groupCount();
		out.println(groupCnt);
		HashMap<Integer, Long> cnt = new HashMap<>(groupCnt);
		for (int i = 0; i < size; i++) {
			int ri = uf.find(i);
			cnt.put(ri, cnt.getOrDefault(ri, 0L) + rects.get(i).size());
		}
		long[] ans = new long[groupCnt];
		int i = 0;
		for (long c : cnt.values()) ans[i++] = c;
		sort(ans);
		out.print(ans).println();
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

	private static final class Rectangle {
		public long x1, y1, x2, y2;

		public Rectangle(long x1, long y1, long x2, long y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		public long size() {
			return (x2 - x1 + 1) * (y2 - y1 + 1);
		}

		public String toString() {
			return "[" + x1 + " " + y1 + " " + x2 + " " + y2 + "]";
		}
	}
}
