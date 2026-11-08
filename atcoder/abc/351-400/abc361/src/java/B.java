import lib.io.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
		int d = sc.nextInt(), e = sc.nextInt(), f = sc.nextInt();

		int g = sc.nextInt(), h = sc.nextInt(), i = sc.nextInt();
		int j = sc.nextInt(), k = sc.nextInt(), l = sc.nextInt();

		if (d <= g || e <= h || f <= i) {
			System.out.println("No");
		} else if (j <= a || k <= b || l <= c) {
			System.out.println("No");
		} else {
			System.out.println("Yes");
		}
	}
}
