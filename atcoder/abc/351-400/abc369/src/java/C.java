import lib.io.*;

public class C {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		if (n == 1) {
			System.out.println(1);
			return;
		}
		int a = sc.nextInt();
		int b = sc.nextInt();
		int d = b - a;
		a = b;
		long ans = 0;
		int len = 2;
		while (n-- > 2) {
			b = sc.nextInt();
			if (d == b - a) {
				len++;
			} else {
				ans += (long) len * (len + 1) / 2;
				ans--;
				len = 2;
			}
			d = b - a;
			a = b;
		}
		ans += (long) len * (len + 1) / 2;
		System.out.println(ans);
	}
}
