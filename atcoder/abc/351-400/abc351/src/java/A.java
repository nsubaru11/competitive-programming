import lib.io.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int a = 1;
		for (int i = 0; i < 9; i++) a += sc.nextInt();
		for (int i = 0; i < 8; i++) a -= sc.nextInt();
		System.out.println(a);
	}
}
