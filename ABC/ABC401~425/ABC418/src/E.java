import java.util.*;

import lib.io.*;

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
		int[][] xy = sc.nextIntMat(n, 2);
		HashMap<Long, HashMap<Long, Integer>> map = new HashMap<>();
		HashMap<Long, Integer> map2 = new HashMap<>();
		long ans = 0, cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int x1 = xy[i][0], y1 = xy[i][1], x2 = xy[j][0], y2 = xy[j][1];
				if (x1 > x2 || (x1 == x2 && y1 > y2)) {
					int t = x1;
					x1 = x2;
					x2 = t;
					t = y1;
					y1 = y2;
					y2 = t;
				}
				int dx = x2 - x1, dy = y2 - y1;
				int gcd = gcd(dx, dy < 0 ? -dy : dy);
				long temp = dx / gcd * 20000000L + dy / gcd + 10000000;
				int count = map2.getOrDefault(temp, 0);
				ans += count;
				long dxy = (long) dx * dx + (long) dy * dy;
				map2.put(temp, count + 1);
				HashMap<Long, Integer> map3 = map.computeIfAbsent(temp, k -> new HashMap<>());
				int k = map3.getOrDefault(dxy, 0);
				ans -= k;
				cnt += k;
				map3.put(dxy, k + 1);
			}
		}
		out.println(ans + cnt / 2);
	}

	private static int gcd(int a, int b) {
		return b <= 0 ? a : gcd(b, a % b);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
