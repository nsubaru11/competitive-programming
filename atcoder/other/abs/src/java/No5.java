import lib.io.*;

public class No5 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int a = sc.nextInt(), b = sc.nextInt();
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			if (i == 10000 && a == 1) {
				sum += i;
				break;
			} else if (i == 10000 && a > 1) {
				break;
			}
			int j = i / 1000 + (i / 100) % 10 + (i / 10) % 10 + i % 10;
			if (j >= a && j <= b) {
				sum += i;
			}
		}
		System.out.println(sum);
	}
}
