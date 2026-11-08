import lib.io.*;

import static java.lang.Math.*;

public class C2 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		//recursive
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			String s = sc.next();
			for (int j = 0; j < m; j++) {
				if (s.charAt(j) == 'o') {
					a[i] |= 1 << (j);
				}
			}
		}
		int ans = visitCount(a, n, m, 0, 0);
		System.out.println(ans);
	}

	private static int visitCount(int[] a, int n, int m, int idx, int taste) {
		int ans = n;
		if (taste == ((1 << m) - 1)) {
			return 0;
		} else if (idx == n) {
			return n;
		}
		ans = min(ans, visitCount(a, n, m, idx + 1, taste | a[idx]) + 1);
		ans = min(ans, visitCount(a, n, m, idx + 1, taste));

		return ans;
	}
}
