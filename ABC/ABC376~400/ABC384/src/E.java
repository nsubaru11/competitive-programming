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
		// input
		int h = sc.nextInt();
		int w = sc.nextInt();
		long x = sc.nextLong();
		int p = sc.nextInt() - 1;
		int q = sc.nextInt() - 1;
		long[][] s = sc.nextLongMat(h, w);

		boolean[][] visited = new boolean[h][w];
		long ans = s[p][q];
		visited[p][q] = true;

		PriorityQueue<tuple> pq = new PriorityQueue<>();
		int[] di = {1, 0, -1, 0};
		int[] dj = {0, 1, 0, -1};
		for (int i = 0; i < 4; i++) {
			int ni = p + di[i];
			int nj = q + dj[i];
			if (isRange(ni, nj, h, w)) pq.add(new tuple(ni, nj, s[ni][nj]));
		}

		while (!pq.isEmpty()) {
			tuple t = pq.poll();
			int i = t.i;
			int j = t.j;
			long sij = t.strength;
			if (visited[i][j]) continue;
			if (ceilLong(ans, x) > sij) {
				ans += sij;
				visited[i][j] = true;
				for (int k = 0; k < 4; k++) {
					int ni = i + di[k];
					int nj = j + dj[k];
					if (isRange(ni, nj, h, w)) pq.add(new tuple(ni, nj, s[ni][nj]));
				}
			} else {
				break;
			}
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

	private static boolean isRange(int i, int j, int h, int w) {
		return 0 <= i && i < h && 0 <= j && j < w;
	}

	/**
	 * a / b以上で最小の長整数を返します。
	 *
	 * @param a 割られる値(long)
	 * @param b 割る値(long)
	 * @return ⌈a / b⌉
	 */
	private static long ceilLong(long a, long b) {
		return a < 0 ? a / b : (a + b - 1) / b;
	}

	private static class tuple implements Comparable<tuple> {
		int i, j;
		long strength;

		tuple(int i, int j, long strength) {
			this.i = i;
			this.j = j;
			this.strength = strength;
		}

		@Override
		public int compareTo(tuple o) {
			return Long.compare(strength, o.strength);
		}
	}
}
