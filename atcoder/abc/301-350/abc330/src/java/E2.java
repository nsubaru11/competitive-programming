import java.util.*;

import lib.io.*;

public final class E2 {

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
		int q = sc.nextInt();
		int[] a = new int[n];
		int[] cnt = new int[n + 1];
		TreeSet<Integer> ts = new TreeSet<>();
		for (int i = 0; i < n; i++) {
			int k = sc.nextInt();
			a[i] = k;
			if (k < n) cnt[k]++;
		}
		for (int i = 0; i < n + 1; i++) {
			if (cnt[i] == 0) ts.add(i);
		}
		while (q-- > 0) {
			int i = sc.nextInt() - 1;
			int x = sc.nextInt();
			if (a[i] < n) {
				cnt[a[i]]--;
				if (cnt[a[i]] == 0) ts.add(a[i]);
			}
			a[i] = x;
			if (x < n) {
				cnt[x]++;
				ts.remove(x);
			}
			int ans = ts.isEmpty() ? n : ts.first();
			out.println(ans);
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
