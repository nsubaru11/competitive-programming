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

	private static void solve() {
		int q = sc.nextInt();
		ArrayDeque<Pair> pairs = new ArrayDeque<>();
		while (q-- > 0) {
			int t = sc.nextInt();
			switch (t) {
				case 1:
					pairs.add(new Pair(sc.nextInt(), sc.nextInt()));
					break;
				case 2:
					int k = sc.nextInt();
					long sum = 0;
					while (k > 0) {
						if (pairs.peekFirst().c <= k) {
							Pair p = pairs.pollFirst();
							sum += (long) p.c * p.x;
							k -= p.c;
						} else {
							final Pair p = pairs.peekFirst();
							p.c -= k;
							sum += (long) p.x * k;
							k = 0;
						}
					}
					out.println(sum);
					break;
			}
		}
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static final class Pair implements Comparable<Pair> {
		int c;
		int x;

		public Pair(int c, int x) {
			this.c = c;
			this.x = x;
		}

		@Override
		public int compareTo(Pair o) {
			int t = Integer.compare(c, o.c);
			return t == 0 ? Integer.compare(x, o.x) : t;
		}

		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Pair other)) return false;
			return c == other.c && x == other.x;
		}

		public String toString() {
			return c + " " + x;
		}
	}
}
