import lib.io.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		if (b < c) {
			System.out.println((a < b || c < a) ? "Yes" : "No");
		} else {
			System.out.println((c < a && a < b) ? "Yes" : "No");
		}
	}
}
