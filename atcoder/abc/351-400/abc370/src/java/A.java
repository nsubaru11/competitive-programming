import lib.io.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int l = sc.nextInt();
		int r = sc.nextInt();
		if ((l ^ r) == 1) {
			System.out.println(l == 1 ? "Yes" : "No");
		} else {
			System.out.println("Invalid");
		}
	}
}
