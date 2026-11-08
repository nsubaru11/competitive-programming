import lib.io.*;

import static java.lang.Math.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt(), t = sc.nextInt(), a = sc.nextInt();
		System.out.println((n - t - a < abs(t - a)) ? "Yes" : "No");
	}
}
