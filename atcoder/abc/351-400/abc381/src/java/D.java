import java.util.*;

import lib.io.*;

import static java.lang.Math.*;

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
		int n = sc.nextInt();
		int[] a = sc.nextInt(n);
		HashMap<Integer, Integer> hm = new HashMap<>();
		int max = 0;
		int cnt = 0;
		for (int i = 0; i < n; ) {
			if (i + 1 < n && a[i] == a[i + 1]) {
				if (i + 2 < n && a[i + 1] == a[i + 2]) {
					if (hm.getOrDefault(a[i], -1) < i - cnt) {
						cnt += 2;
					}
					max = max(max, cnt);
					cnt = 2;
					while (i + 2 < n && a[i + 1] == a[i + 2]) {
						i++;
					}
				} else if (hm.getOrDefault(a[i], -1) < i - cnt) {
					cnt += 2;
				} else {
					max = max(max, cnt);
					cnt = i - hm.get(a[i]);
				}
				hm.put(a[i], i);
				i += 2;
			} else {
				i++;
				max = max(max, cnt);
				cnt = 0;
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
