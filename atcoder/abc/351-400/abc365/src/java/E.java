import lib.io.*;

public class E {

	private static FastScanner sc = new FastScanner();
	private static FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		long[] A = sc.nextLong(n);
		long[] a = new long[n];
		a[0] = A[0];
		for (int i = 1; i < n; i++) {
			a[i] = a[i - 1] ^ A[i];
		}
		long sum = 0;
		for (int i = 0; i < n; i++) {
			sum += (a[n - 1] - a[i]) + A[i];
		}
		out.println(sum);
		out.flush();
	}
}
