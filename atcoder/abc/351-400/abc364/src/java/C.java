import lib.io.*;

public class C {

	private static FastScanner sc = new FastScanner();
	private static FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		long x = sc.nextLong(), y = sc.nextLong();
		int[] a = sc.nextSortedInt(n);
		int[] b = sc.nextSortedInt(n);
		int cnt = 0, cnt2 = 0;
		for (int i = n - 1; i >= 0; i--) {
			if (x - a[i] >= 0) {
				x -= a[i];
				cnt++;
			} else if (x >= 0) {
				x -= a[i];
				cnt++;
			}
			if (y - b[i] >= 0) {
				y -= b[i];
				cnt2++;
			} else if (y >= 0) {
				y -= b[i];
				cnt2++;
			}
		}
		out.println(Math.min(cnt, cnt2));
		out.flush();
	}
}
