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
		int n = sc.nextInt();
		int m = sc.nextInt();
		int q = sc.nextInt();
		boolean[] all = new boolean[n];
		HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
		while (q-- > 0) {
			int t = sc.nextInt();
			switch (t) {
				case 1:
					int a = sc.nextInt() - 1;
					int b = sc.nextInt() - 1;
					map.computeIfAbsent(a, k -> new HashSet<>()).add(b);
					break;
				case 2:
					a = sc.nextInt() - 1;
					all[a] = true;
					break;
				case 3:
					a = sc.nextInt() - 1;
					b = sc.nextInt() - 1;
					if (all[a]) {
						out.println("Yes");
					} else {
						if (map.containsKey(a)) {
							if (map.get(a).contains(b)) {
								out.println("Yes");
								break;
							}
						}
						out.println("No");
					}
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
}
