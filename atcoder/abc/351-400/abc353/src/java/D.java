import lib.io.*;

public class D {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		long sum_All = 0, sum_B = 0;
		int mod = 998244353;
		for (int i = 0, b = 0; i < n; i++) {
			int a = sc.nextInt();
			sum_All = (sum_All + (long) a * i) % mod;
			if (i > 0) {
				sum_B = ((long) sum_B + b) % mod;
				long len = 1;
				for (int j = 0; j < Integer.toString(a).length(); j++) {
					len = len * 10L % mod;
				}
				len = len * sum_B % mod;
				sum_All = (sum_All + len) % mod;
			}
			b = a;
		}
		System.out.println(sum_All);
	}
}
