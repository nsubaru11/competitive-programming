import lib.io.*;

import static java.lang.Math.*;

public class C {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[] a = sc.nextInt(n);
		int[] max = new int[n];
		int cost = 0;
		for (int i = 0; i < n; i++) {
			int w = sc.nextInt();
			cost += min(max[a[i] - 1], w);
			max[a[i] - 1] = max(max[a[i] - 1], w);
		}
		System.out.println(cost);
	}
}
