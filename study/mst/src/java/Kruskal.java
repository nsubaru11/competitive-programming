import java.util.*;

final class Kruskal {
	private final UnionFind uf;
	private final PriorityQueue<Edge> edges;

	public Kruskal(int v) {
		uf = new UnionFind(v);
		edges = new PriorityQueue<>(v);
	}

	public Kruskal addEdge(int u, int v, int cost) {
		edges.add(new Edge(u, v, cost));
		return this;
	}

	public int MST() {
		int ans = 0;
		while (!edges.isEmpty()) {
			Edge e = edges.poll();
			if (uf.find(e.u) != uf.find(e.v)) {
				uf.union(e.u, e.v);
				ans += e.cost;
			}
		}
		return ans;
	}

	private static class Edge implements Comparable<Edge> {
		int u, v, cost;

		Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
			this.cost = w;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(cost, o.cost);
		}
	}

	private static final class UnionFind {
		private final int[] root, rank;

		public UnionFind(int size) {
			root = new int[size];
			rank = new int[size];
			Arrays.setAll(root, i -> i);
		}

		public int find(int x) {
			return x != root[x] ? root[x] = find(root[x]) : root[x];
		}

		public int union(int x, int y) {
			x = find(x);
			y = find(y);
			if (x != y) {
				if (rank[x] > rank[y]) {
					root[y] = x;
				} else if (rank[x] < rank[y]) {
					root[x] = y;
				} else {
					root[y] = x;
					rank[x] += 1;
				}
			}
			return rank[x] >= rank[y] ? x : y;
		}

	}

}
