import lib.io.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt(), k = sc.nextInt();
		int cnt = 0;
		for (int i = 0, group = 0; i < n; i++) {
			int a = sc.nextInt();
			if (group + a <= k) {
				group += a;
			} else {
				group = a;
				cnt++;
			}
		}
		System.out.println(cnt + 1);
	}
}
