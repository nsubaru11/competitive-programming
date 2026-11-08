import lib.io.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int a = sc.nextInt(), b = sc.nextInt();
		System.out.println(a == b ? -1 : 6 - a - b);
	}
}
