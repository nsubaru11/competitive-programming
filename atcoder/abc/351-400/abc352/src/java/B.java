import lib.io.*;

public class B {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		String s = sc.next(), t = sc.next();
		for (int i = 0, j = 0; i < s.length(); i++) {
			if (i > 0) out.print(" ");
			out.print(j = t.indexOf(s.charAt(i), j) + 1);
		}
		out.println().flush();
	}
}
