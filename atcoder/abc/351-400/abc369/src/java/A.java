import lib.io.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int a = sc.nextInt();
		int b = sc.nextInt();
		if (a == b) {
			System.out.println(1);
		} else if ((a + b) % 2 == 0) {
			System.out.println(3);
		} else {
			System.out.println(2);
		}
	}
}
