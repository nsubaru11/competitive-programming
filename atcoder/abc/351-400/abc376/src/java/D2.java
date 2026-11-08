import java.util.*;

import lib.io.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public class D2 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int m = sc.nextInt();
		Graph g = new Graph(n);
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			g.addEdge(a, b, 0);
		}
		System.out.println(g.shortestRoot());
	}

	private static class Graph {
		private final List<List<Edge>> edges;
		private int v;
		private int min;

		Graph(int v) {
			this.v = v;
			edges = new ArrayList<>(v);
			while (v-- > 0) {
				edges.add(new ArrayList<>());
			}
		}

		public void addEdge(int u, int v, int c) {
			edges.get(u).add(new Edge(u, v, c));
		}

		public int shortestRoot() {
			min = Integer.MAX_VALUE;
			return bfs() ? min : -1;
		}

		public boolean isExistCycle(int i) {
			return dfs(i, new BitSet(v), new BitSet(v));
		}

		public boolean dfs(int i, BitSet seen, BitSet finished) {
			seen.set(i);
			for (Edge e : edges.get(i)) {
				int j = e.to;
				if (finished.get(i)) continue;
				if (seen.get(j) && !finished.get(j)) return true;
				if (dfs(j, seen, finished)) return true;
			}
			finished.set(i);
			return false;
		}

		public boolean bfs() {
			Deque<Integer> dq = new ArrayDeque<>();
			dq.add(0);
			int[] finished = new int[v];
			fill(finished, Integer.MAX_VALUE);
			finished[0] = 1;
			BitSet seen = new BitSet(v);
			while (!dq.isEmpty()) {
				int i = dq.pollFirst();
				seen.set(i);
				for (Edge e : edges.get(i)) {
					int j = e.to;
					if (j == 0) {
						min = finished[i];
						return true;
					}
					finished[j] = min(finished[i] + 1, finished[j]);
					if (!seen.get(j)) {
						dq.addLast(j);
					}
				}
			}
			return false;
		}

		private class Edge {
			int from, to, cost;

			Edge(int i, int j, int c) {
				this.from = i;
				this.to = j;
				this.cost = c;
			}
		}
	}
}
