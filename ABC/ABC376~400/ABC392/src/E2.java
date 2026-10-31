import java.util.*;

import lib.ds.unionfind.*;
import lib.io.*;

public class E2 {

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
		int m = sc.nextInt();
		UnionFind nodes = new UnionFind(n);
		HashSet<Integer> roots = new HashSet<>(n);
		for (int i = 0; i < n; i++) {
			roots.add(i);
		}
		List<Pair> pairList = new ArrayList<>(m);
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			if (nodes.isConnected(a, b)) {
				pairList.add(new Pair(i, a, b));
			} else {
				int findA = nodes.find(a);
				int findB = nodes.find(b);
				nodes.union(a, b);
				if (findA == nodes.find(a)) {
					roots.remove(findB);
				} else {
					roots.remove(findA);
				}
			}
		}
		out.println(nodes.groupCount() - 1);
		int i = 0;
		int a = nodes.find(0);
		for (int r : roots) {
			if (r == a) continue;
			Pair pair = pairList.get(i);
			if (nodes.isConnected(pair.a, r)) {
				nodes.union(a, pair.a);
				out.print(pair.i + 1 + " ");
				out.print(pair.a + 1 + " ");
				out.println(1);
			} else {
				nodes.union(pair.a, r);
				out.print(pair.i + 1 + " ");
				out.print(pair.a + 1 + " ");
				out.println(r + 1);
			}
			i++;
		}
		out.flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static class Pair {
		int i, a, b;

		Pair(int i, int a, int b) {
			this.i = i;
			this.a = a;
			this.b = b;
		}
	}
}
