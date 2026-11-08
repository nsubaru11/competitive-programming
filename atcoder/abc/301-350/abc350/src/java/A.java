import lib.io.*;


public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int k = Integer.parseInt(sc.next().substring(3, 6));
		System.out.println(k % 316 == 0 || k > 349 ? "No" : "Yes");
	}
}
