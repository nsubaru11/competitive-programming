import java.util.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = Integer.parseInt(sc.next());
		int W = Integer.parseInt(sc.next());

		// 同じものを使ってはいけないため、二次元のdp。
		int min = Integer.MAX_VALUE;
		int[][] dp = new int[n + 1][W + 1];
		for (int i = 1; i < n + 1; i++) {
			int v = Integer.parseInt(sc.next());
			int w = Integer.parseInt(sc.next());
			min = Math.min(min, w);
			for (int j = min; j < W + 1; j++) {
				dp[i][j] = (j >= w) ? Math.max(dp[i - 1][j], dp[i - 1][j - w] + v) : dp[i - 1][j];
			}
		}
		System.out.println(dp[n][W]);
		sc.close();
	}

}
