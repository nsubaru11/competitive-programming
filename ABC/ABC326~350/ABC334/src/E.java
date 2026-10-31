import java.util.*;

import lib.ds.unionfind.*;
import lib.io.*;

public final class E {

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
		int h = sc.nextInt();
		int w = sc.nextInt();
		char[] grid = sc.nextChars(h * w);
		UnionFind uf = new UnionFind(h * w);
		int mod = 998244353;
		for (int i = 0, ij = 0; i < h; i++) {
			for (int j = 0; j < w; j++, ij++) {
				if (grid[ij] == '#') {
					if (j + 1 < w && grid[ij + 1] == '#') uf.union(ij, ij + 1);
					if (i + 1 < h && grid[ij + w] == '#') uf.union(ij, ij + w);
				}
			}
		}
		int red = 0, delta = 0;
		for (int i = 0, ij = 0; i < h; i++) {
			for (int j = 0; j < w; j++, ij++) {
				if (grid[ij] == '.') {
					red++;
					Set<Integer> set = new HashSet<>(4);
					if (j + 1 < w && grid[ij + 1] == '#') set.add(uf.find(ij + 1));
					if (i + 1 < h && grid[ij + w] == '#') set.add(uf.find(ij + w));
					if (i - 1 >= 0 && grid[ij - w] == '#') set.add(uf.find(ij - w));
					if (j - 1 >= 0 && grid[ij - 1] == '#') set.add(uf.find(ij - 1));
					delta += 1 - set.size();
				}
			}
		}
		delta = (delta % mod + mod) % mod;
		int cnt = (uf.groupCount() - red) % mod;
		red = (int) modPow(red, mod - 2, mod);
		int ans = (int) ((((long) delta * red % mod) + cnt) % mod);
		out.println(ans);
	}

	/**
	 * a ^ b % mod を求めます。
	 *
	 * @param a   底
	 * @param b   指数
	 * @param mod 割る値(素数)
	 * @return a ^ b % mod
	 */
	public static long modPow(long a, long b, long mod) {
		long ans = 1;
		b %= mod - 1;
		for (; b > 0; a = a * a % mod, b >>= 1) {
			if ((b & 1) == 1) ans = ans * a % mod;
		}
		return ans;
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
