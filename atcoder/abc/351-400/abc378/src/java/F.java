import java.util.*;

import lib.ds.unionfind.*;
import lib.io.*;

public class F {

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
		int[] u = new int[n - 1];
		int[] v = new int[n - 1];
		int[] deg = new int[n];// 各頂点の次数を記憶
		for (int i = 0; i < n - 1; i++) {
			u[i] = sc.nextInt() - 1;
			v[i] = sc.nextInt() - 1;
			deg[u[i]]++;
			deg[v[i]]++;
		}
		UnionFind uf = new UnionFind(n);// 連結する次数3の頂点をグループ化
		int[] c = new int[n];// 次数3に連結する次数2の頂点数を記憶
		for (int i = 0; i < n - 1; i++) {
			if (deg[u[i]] == deg[v[i]] && deg[u[i]] == 3) {
				uf.union(u[i], v[i]);
			} else if (deg[u[i]] == 2 && deg[v[i]] == 3) {
				c[v[i]]++;
			} else if (deg[u[i]] == 3 && deg[v[i]] == 2) {
				c[u[i]]++;
			}
		}
		long ans = 0;
		for (List<Integer> values : uf.groups().values()) {
			int cnt = 0;
			for (int value : values) {
				cnt += c[value];
			}
			ans += (long) cnt * (cnt - 1) / 2;
		}
		System.out.println(ans);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
