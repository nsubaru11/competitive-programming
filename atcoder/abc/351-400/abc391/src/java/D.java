import static java.lang.Math.*;

import java.util.*;

import lib.io.*;

public class D {

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
		int w = sc.nextInt();
		PriorityQueue<Pair>[] pq0 = new PriorityQueue[w];
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt() - 1;
			int y = sc.nextInt() - 1;
			if (pq0[x] == null) pq0[x] = new PriorityQueue<>();
			pq0[x].add(new Pair(i, y));
		}
		int[] rank = new int[n]; // 箱番号->下から何番目
		Map<Integer, Integer> vanish = new HashMap<>(); // 下から何番目->消滅時間
		for (int i = 0, k = 0; k < n; i++) {
			int max = 0;
			for (int j = 0; j < w; j++) {
				PriorityQueue<Pair> pq = pq0[j];
				if (pq == null || pq.isEmpty()) {
					max = Integer.MAX_VALUE;
					continue;
				}
				Pair pair = pq.poll();
				rank[pair.index] = i;
				max = max(max, pair.y);
				k++;
			}
			vanish.put(i, max);
		}
		int q = sc.nextInt();
		while (q-- > 0) {
			int t = sc.nextInt();
			int a = sc.nextInt() - 1;
			out.println(vanish.get(rank[a]) > t - 1 ? "Yes" : "No");
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

	private static class Pair implements Comparable<Pair> {
		int index, y;

		Pair(int index, int y) {
			this.index = index;
			this.y = y;
		}

		@Override
		public int compareTo(Pair o) {
			return y - o.y;
		}
	}
}
