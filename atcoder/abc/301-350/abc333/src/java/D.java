import java.util.*;

import lib.io.*;

import static java.lang.Math.*;

public final class D {

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
		List<Set<Integer>> tree = new ArrayList<>(n);
		for (int i = 0; i < n; i++) tree.add(new HashSet<>());
		for (int i = 0; i < n - 1; i++) {
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			tree.get(u).add(v);
			tree.get(v).add(u);
		}
		if (tree.get(0).size() == 1) {
			out.println(1);
			return;
		}
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			if (tree.get(i).size() == 1) dq.add(i);
		}
		int[] count = new int[n];
		while (!dq.isEmpty()) {
			int v = dq.poll();
			int to = tree.get(v).iterator().next(); // 葉に繋がっている頂点は1つだけ
			if (to == 0) continue;
			tree.get(to).remove(v);
			count[to] += count[v] + 1;
			if (tree.get(to).size() == 1) dq.add(to);
		}
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for (int v : tree.get(0)) {
			max = max(max, count[v] + 1);
			sum += count[v] + 1;
		}
		out.println(sum - max + 1);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
