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
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			boolean[] s = new boolean[1 << n];
			for (int i = 0; i < (1 << n) - 1; i++)
				s[i + 1] = sc.nextChar() == '1';
			HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
			map.computeIfAbsent(0, k -> new HashSet<>()).add(0);
			for (int i = 1; i <= n; i++) {
				map.put(i, new HashSet<>());
				for (int k : map.get(i - 1)) {
					for (int j = 0; j < n; j++) {
						if ((k & (1 << j)) == 0) {
							int next = k | (1 << j);
							if (!s[next]) {
								map.get(i).add(next);
							}
						}
					}
				}
			}
			out.println(!map.get(n).isEmpty());
		}
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
