import java.util.*;

import lib.io.*;

public class D {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		FastPrinter out = new FastPrinter();

		int n = sc.nextInt();
		long[] l = new long[n], r = new long[n];
		for (int i = 0; i < n; i++) {
			l[i] = sc.nextLong();
			r[i] = sc.nextLong();
		}
		long sum = (long) n * (n - 1L) / 2L;
		Arrays.sort(l);
		Arrays.sort(r);
		for (int i = 0, j = 0; i < n; i++) {
			while (l[i] > r[j]) {
				j++;
			}
			sum -= j;
		}
		out.println(sum);
		out.flush();
	}
}
