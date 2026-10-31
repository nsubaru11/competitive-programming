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
		TreeMap<Integer, Integer> tm = new TreeMap<>(Collections.reverseOrder());
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			tm.put(a, tm.getOrDefault(a, 0) + 1);
			map.put(a, i + 1);
		}
		int ans = -1;
		for (Map.Entry<Integer, Integer> entry : tm.entrySet()) {
			if (entry.getValue() == 1) {
				ans = map.get(entry.getKey());
				break;
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
}
