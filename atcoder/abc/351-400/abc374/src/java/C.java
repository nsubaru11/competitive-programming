import lib.io.*;

import static java.lang.Math.*;

public class C {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[] a = sc.nextInt(n);
		System.out.println(divide(n, a, 0, 0));
	}

	private static int divide(int n, int[] a, int sum1, int sum2) {
		if (n == 0) {
			return max(sum1, sum2);
		}
		return min(divide(n - 1, a, sum1 + a[n - 1], sum2), divide(n - 1, a, sum1, sum2 + a[n - 1]));
	}
}
