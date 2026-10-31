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
		int q = sc.nextInt();
		ArrayList<Pair> al = new ArrayList<>();
		int l = 0, r = 1;
		al.add(new Pair(0));
		al.add(new Pair(0));
		while (q-- > 0) {
			int n = sc.nextInt();
			switch (n) {
				case 1:
					al.get(r).cnt++;
					break;
				case 2:
					al.get(r).t += sc.nextInt();
					al.add(new Pair(0));
					r++;
					al.get(r).cnt = al.get(r - 1).cnt;
					break;
				case 3:
					int h = sc.nextInt();
					int idx = r;
					long sum = 0;
					while (idx >= l && sum < h) {
						sum += al.get(idx).t;
						idx--;
					}
					if (sum >= h) {
						out.println(al.get(idx + 1).cnt - al.get(l).cnt);
						l = idx + 1;
					} else {
						out.println(0);
					}
					break;
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

	private static class Pair {
		long t;
		int cnt;

		public Pair(int t) {
			this.t = t;
			this.cnt = 0;
		}
	}
}
