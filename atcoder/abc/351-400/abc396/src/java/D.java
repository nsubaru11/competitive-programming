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

	private static void solve() {
		int n = sc.nextInt();
		int m = sc.nextInt();
		Graph g = new Graph(n);
		while (m-- > 0) {
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			long w = sc.nextLong();
			g.addEdge(u, v, w);
			g.addEdge(v, u, w);
		}
		out.println(g.solve());
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static final class Graph {
		private final int v;
		private final List<List<Edge>> edges;
		private long ans = Long.MAX_VALUE;

		public Graph(int v) {
			this.v = v;
			edges = new ArrayList<>(v);
			while (v-- > 0) {
				edges.add(new ArrayList<>());
			}
		}

		public void addEdge(int u, int v, long c) {
			edges.get(u).add(new Edge(u, v, c));
		}

		public long solve() {
			dfs(0, 0, new BitSet(v));
			return ans;
		}

		public void dfs(int i, long temp, BitSet seen) {
			seen.set(i);
			if (i == v - 1) {
				ans = Math.min(ans, temp);
				seen.clear(v - 1);
				return;
			}
			for (Edge e : edges.get(i)) {
				int j = e.to;
				if (seen.get(j)) continue;
				long c = e.cost;
				seen.set(j);
				dfs(j, temp ^ c, seen);
				seen.clear(j);
			}
		}

		private static final class Edge {
			int from, to;
			long cost;

			public Edge(int i, int j, long c) {
				from = i;
				to = j;
				cost = c;
			}
		}
	}
}
