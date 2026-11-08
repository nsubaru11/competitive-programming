import lib.io.*;

import static java.time.LocalDate.*;

public class A {

	private static FastScanner sc = new FastScanner();
	private static FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		if (of(n, 1, 1).isLeapYear()) {
			out.println(366);
		} else {
			out.println(365);
		}
		out.flush();
	}
}
