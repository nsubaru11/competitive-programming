import lib.io.*;

import static java.lang.Math.*;

public class C {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] a = sc.nextSortedInt(n);
		int ans = a[n - 1] - a[k];
		for (int i = 1; k - i >= 0; i++) {
			ans = min(ans, a[n - 1 - i] - a[k - i]);
		}
		System.out.println(ans);
	}
}
