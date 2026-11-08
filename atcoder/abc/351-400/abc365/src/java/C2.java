import lib.io.*;

public class C2 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		long m = sc.nextLong();
		int[] a = sc.nextSortedInt(n);
		long total = 0;
		for (int v : a) total += v;
		if (total <= m) {
			System.out.println("infinite");
		} else {
			long sum = 0;
			long sum_a = 0;
			for (int i = 0; i < n; sum_a += a[i], i++) {
				if (i > 0 && a[i] == a[i - 1]) continue;
				sum = sum_a + (long) a[i] * (n - i);
				if (sum > m) {
					long k = (m - sum_a) / (n - i);
					System.out.println(k);
					break;
				}
			}
		}
	}
}
