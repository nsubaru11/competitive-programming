import lib.io.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		char ab = sc.nextChar();
		char ac = sc.nextChar();
		char bc = sc.nextChar();
		System.out.println(ab != ac ? "A" : ab == bc ? "B" : "C");
	}
}
