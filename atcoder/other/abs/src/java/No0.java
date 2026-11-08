import lib.io.*;

public class No0 {

	private static final FastPrinter out = new FastPrinter();
	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
		String s = sc.next();
		out.print(a + b + c, s);
		out.flush();
	}
}
