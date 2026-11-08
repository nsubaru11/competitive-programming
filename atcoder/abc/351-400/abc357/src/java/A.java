import lib.io.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt(), m = sc.nextInt();
		int i = 0;
		for (; i < n; i++) {
			m -= sc.nextInt();
			if (m < 0) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(i);
	}
}
