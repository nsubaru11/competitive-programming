import lib.io.*;

public class C {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		long cnt = 0;
		int t = 0;
		while (n-- > 0) {
			int a = sc.nextInt();
			if (t == 1) {
				a -= t;
				t = 3;
				cnt++;
				if (a <= 0) {
					continue;
				}
			}
			if (t == 3) {
				a -= t;
				t = 0;
				cnt++;
				if (a <= 0) {
					continue;
				}
			}
			int d = a % 5;
			switch (d) {
				case 0:
					cnt += a / 5 * 3;
					t = 0;
					break;
				case 1:
					cnt += a / 5 * 3 + 1;
					t = 1;
					break;
				case 2:
					cnt += a / 5 * 3 + 2;
					t = 3;
					break;
				case 3, 4:
					cnt += a / 5 * 3 + 3;
					t = 0;
					break;
			}
		}
		System.out.println(cnt);
	}
}
