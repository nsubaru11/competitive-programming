import lib.io.*;

public class B {

	private static FastScanner sc = new FastScanner();
	private static FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int h = sc.nextInt();
		int w = sc.nextInt();
		int y = sc.nextInt() - 1;
		int x = sc.nextInt() - 1;
		char[][] c = sc.nextCharMat(h);
		for (char s : sc.nextChars()) {
			if (s == 'L' && x - 1 >= 0 && c[y][x - 1] == '.') {
				x--;
			} else if (s == 'U' && y - 1 >= 0 && c[y - 1][x] == '.') {
				y--;
			} else if (s == 'R' && x + 1 < w && c[y][x + 1] == '.') {
				x++;
			} else if (s == 'D' && y + 1 < h && c[y + 1][x] == '.') {
				y++;
			}
		}
		out.print(++y, ++x);
		out.flush();
	}
}
