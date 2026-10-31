import java.util.*;

public class C2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = Integer.parseInt(sc.next());
		int W = Integer.parseInt(sc.next());

		// 一次元dpで可能
		long[] dp = new long[W + 1];
		for (int i = 0; i < n; i++) {
			int v = Integer.parseInt(sc.next());
			int w = Integer.parseInt(sc.next());
			for (int j = w; j < W + 1; j++) {
				dp[j] = Math.max(dp[j], dp[j - w] + v);
			}
		}
		System.out.println(dp[W]);
		sc.close();
	}

}
