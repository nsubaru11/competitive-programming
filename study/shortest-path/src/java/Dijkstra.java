import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

@SuppressWarnings("unused")
public class Dijkstra {
	private int used;
	private final int v;
	private final int[] path;
	private final long[] ans;
	private final List<List<Edge>> edges;
	private final long INF = Long.MAX_VALUE;

	public Dijkstra(int v) {
		if (v < 0)
			throw new IllegalArgumentException();
		this.v = v;
		used = -1;
		path = new int[v];
		ans = new long[v];
		edges = new ArrayList<>(v);
		while (v-- > 0) {
			edges.add(new ArrayList<>());
		}
	}

	public void addEdge(int i, int j, int cost) {
		if (i < 0 || i >= v || j < 0 || j >= v)
			throw new IndexOutOfBoundsException();
		edges.get(i).add(new Edge(i, j, cost));
	}

	public long getShortestPathWeight(int i, int j) {
		if (i < 0 || i >= v || j < 0 || j >= v)
			throw new IndexOutOfBoundsException();
		if (used != i) {
			used = i;
			Arrays.fill(ans, INF);
			ans[i] = 0;
			Arrays.fill(path, -1);
			PriorityQueue<Vertex> pq = new PriorityQueue<>();
			pq.add(new Vertex(i, 0));
			while (!pq.isEmpty()) {
				Vertex v = pq.poll();
				int from = v.vertex;
				if (ans[from] < v.cost)
					continue;
				for (Edge edge : edges.get(from)) {
					long cost = edge.cost;
					int to = edge.to;
					if (ans[from] != INF && ans[to] > ans[from] + cost) {
						ans[to] = ans[from] + cost;
						pq.add(new Vertex(to, ans[to]));
						path[to] = from;
					}
				}
			}
		}
		return ans[j];
	}

	public List<Integer> getShortestPath(int i, int j) {
		if (i < 0 || i >= v || j < 0 || j >= v)
			throw new IndexOutOfBoundsException();
		if (used != i)
			getShortestPathWeight(i, j);
		if (ans[j] == INF)
			return null;
		List<Integer> shortestPath = new ArrayList<>();
		shortestPath.add(j);
		while (path[j] >= 0) {
			j = path[j];
			shortestPath.add(j);
		}
		Collections.reverse(shortestPath);
		return shortestPath;
	}

	private class Edge {
		public int from, to, cost;

		public Edge(int i, int j, int c) {
			from = i;
			to = j;
			cost = c;
		}
	}

	private class Vertex implements Comparable<Vertex> {
		public int vertex;
		public long cost;

		public Vertex(int v, long c) {
			this.vertex = v;
			this.cost = c;
		}

		@Override
		public int compareTo(Vertex o) {
			return Long.compare(cost, o.cost);
		}
	}

}
