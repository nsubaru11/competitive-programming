import java.util.*;

import lib.io.*;

import static java.lang.Math.*;

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
		int l = sc.nextInt();
		Pair[] a = new Pair[n];
		Pair[] b = new Pair[m];
		for (int i = 0; i < n; i++)
			a[i] = new Pair(i, sc.nextInt());
		for (int i = 0; i < m; i++)
			b[i] = new Pair(i, sc.nextInt());
		Arrays.sort(a);
		Arrays.sort(b);
		HashMap<Integer, HashSet<Integer>> hm = new HashMap<>();
		for (int i = 0; i < l; i++) {
			int c = sc.nextInt() - 1;
			int d = sc.nextInt() - 1;
			hm.computeIfAbsent(c, k -> new HashSet<>()).add(d);
		}
		int ans = 0;
		for (int i = n - 1; i >= 0; i--) {
			for (int j = m - 1; j >= 0; j--) {
				if (ans >= a[i].j + b[j].j) break;
				if (hm.containsKey(a[i].i) && hm.get(a[i].i).contains(b[j].i)) continue;
				ans = max(ans, a[i].j + b[j].j);
			}
		}
		out.println(ans);
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
