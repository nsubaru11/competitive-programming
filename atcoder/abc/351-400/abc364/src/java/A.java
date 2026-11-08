import lib.io.*;

public class A {

	private static FastScanner sc = new FastScanner();
	private static FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		String s = sc.next();
		for (int i = 1; i < n - 1; i++) {
			String t = sc.next();
			if (s.equals("sweet") && s.equals(t)) {
				out.println("No");
				out.flush();
				return;
			}
			s = t;
		}
		out.println("Yes");
		out.flush();
	}
}
