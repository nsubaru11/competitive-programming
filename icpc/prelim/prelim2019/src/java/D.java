import java.io.*;
import java.util.*;

import static java.util.Arrays.*;

public final class D {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out);

	private static void solve(int n) {
		int m = sc.nextInt();
		int[] a = new int[n];
		setAll(a, i -> sc.nextInt());
		int[] c = new int[n];
		setAll(c, i -> (sc.nextInt() - a[i] + m) % m);
		int[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			dp[0][i] = i * m + c[0];
		}
		for (int i = 1; i < n; i++) {
			for (int j = 0, k = 0; j < n; j++) {
				int d = c[i] + m * j;
				int d1 = c[i - 1] + m * k, d2 = d1 + m;
				while (k + 2 < n && d2 < d) {
					k++;
					d1 = c[i - 1] + m * k;
					d2 = d1 + m;
				}
				int dp1 = (d > d1 ? d - d1 : 0) + dp[i - 1][k];
				int dp2 = (d > d2 ? d - d2 : 0) + dp[i - 1][k + 1];
				dp[i][j] = Math.min(dp1, dp2);
			}
		}
		int ans = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			ans = Math.min(ans, dp[n - 1][i]);
		}
		out.println(ans);
	}

	public static void main(final String[] args) {
		try {
			int n;
			while ((n = sc.nextInt()) != 0) solve(n);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
			out.close();
		}
	}

}
