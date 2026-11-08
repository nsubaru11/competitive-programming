import lib.io.*;
import lib.util.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

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

	private static int h, w;

	private static void solve() {
		h = sc.nextInt();
		w = sc.nextInt();
		int[][] a = sc.nextIntMat(h, w);
		int[][] b = sc.nextIntMat(h, w);
		int[] di = new int[h];
		setAll(di, i -> i);
		int ans = Integer.MAX_VALUE;
		outer:
		do {
			int[] dj = new int[w];
			setAll(dj, j -> j);
			do {
				if (equals(a, b, di, dj)) {
					ans = min(ans, count(di, dj));
					break outer;
				}
			} while (Permutation.next(dj));
		} while (Permutation.next(di));
		out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	private static int count(int[] di, int[] dj) {
		int cnt = 0;
		for (int i = 0; i < h; i++) {
			for (int j = i + 1; j < h; j++) {
				if (di[i] > di[j]) cnt++;
			}
		}
		for (int i = 0; i < w; i++) {
			for (int j = i + 1; j < w; j++) {
				if (dj[i] > dj[j]) cnt++;
			}
		}
		return cnt;
	}

	private static boolean equals(int[][] a, int[][] b, int[] di, int[] dj) {
		for (int ib = 0; ib < h; ib++) {
			int ia = di[ib];
			for (int jb = 0; jb < w; jb++) {
				int ja = dj[jb];
				if (a[ia][ja] != b[ib][jb]) return false;
			}
		}
		return true;
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
