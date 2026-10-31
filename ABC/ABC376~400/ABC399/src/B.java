import java.util.*;

import lib.io.*;

public class B {

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
		Pair[] p = new Pair[n];
		for (int i = 0; i < n; i++) {
			p[i] = new Pair(i, sc.nextInt());
		}
		Arrays.sort(p);
		int r = 1;
		int[] rank = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			rank[p[i].i] = r;
			int k = 1;
			while (i - 1 >= 0 && p[i].j == p[i - 1].j) {
				rank[p[i - 1].i] = r;
				i--;
				k++;
			}
			r += k;
		}
		out.println(rank);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static final class Pair implements Comparable<Pair> {
		int i, j;

		public Pair(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public int compareTo(Pair o) {
			return Integer.compare(j, o.j);
		}

		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Pair other)) return false;
			return i == other.i && j == other.j;
		}

		public String toString() {
			return i + " " + j;
		}
	}
}
