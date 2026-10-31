import java.util.*;

public class A {

	// dp基本形、貰うdp。
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		//入力
		int n = Integer.parseInt(sc.next());
		int m = Integer.parseInt(sc.next());
		int[] coin = new int[m];
		for (int i = 0; i < m; i++) {
			coin[i] = Integer.parseInt(sc.next());
		}

		//dpテーブルの作成
		int[][] dp = new int[m + 1][n + 1];
		/* 前提
		 * dp[i][j] = min(dp[i - 1][j] , dp[i][j - coins[i - 1]] + 1);
		 * 0 <= i <= m; 0 <= j <= n;
		 * i - 1 番目までの coin を使って、ちょうど j 円支払うときに使う最小のコインの枚数を dp[i][j] に保存。
		 *
		 * 操作
		 * 1. dp[0][0 ~ n]をSUPで初期化 (SUP は int の最大値)。
		 * 2. i 番目のとき、i - 1 番目までのコインを用いて、ちょうど j 円支払う時の最小の枚数 dp[i][j] は、
		 *    i - 2 番目までのコインを用いて j 円支払うときの最小枚数 dp[i - 1][j] と、
		 *    i - 1 番目までのコインを用いて (j - coin[i - 1]) 円支払うときの枚数に,
		 *    さらに今回使う 1 枚を加えた枚数 dp[i][j - coin[i - 1]] + 1 との小さいほうとなる。
		 *    つまり　j < coin[i - 1] ならば dp[i][j] = dp[i - 1][j]となり、
		 *    そうでなければdp[i][j] = min(dp[i - 1][j] , dp[i][j - coins[i - 1]] + 1) となる。
		 * 3. 最終的に　m　番目までの coin を用いて、ちょうど n 円支払うときの最小のコインの枚数は dp[m][n] となる。
		 */
		Arrays.fill(dp[0], Integer.MAX_VALUE);
		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] = dp[i - 1][j];
				if (j >= coin[i - 1]) {
					dp[i][j] = Math.min(dp[i][j], dp[i][j - coin[i - 1]] + 1);
				}
			}
		}
		System.out.println(dp[m][n]);
		sc.close();
	}

}
