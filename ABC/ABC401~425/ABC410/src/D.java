import static java.lang.Math.*;

import java.util.*;

import lib.io.*;

public class D {

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

	private static int n, ans = Integer.MAX_VALUE;
	private static BitSet memo;
	private static int[] head, cnt, to, cost;

	private static void solve() {
		n = sc.nextInt();
		int m = sc.nextInt();
		head = new int[n * n];
		cnt = new int[n];
		to = new int[m];
		cost = new int[m];
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			int c = sc.nextInt();
			head[a * n + cnt[a]++] = i;
			to[i] = b;
			cost[i] = c;
		}
		memo = new BitSet(n << 10);
		dfs(0, 0);
		out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	private static void dfs(int i, int c) {
		if (i == n - 1) {
			ans = min(ans, c);
		}
		int k = (i << 10) + c;
		if (!memo.get(k)) {
			memo.set(k);
			for (int j = 0; j < cnt[i]; j++) {
				int idx = head[i * n + j];
				dfs(to[idx], c ^ cost[idx]);
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
