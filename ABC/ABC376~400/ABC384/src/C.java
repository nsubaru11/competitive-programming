import java.util.*;

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

	private static int[] point;
	private static PriorityQueue<Pair> pq;

	private static void solve() {
		point = sc.nextInt(5);
		pq = new PriorityQueue<>();
		comb(0, "", 0);
		while (!pq.isEmpty()) {
			out.println(pq.poll());
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

	private static void comb(int i, String s, int p) {
		if (i == 5) return;
		comb(i + 1, s + Character.toString('A' + i), p + point[i]);
		comb(i + 1, s, p);
		pq.add(new Pair(s + Character.toString('A' + i), p + point[i]));
	}

	private static class Pair implements Comparable<Pair> {
		String name;
		int point;

		Pair(String name, int point) {
			this.name = name;
			this.point = point;
		}

		@Override
		public String toString() {
			return name;
		}

		@Override
		public int compareTo(Pair o) {
			if (point == o.point) {
				return name.compareTo(o.name);
			} else {
				return o.point - point;
			}
		}
	}
}
