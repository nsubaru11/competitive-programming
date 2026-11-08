import lib.io.*;

public class No3_ {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			int cnt = 0;
			while (a % 2 == 0 && cnt < min) {
				cnt++;
				a /= 2;
			}
			min = Math.min(min, cnt);
		}
		System.out.println(min);
	}
}
