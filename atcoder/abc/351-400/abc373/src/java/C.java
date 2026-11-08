import lib.io.*;

import static java.lang.Math.*;

public class C {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int a = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			a = max(a, sc.nextInt());
		}
		int b = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			b = max(b, sc.nextInt());
		}
		System.out.println(a + b);
	}
}
