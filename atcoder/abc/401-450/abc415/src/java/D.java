import static java.lang.Long.*;

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
		long n = sc.nextLong();
		int m = sc.nextInt();
		Pair[] p = new Pair[m];
		for (int i = 0; i < m; i++) {
			p[i] = new Pair(sc.nextLong(), sc.nextLong());
		}
		long ans = 0;
		while (n > 0) {
			int k = 0;
			for (int i = 1; i < m; i++) {
				if (n / p[k].i * p[k].j + (n % p[k].i) < n / p[i].i * p[i].j + (n % p[i].j)) {
					k = i;
				}
				if (n / p[k].i * p[k].j + (n % p[k].i) == n / p[i].i * p[i].j + (n % p[i].j)) {
					k = n / p[k].i > n / p[i].i ? k : i;
				}
			}
			long temp = n / p[k].i * p[k].j + n % p[k].i;
			if (n / p[k].i == 0) break;
			ans += n - n % p[k].i;
			n = temp;
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
		long i, j;

		public Pair(long i, long j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public int compareTo(Pair o) {
			int t = compare(i, o.i);
			return t == 0 ? compare(j, o.j) : t;
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
