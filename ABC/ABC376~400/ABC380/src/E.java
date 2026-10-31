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
		int q = sc.nextInt();
		UnionFind uf = new UnionFind(n);
		int[] a = new int[n];
		while (q-- > 0) {
			int k = sc.nextInt();
			switch (k) {
				case 1:
					int x = sc.nextInt() - 1;
					int c = sc.nextInt() - 1;
					uf.union(c, x);
					break;
				case 2:
					c = sc.nextInt() - 1;
					out.println(uf.size(c));
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
}
