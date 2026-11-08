import lib.io.*;

import static java.lang.Math.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[][] a = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				a[i][j] = sc.nextInt() - 1;
			}
		}
		int i = 0;
		for (int j = 0; j < n; j++) {
			i = a[max(i, j)][min(i, j)];
		}
		System.out.println(i + 1);
	}
}
