import lib.ds.unionfind.*;
import lib.io.*;

public class C {

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
		int[] degree = new int[n];
		UnionFind uf = new UnionFind(n);
		while (m-- > 0) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			degree[a]++;
			degree[b]++;
			uf.union(a, b);
		}
		for (int i = 0; i < n; i++) {
			if (degree[i] != 2) {
				out.println("No");
				return;
			}
		}
		out.println(uf.groupCount() == 1);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
