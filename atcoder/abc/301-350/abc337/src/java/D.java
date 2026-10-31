import static java.lang.Math.*;

import java.util.*;

import lib.io.*;

public final class D {

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
		int h = sc.nextInt();
		int w = sc.nextInt();
		int k = sc.nextInt();
		char[] mat = sc.nextChars(h * w);
		int ans = Integer.MAX_VALUE;
		if (w >= k) {
			for (int i = 0, ij = 0; i < h; i++) {
				ArrayDeque<Character> q = new ArrayDeque<>(k);
				int a = 0, b = 0; // a -> . , b -> x
				for (int j = 0; j < k; j++, ij++) {
					char m = mat[ij];
					if (m == '.') a++;
					else if (m == 'x') b++;
					q.addLast(m);
				}
				for (int j = k; j < w; j++, ij++) {
					if (b == 0) ans = min(ans, a);
					char o = q.pollFirst();
					if (o == '.') a--;
					else if (o == 'x') b--;
					char in = mat[ij];
					if (in == '.') a++;
					else if (in == 'x') b++;
					q.addLast(in);
				}
				if (b == 0) ans = min(ans, a);
			}
		}
		if (h >= k) {
			for (int j = 0, ij = 0; j < w; ij = ++j) {
				ArrayDeque<Character> q = new ArrayDeque<>(k);
				int a = 0, b = 0; // a -> . , b -> x
				for (int i = 0; i < k; i++, ij += w) {
					char m = mat[ij];
					if (m == '.') a++;
					else if (m == 'x') b++;
					q.addLast(m);
				}
				for (int i = k; i < h; i++, ij += w) {
					if (b == 0) ans = min(ans, a);
					char o = q.pollFirst();
					if (o == '.') a--;
					else if (o == 'x') b--;
					char in = mat[ij];
					if (in == '.') a++;
					else if (in == 'x') b++;
					q.addLast(in);
				}
				if (b == 0) ans = min(ans, a);
			}
		}
		out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
