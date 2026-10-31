import java.util.*;

public class D {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = Integer.parseInt(sc.next());
		int W = Integer.parseInt(sc.next());
		int[] w = new int[n];
		int[] v = new int[n];
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < n; i++) {
			v[i] = Integer.parseInt(sc.next());
			w[i] = Integer.parseInt(sc.next());
			if (w[i] < min) {
				min = w[i];
			}
		}
		
		long[][] dp = new long[n + 1][W + 1];
		for (int i = 1; i < n + 1; i++) {
			for (int j = min; j < W + 1; j++) {
				if (j >= w[i - 1]) {
					dp[i][j] = Math.max(dp[i][j - w[i - 1]] + v[i - 1], dp[i - 1][j - w[i - 1]] + v[i - 1]);
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		System.out.println(dp[n][W]);
	}
}

