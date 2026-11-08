import java.util.*;

import lib.io.*;

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

	// region initialization
	// ------------------------ 定数 ------------------------


	private static final int[] dij = new int[]{0, -1, 0, 1, -1, -1, 1, 1, -1, 0, 1, 0, -1, 1, 1, -1};


	// endregion

	// ------------------------ メインロジック ------------------------
	private static void solve() {
		int h = sc.nextInt();
		int w = sc.nextInt();
		int hw = h * w;
		char[] s = sc.nextChars(hw);
		int[] visitedCnt = new int[hw];
		fill(visitedCnt, -1);
		int a = sc.nextInt() - 1;
		int b = sc.nextInt() - 1;
		int c = sc.nextInt() - 1;
		int d = sc.nextInt() - 1;
		int start = a * w + b, goal = c * w + d;
		visitedCnt[start] = 0;
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		dq.add(start);
		while (!dq.isEmpty()) {
			int from = dq.poll();
			if (from == goal) break;
			int cnt = visitedCnt[from];
			int i = from / w, j = from % w;
			for (int t = 0; t < 4; t++) {
				int di = i + dij[t], dj = j + dij[t + 8];
				if (di < 0 || h <= di || dj < 0 || w <= dj) continue;
				int to = di * w + dj;
				int toCnt = visitedCnt[to];
				if (s[to] == '.') {
					if (toCnt == -1 || cnt < toCnt) {
						visitedCnt[to] = cnt;
						dq.addFirst(to);
					}
				} else {
					if (toCnt == -1 || cnt + 1 < toCnt) {
						visitedCnt[to] = cnt + 1;
						dq.addLast(to);
					}
					di += dij[t];
					dj += dij[t + 8];
					if (di < 0 || h <= di || dj < 0 || w <= dj) continue;
					to = di * w + dj;
					toCnt = visitedCnt[to];
					if (toCnt == -1 || cnt + 1 < toCnt) {
						visitedCnt[to] = cnt + 1;
						dq.addLast(to);
					}
				}
			}
		}
		out.println(visitedCnt[goal]);
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
