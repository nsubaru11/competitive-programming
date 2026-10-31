import java.util.*;

import lib.ds.unionfind.*;
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
		int m = sc.nextInt();
		UnionFind uf = new UnionFind(n);
		HashMap<Integer, List<Pair>> hs = new HashMap<>();
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			if (uf.isConnected(a, b)) {
				hs.computeIfAbsent(uf.find(a), k -> new ArrayList<>()).add(new Pair(a, b, i));
			} else {
				int findA = uf.find(a);
				int findB = uf.find(b);
				uf.union(a, b);
				if (findA == uf.find(a)) {
					if (hs.containsKey(findB)) {
						hs.computeIfAbsent(findA, k -> new ArrayList<>()).addAll(hs.get(findB));
						hs.remove(findB);
					}
				} else {
					if (hs.containsKey(findA)) {
						hs.computeIfAbsent(findB, k -> new ArrayList<>()).addAll(hs.get(findA));
						hs.remove(findA);
					}
				}
			}
		}
		out.println(uf.groupCount() - 1);
		int root = 0;
		List<Pair> pairs0 = null;
		for (int h : hs.keySet()) {
			if (root == 0) {
				root = ~h;
				pairs0 = hs.get(h);
			} else {
				Pair pair = pairs0.remove(0);
				uf.union(pair.a, h);
				out.print(pair.c + 1, pair.a + 1, h + 1).println();
				pairs0.addAll(hs.get(h));
			}
		}
		if (uf.groupCount() > 1) {
			root = ~root;
			for (int i = 0; i < n; i++) {
				if (!uf.isConnected(uf.find(i), root)) {
					Pair pair = pairs0.remove(0);
					uf.union(pair.a, i);
					out.print(pair.c + 1, pair.a + 1, i + 1).println();
				}
			}
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
		int a, b, c;

		Pair(int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}
	}
}
