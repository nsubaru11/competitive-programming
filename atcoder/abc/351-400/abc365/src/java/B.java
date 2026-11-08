import lib.io.*;

public class B {

	private static FastScanner sc = new FastScanner();
	private static FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int first = 0, second = 0, firstIdx = 0, secondIdx = 0;
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			if (a > first) {
				second = first;
				secondIdx = firstIdx;
				first = a;
				firstIdx = i + 1;
			} else if (a > second) {
				second = a;
				secondIdx = i + 1;
			}
		}
		out.println(secondIdx);
		out.flush();
	}
}
