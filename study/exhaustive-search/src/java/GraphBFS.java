import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * BFSの基本フォーマットです。 隣接行列リストのBFSを行います。 コンストラクタでisDirectedを指定しなければ無向グラフとなります。
 */
public class GraphBFS {
	private final int v, e;
	private final boolean isDirected;
	private final List<List<Integer>> graph;

	public GraphBFS(int v, int e, boolean isDirected) {
		this.v = v;
		this.e = e;
		this.isDirected = isDirected;
		graph = new ArrayList<>(v);
		for (int i = 0; i < v; i++) {
			graph.add(new ArrayList<>());
		}
	}

	public GraphBFS(int v, int e) {
		this(v, e, false);
	}

	public void addEdge(int from, int to) {
		graph.get(from).add(to);
		if (!isDirected) graph.get(to).add(from);
	}

	public int searchGoal(int start, int goal) {
		return searchGoal(List.of(start), goal);
	}

	public int searchGoal(List<Integer> start, int goal) {
		ArrayDeque<Integer> dq = new ArrayDeque<>(Math.min(v, e));
		int[] dist = new int[v];
		Arrays.fill(dist, -1);
		for (int s: start) {
			dq.add(s);
			dist[s] = 0;
		}
		while (!dq.isEmpty()) {
			int current = dq.poll();
			if (current == goal) break;
			for (int e : graph.get(current)) {
				if (dist[e] == -1) {
					dist[e] = dist[current] + 1;
					dq.add(e);
				}
			}
		}
		return dist[goal];
	}
}
