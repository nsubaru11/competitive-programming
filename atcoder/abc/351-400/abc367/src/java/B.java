import lib.io.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		double x = sc.nextDouble();
		if ((int) x == x) {
			System.out.println((int) x);
		} else {
			System.out.println(x);
		}
	}
}
