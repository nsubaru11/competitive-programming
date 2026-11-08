import lib.io.*;

public class D {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt(), m = sc.nextInt();
		int[] a = new int[n], b = new int[m];
		long cnt = 0;
		b[a[0] = sc.nextInt() % m]++;
		for (int i = 1; i < n; i++) {
			a[i] = (sc.nextInt() + a[i - 1]) % m;
			cnt += ++b[a[i]] - 1;
		}
		for (int i = 0; i < n - 1; i++) {
			b[a[i]]--;
			a[i] = (a[i] + a[n - 1]) % m;
			cnt += b[a[i]];
		}
		System.out.println(cnt);
	}
}
