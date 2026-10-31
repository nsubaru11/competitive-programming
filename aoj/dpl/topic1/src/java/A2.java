import java.util.*;

public class A2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = Integer.parseInt(sc.next());
		int m = Integer.parseInt(sc.next());

		//ひとつ前の値を利用し、同じものを使ってよいなら一次元dpで可能。
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 0; i < m; i++) {
			int coin = Integer.parseInt(sc.next());
			for (int j = coin; j < n + 1; j++) {
				dp[j] = Math.min(dp[j], dp[j - coin] + 1);
			}
		}
		System.out.println(dp[n]);
		sc.close();
	}

}
