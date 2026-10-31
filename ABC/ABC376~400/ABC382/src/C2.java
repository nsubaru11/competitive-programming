import java.util.*;

import lib.io.*;

public class C2 {

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
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int i = 0, prev = Integer.MAX_VALUE; i < n; i++) {
			int a = sc.nextInt();
			if (prev > a) {
				map.put(a, i + 1);
				prev = a;
			}
		}
		while (m-- > 0) {
			int a = sc.nextInt();
			Map.Entry<Integer, Integer> entry = map.floorEntry(a);
			out.println(entry == null ? -1 : entry.getValue());
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
}
