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

	private static void solve() {
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int x = sc.nextInt() - 1;
			int y = sc.nextInt() - 1;
			Graph g = new Graph(n, out);
			while (m-- > 0) {
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				g.addEdge(a, b);
			}
			g.solve(x, y);
			out.println();
		}
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static class Graph {
		private final int n;
		private final List<List<Integer>> graph;
		private final FastPrinter out;

		public Graph(final int n, final FastPrinter out) {
			this.n = n;
			this.out = out;
			this.graph = new ArrayList<>(n);
			for (int i = 0; i < n; i++)
				this.graph.add(new ArrayList<>());
		}

		public void addEdge(final int from, final int to) {
			graph.get(from).add(to);
			graph.get(to).add(from);
		}

		public void solve(int x, int y) {
			for (int i = 0; i < n; i++)
				Collections.sort(graph.get(i));
			int[] path = new int[n];
			Arrays.fill(path, -1);
			path[x] = x;
			dfs(x, x, y, path);
		}

		public boolean dfs(int i, int x, int y, int[] path) {
			for (int p : graph.get(i)) {
				if (path[p] == -1) {
					path[p] = i;
					if (p == y) {
						ArrayList<Integer> path2 = new ArrayList<>();
						while (x != y) {
							path2.add(y + 1);
							y = path[y];
						}
						out.print(x + 1);
						for (int j = path2.size() - 1; j >= 0; j--) {
							out.print(' ');
							out.print(path2.get(j));
						}
						return true;
					}
					if (dfs(p, x, y, path)) {
						return true;
					}
				}
			}
			return false;
		}
	}
}
