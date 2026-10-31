import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.util.*;

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
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			int[] a = sc.nextInt(n);
			Arrays.sort(a);
			if (isGeometricSequence(a, n)) {
				out.println("Yes");
				continue;
			}
			int zeroIdx = ~binarySearch(a, 0);
			int[] b = new int[n];
			if (n % 2 == 0) {
				if (zeroIdx == n / 2) {
					if (abs(a[zeroIdx - 1]) > abs(a[zeroIdx])) {
						for (int i = 0; i < n; i += 2) {
							b[i] = a[zeroIdx + i / 2];
							b[i + 1] = a[zeroIdx - (i + 2) / 2];
						}
					} else {
						for (int i = 0; i < n; i += 2) {
							b[i] = a[zeroIdx - (i + 2) / 2];
							b[i + 1] = a[zeroIdx + i / 2];
						}
					}
				} else {
					out.println("No");
					continue;
				}
			} else {
				if (zeroIdx == n / 2) {
					b[0] = a[zeroIdx];
					for (int i = 1; i < n; i += 2) {
						b[i] = a[zeroIdx - (i + 1) / 2];
						b[i + 1] = a[zeroIdx + (i + 2) / 2];
					}
				} else if (zeroIdx == n / 2 + 1) {
					b[0] = a[zeroIdx - 1];
					for (int i = 1; i < n; i += 2) {
						b[i] = a[zeroIdx + (i - 1) / 2];
						b[i + 1] = a[zeroIdx - (i + 3) / 2];
					}
				} else {
					out.println("No");
					continue;
				}
			}
			out.println(isGeometricSequence(b, n));
		}
	}

	private static boolean isGeometricSequence(final int[] a, int n) {
		int numer = a[1];
		int denom = a[0];
		for (int i = 1; i < n - 1; i++) {
			if ((long) a[i] * numer % denom != 0) {
				return false;
			}
			if ((long) a[i] * numer / denom != a[i + 1]) {
				return false;
			}
		}
		return true;
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
