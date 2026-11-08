import java.util.*;

import lib.io.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public class D {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter(true);
	// endregion

	private static void solve() {
		int n = sc.nextInt();
		HashMap<Integer, Integer> hm = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			hm.put(a, hm.getOrDefault(a, 0) + 1);
		}

		int[][] a = new int[n][];
		for (int i = 0; i < n; i++) {
			int k = sc.nextInt();
			a[i] = sc.nextInt(k);
			sort(a[i]);
		}
		double max = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				long cnt = 0;
				int iLen = a[i].length;
				int jLen = a[j].length;
				for (int k = 0, l = 0; k < iLen; k++) {
					while (l < jLen && a[i][k] > a[j][l]) {
						l++;
					}
					if (l < jLen && a[i][k] == a[j][l]) {
						long cnt1 = 1;
						while (k + 1 < iLen && a[i][k] == a[i][k + 1]) {
							k++;
							cnt1++;
						}
						long cnt2 = 1;
						while (l + 1 < jLen && a[j][l] == a[j][l + 1]) {
							l++;
							cnt2++;
						}
						cnt += cnt1 * cnt2;
					}
				}
				double tmp = (double) cnt / ((long) iLen * jLen);
				max = max(max, tmp);
			}
		}
		out.println(max);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
