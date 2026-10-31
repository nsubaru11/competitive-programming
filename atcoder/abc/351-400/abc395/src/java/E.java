import static java.lang.Long.*;

import java.util.*;

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
		Dijkstra dijkstra = new Dijkstra(n);
		while (m-- > 0) {
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			dijkstra.addEdge(u, v);
		}
		out.println(dijkstra.getShortestPathWeight(0, n - 1));
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static final class Dijkstra {
		private static final long INF = MAX_VALUE;
		private final int v;
		private final long[][] ans;
		private final List<List<Edge>> edges;

		public Dijkstra(int v) {
			this.v = v;
			edges = new ArrayList<>(v);
			ans = new long[v][2];
			for (int i = 0; i < v; i++) {
				edges.add(new ArrayList<>());
				ans[i][0] = ans[i][1] = INF;
			}
		}

		public void addEdge(int i, int j) {
			edges.get(i).add(new Edge(i, j, false));
			edges.get(j).add(new Edge(j, i, true));
		}

		public long getShortestPathWeight(int i, int j) {
			ans[i][0] = ans[i][1] = 0;
			PriorityQueue<Vertex> pq = new PriorityQueue<>();
			pq.add(new Vertex(i, ans[i][0], false));
			while (!pq.isEmpty()) {
				Vertex vertex = pq.poll();
				int from = vertex.index;
				boolean flip = vertex.flip;
				int i2 = !flip ? 0 : 1;
				long cost = vertex.cost;
				if (ans[from][i2] < vertex.cost) continue;  // すでにより良い経路が見つかっている場合はスキップ
				for (Edge e : edges.get(from)) {
					int to = e.to;
					boolean flag = e.flag;
					if (flag == flip) {
						int f = !flip ? 0 : 1;
						if (ans[to][f] > cost + 1) {
							ans[to][f] = cost + 1;
							pq.add(new Vertex(to, cost + 1, flip));
						}
					} else {
						int f = !flip ? 0 : 1;
						if (ans[to][f] > cost + x + 1) {
							ans[to][f] = cost + x + 1;
							pq.add(new Vertex(to, cost + x + 1, !flip));
						}
					}
				}
			}
			return Math.min(ans[j][0], ans[j][1]);
		}

		private record Edge(int from, int to, boolean flag) {}

		private record Vertex(int index, long cost, boolean flip) implements Comparable<Vertex> {
			@Override
			public int compareTo(Vertex o) {
				return compare(cost, o.cost);
			}
		}
	}
}
