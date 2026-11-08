import java.util.*;

import lib.io.*;

public class D {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int m = sc.nextInt();
		Graph g = new Graph(n);
		for (int i = 0; i < m; i++) {
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			int w = sc.nextInt();
			g.addEdge(u, v, w);
		}
		out.print(g.solve()).println().flush();
	}

	private static class Graph {
		private static int v;
		List<List<Edge>> edges;

		Graph(int vv) {
			v = vv;
			edges = new ArrayList<>();
			while (vv-- > 0) {
				edges.add(new ArrayList<>());
			}
		}

		public void addEdge(int u, int v, int c) {
			edges.get(u).add(new Edge(u, v, c));
			//edges.get(v).add(new Edge(v, u, -c));
		}

		long[] solve() {
			long[] ans = new long[v];
			BitSet bs = new BitSet(v);
			Stack<Integer> st = new Stack<>();
			int u = 0;
			bs.set(u);
			while (u < v) {
				st.push(u);
				while (!st.isEmpty()) {
					u = st.pop();
					for (Edge e : edges.get(u)) {
						int v = e.to;
						if (!bs.get(v)) {
							ans[v] = e.cost + ans[u];
							bs.set(v);
							st.push(v);
						} else if (!bs.get(u)) {
							ans[u] = ans[v] - e.cost;
							bs.set(u);
						}
					}
				}
				u = bs.nextClearBit(u);
			}
			return ans;
		}

		private class Edge {
			int to, cost;

			Edge(int i, int j, int c) {
				this.to = j;
				this.cost = c;
			}
		}

	}
}
