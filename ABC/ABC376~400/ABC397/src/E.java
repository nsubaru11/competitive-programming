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
		int n = sc.nextInt();
		int k = sc.nextInt();
		List<List<Edge>> tree = new ArrayList<>(n * k);
		for (int i = 0; i < n * k; i++) {
			tree.add(new ArrayList<>());
		}
		for (int i = 1; i < n * k; i++) {
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			tree.get(u).add(new Edge(u, v));
			tree.get(v).add(new Edge(v, u));
		}
		Deque<Integer> stack = new ArrayDeque<>();
		int[] memo = new int[n * k];
		for (List<Edge> edges : tree) {
			if (edges.size() == 1) {
				int u = edges.get(0).u;
				stack.push(u);
				memo[u] = 1;
				break;
			}
		}
		while (!stack.isEmpty()) {
			int u = stack.pop();
			if (memo[u] == k) {
				for (Edge edge : tree.get(u)) {
					if (memo[edge.v] == 0) {
						stack.push(edge.v);
						memo[edge.v] = 1;
					}
				}
			}
			int cnt = 0;
			int v = 0;
			boolean found = false;
			int cnt2 = 0;
			int v2 = 0;
			boolean found2 = false;
			for (Edge edge : tree.get(u)) {
				if (memo[edge.v] == 0) {
					cnt++;
					v = edge.v;
					found = true;
				} else {
					if (memo[edge.v] + memo[u] == k) {
						cnt2++;
						found2 = true;
						v2 = edge.v;
					}
				}
			}
			if (!found) {
				out.println("No");
				return;
			}
			if (cnt == 1) {
				memo[v] = memo[u] + 1;
				stack.push(v);
			}
		}
		int cnt = 0;
		for (int m : memo) {
			if (m == k) cnt++;
		}
		out.println(cnt != n ? "No" : "Yes");
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static class Edge {
		int u, v;

		Edge(int u, int v) {
			this.u = u;
			this.v = v;
		}
	}
}
