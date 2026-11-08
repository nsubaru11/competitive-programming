import lib.io.*;

import static java.lang.Math.*;

public class D {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		long even = 0;
		long odd = sc.nextInt();
		for (int i = 1; i < n; i++) {
			int a = sc.nextInt();
			long temp = even;
			even = max(odd + a * 2, even);
			odd = max(odd, temp + a);
		}
		System.out.println(max(odd, even));
	}
}
