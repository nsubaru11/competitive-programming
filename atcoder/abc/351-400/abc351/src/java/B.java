import lib.io.*;

public class B {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		char[][] c = sc.nextCharMat(n);
		for (int i = 0; i < n; i++) {
			int j = 0;
			for (char s : sc.nextChars()) {
				if (s != c[i][j++]) {
					out.print(++i, j).flush();
					return;
				}
			}
		}
	}
}
