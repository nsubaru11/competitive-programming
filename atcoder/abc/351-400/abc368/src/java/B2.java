import lib.io.*;

public class B2 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		long sum = 0, high = Long.MIN_VALUE;
		for (int v : sc.nextInt(n)) {
			sum += v;
			high = Math.max(high, v);
		}
		System.out.println(Math.min(sum / 2, sum - high));
	}
}
