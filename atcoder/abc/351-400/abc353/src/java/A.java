import lib.io.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt(), h = sc.nextInt();
		for (int i = 2; i <= n; i++) {
			if (h < sc.nextInt()) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(-1);
	}
}
