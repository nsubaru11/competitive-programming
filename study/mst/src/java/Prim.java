import java.util.*;

final class Prim {
	private final int v;
	private final List<PriorityQueue<Edge>> edges;

	public Prim(int v) {
		this.v = v;
		edges = new ArrayList<>(v);
		while(v-- > 0) {
			edges.add(new PriorityQueue<Edge>());
		}
	}

	public Prim addEdge(int u, int v, int cost) {
		edges.get(u).add(new Edge(u, v, cost));
		edges.get(v).add(new Edge(v, u, cost));
		return this;
	}

	public int MST() {
		int ans = 0;
		BitSet visited = new BitSet(v);
		PriorityQueue<Edge> heap = new PriorityQueue<>();
		heap.add(edges.get(0).poll());
		while (!heap.isEmpty()) {
			Edge e = heap.poll();
			int cost = e.cost;
			if (!visited.get(e.u)) {
				visited.set(e.u);
				ans += cost;
				for (Edge e2: edges.get(e.v)) {
					if (!visited.get(e2.u)) {
						heap.add(e2);
					}
				}
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

}
