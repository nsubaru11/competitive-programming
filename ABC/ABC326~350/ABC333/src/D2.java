import static java.lang.Math.*;

import java.util.*;

import lib.ds.unionfind.*;
import lib.io.*;

public final class D2 {

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
		UnionFind uf = new UnionFind(n);
		HashSet<Integer> set = new HashSet<>(n);
		for (int i = 0; i < n - 1; i++) {
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			if (u == 0) set.add(v);
			else if (v == 0) set.add(u);
			else uf.union(u, v);
		}
		int max = Integer.MIN_VALUE;
		for (int v : set) max = max(max, uf.size(v));
		out.println(n - max);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
