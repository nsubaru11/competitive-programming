import static java.lang.Math.*;

import java.util.*;

import lib.io.*;

public class D2 {

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
		HashMap<Integer, Integer> hm = new HashMap<>();
		int max = 0;
		int cnt = 0;
		int pre = -1;
		int cur = 0;
		for (int i = 0; i < n; ) {
			while (i < n && pre != cur) {
				pre = cur;
				cur = sc.nextInt();
				i++;
				cnt = 0;
			}
			while (i < n && cur == pre && hm.getOrDefault(cur, -1) < i - cnt) {
				if (i == n - 1) break;
				pre = sc.nextInt();
				cur = sc.nextInt();
				hm.put(cur, i);
				i += 2;
				cnt += 2;
			}
			max = max(cnt, max);
			while (i < n && cur == pre && hm.getOrDefault(cur, -1) >= i - cnt) {
				pre = cur;
				cur = sc.nextInt();
				hm.put(cur, i);
				i++;
			}
		}
		System.out.println(max(max, cnt));
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
