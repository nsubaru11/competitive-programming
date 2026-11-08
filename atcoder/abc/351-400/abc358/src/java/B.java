import lib.io.*;

public class B {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt(), a = sc.nextInt();
		for (int i = 0, now = 0; i < n; i++) {
			int t = sc.nextInt();
			if (now <= t) {
				now = t + a;
			} else {
				now += a;
			}
			out.println(now);
		}
		out.flush();
	}
}
