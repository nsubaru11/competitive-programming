import lib.io.*;

public class D {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int m = sc.nextInt();
		long[] a = sc.nextSortedLong(n);
		long[] b = sc.nextSortedLong(m);
		long ans = 0;
		for (int i = 0, j = 0; i < m; i++) {
			for (; j < n; j++) {
				if (a[j] >= b[i]) {
					ans += a[j];
					j++;
					break;
				}
				if (j == n - 1) {
					System.out.println(-1);
					return;
				}
			}
			if (j == n && i < m - 1) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(ans);

	}
}
