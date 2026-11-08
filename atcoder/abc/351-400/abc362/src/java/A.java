import lib.io.*;

import static java.lang.Math.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int r = sc.nextInt();
		int g = sc.nextInt();
		int b = sc.nextInt();
		char s = sc.nextChar();
		System.out.println((s == 'R' ? min(g, b) : s == 'G' ? min(r, b) : min(r, g)));
	}
}
