import lib.io.*;

public class A {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		FastPrinter out = new FastPrinter();

		int n = 100 - sc.nextInt() % 100;
		out.println(n);
		out.flush();
	}
}
