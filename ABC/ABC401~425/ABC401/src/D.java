import lib.io.*;

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
		int k = sc.nextInt();
		char[] c = sc.nextChars(n);
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if (c[i] == 'o') {
				if (i - 1 >= 0 && c[i - 1] == '?') {
					c[i - 1] = '.';
				}
				if (i + 1 < n && c[i + 1] == '?') {
					c[i + 1] = '.';
				}
				cnt++;
			}
		}
		if (cnt == k) {
			for (int i = 0; i < n; i++) {
				if (c[i] == '?') {
					c[i] = '.';
				}
			}
		} else {
			int cnt2 = 0;
			boolean f = false;
			int max = 0;
			int j = 0;
			for (int i = 0; i < n; i++) {
				if (c[i] == '?') {
					cnt2++;
					f = true;
					j++;
				} else {
					if (f) {
						max += (j + 1) / 2;
					}
					f = false;
					j = 0;
				}
			}
			if (f) {
				max += (j + 1) / 2;
			}
			if (cnt2 == k - cnt) {
				for (int i = 0; i < n; i++) {
					if (c[i] == '?') {
						c[i] = 'o';
					}
				}
			} else if (max == k - cnt) {
				for (int i = 0; i < n; ) {
					if (c[i] == '?') {
						int l = 1;
						while (i + l < n && c[i + l] == '?') {
							l++;
						}
						if (l % 2 == 1) {
							for (int ll = 0; ll < l; ll++) {
								c[i + ll] = ll % 2 == 0 ? 'o' : '.';
							}
						}
						i += l;
					} else {
						i++;
					}
				}
			}
		}
		for (int i = 0; i < n; i++) {
			out.print(c[i]);
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
