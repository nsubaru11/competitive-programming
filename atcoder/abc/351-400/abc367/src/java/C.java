import lib.io.*;

public class C {

	private static final FastPrinter out = new FastPrinter();
	private static final FastScanner sc = new FastScanner();
	private static int n, k, sum;
	private static int[] R, a;

	public static void main(String[] args) {
		n = sc.nextInt();
		k = sc.nextInt();
		R = sc.nextInt(n);
		a = new int[n];
		search(0);
		out.flush();
	}

	private static void search(int idx) {
		if (idx < n) {
			for (a[idx] = 1; a[idx] <= R[idx]; a[idx]++) {
				sum++;
				search(idx + 1);
			}
			sum -= R[idx];
		} else {
			if (sum % k == 0) {
				out.println(a);
			}
		}
	}
}
