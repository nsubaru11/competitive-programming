import java.util.*;

import lib.io.*;

import static java.lang.Math.*;

public final class E {

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
		List<ArrayDeque<Integer>> list = new ArrayList<>(n);
		for (int i = 0; i < n; i++) list.add(new ArrayDeque<>());
		char[] a = new char[n];
		for (int i = 0; i < n; i++) {
			int t = sc.nextInt();
			int x = sc.nextInt() - 1;
			if (t == 1) {
				list.get(x).addLast(i);
			} else {
				ArrayDeque<Integer> q = list.get(x);
				if (q.isEmpty()) {
					out.println(-1);
					return;
				}
				int y = q.pollLast();
				a[y] = '1';
				a[i] = '2';
			}
		}
		int ans = Integer.MIN_VALUE;
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (a[i] == '1') {
				cnt++;
				sb.append('1').append(' ');
			} else if (a[i] == '2') {
				ans = max(ans, cnt--);
			} else {
				sb.append('0').append(' ');
			}
		}
		ans = max(ans, cnt);
		sb.deleteCharAt(sb.length() - 1);
		out.println(ans).println(sb);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
