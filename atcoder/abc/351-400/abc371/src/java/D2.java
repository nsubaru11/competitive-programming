import lib.io.*;

import static java.util.Arrays.*;

public class D2 {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[] x = sc.nextInt(n);
		long[] p = sc.nextLongPrefixSum(n);
		int q = sc.nextInt();
		for (int i = 0; i < q; i++) {
			int l = sc.nextInt();
			int r = sc.nextInt();
			l = binarySearch(x, l);
			r = binarySearch(x, r);
			if (l < 0 && r < 0) {
				out.println(r == -1 ? 0 : l == -1 ? p[~r - 1] : p[~r - 1] - p[~l - 1]);
			} else if (l >= 0 && r < 0) {
				out.println(l == 0 ? p[~r - 1] : p[~r - 1] - p[l - 1]);
			} else if (l < 0 && r >= 0) {
				out.println(l == -1 ? p[r] : p[r] - p[~l - 1]);
			} else {
				out.println(l == 0 ? p[r] : p[r] - p[l - 1]);
			}
		}
		out.flush();
	}
}
