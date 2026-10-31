import static java.util.Arrays.*;

import java.util.*;

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
		int m = sc.nextInt();
		List<List<Integer>> graphRev = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graphRev.add(new ArrayList<>());
		}
		while (m-- > 0) {
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			graphRev.get(v).add(u);
		}
		int q = sc.nextInt();
		boolean[] flag = new boolean[n];
		while (q-- > 0) {
			char t = sc.nextChar();
			int v = sc.nextInt() - 1;
			if (t == '1') {
				if (flag[v]) continue;
				ArrayDeque<Integer> dq = new ArrayDeque<>();
				dq.add(v);
				flag[v] = true;
				while (!dq.isEmpty()) {
					int from = dq.poll();
					for (int to : graphRev.get(from)) {
						if (flag[to]) continue;
						dq.add(to);
						flag[to] = true;
					}
				}
			} else {
				out.println(flag[v]);
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
