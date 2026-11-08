import lib.io.*;

public class B {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		FastPrinter out = new FastPrinter();

		int n = sc.nextInt();
		int t = sc.nextInt();
		int p = sc.nextInt();

		int[] a = sc.nextSortedInt(n);
		int ans = t - a[n - p];
		out.println((ans <= 0) ? 0 : ans);
		out.flush();
	}
}
