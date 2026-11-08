import lib.io.*;

import static java.lang.Math.*;

public class A2 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		//solve
		int n = sc.nextInt();
		int[] h = sc.nextInt(n);
		int[] dp = new int[n];
		dp[1] = abs(h[1] - h[0]);
		for (int i = 2; i < n; i++) {
			dp[i] = min(abs(h[i] - h[i - 2]) + dp[i - 2], abs(h[i] - h[i - 1]) + dp[i - 1]);
		}
		System.out.println(dp[n - 1]);
	}
}
