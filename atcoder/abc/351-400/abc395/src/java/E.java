import java.util.*;

import lib.graph.*;
import lib.io.*;

public class E {

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

	private static int x;

	private static void solve() {
		int n = sc.nextInt();
		int m = sc.nextInt();
		x = sc.nextInt();
		// 頂点 i は辺の向きがそのままの状態、頂点 n + i は辺の向きを反転した状態を表す
		DirectedGraph g = new DirectedGraph(2 * n, 2 * m + 2 * n);
		while (m-- > 0) {
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			g.add(u, v, 1);
			g.add(n + v, n + u, 1);
		}
		for (int i = 0; i < n; i++) {
			g.add(i, n + i, x);
			g.add(n + i, i, x);
		}
		long[] dist = Dijkstra.dist(g, 0);
		out.println(Math.min(dist[n - 1], dist[2 * n - 1]));
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
