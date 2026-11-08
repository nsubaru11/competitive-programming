import lib.io.*;

import static java.lang.Math.*;

public class C3 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		//bit X
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			String s = sc.next();
			for (int j = 0; j < m; j++) {
				if (s.charAt(j) == 'o') {
					a[i] |= 1 << j;
				}
			}
		}
		int ans = n;
		int full = (1 << m) - 1;
		for (int i = 0; i < 1 << n; i++) {
			int idx = i;
			int taste = 0;
			int cnt = 0;
			for (int x = idx; x > 0; x &= x - 1) {
				int j = Integer.bitCount((x & (~x + 1)) - 1);
				taste |= a[j];
				cnt++;
			}
			if (taste == full) {
				ans = min(ans, cnt);
			}
		}
		System.out.println(ans);
	}
}
