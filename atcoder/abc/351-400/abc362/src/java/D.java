import lib.graph.*;
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

	private static void solve() {
		int n = sc.nextInt();
		int m = sc.nextInt();
		DirectedGraph g = new DirectedGraph(n, m * 2);
		int[] a = sc.nextInt(n);
		while (m-- > 0) {
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			int b = sc.nextInt();
			g.add(u, v, b + a[v]);
			g.add(v, u, b + a[u]);
		}
		long[] d = Dijkstra.dist(g, 0);
		out.print(d[1] + a[0]);
		for (int i = 2; i < n; i++) {
			out.print(' ').print(d[i] + a[0]);
		}
		out.println();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
