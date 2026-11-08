import lib.io.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int h = sc.nextInt();
		int H = 1, n = 1;
		for (int i = 1; ; i++, n <<= 1, H += n) {
			if (h < H) {
				System.out.println(i);
				return;
			}
		}
	}
}
