import lib.io.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[] a = sc.nextInt(n * 2);
		int cnt = 0;
		for (int i = 0; i < n * 2 - 2; i++) {
			if (a[i] == a[i + 2]) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
