import java.util.*;

public class D {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int n = Integer.parseInt(sc.next());
			if (n == 0) {
				sc.close();
				return;
			}
			int[] balloon = new int[n];
			int sum = 0;
			for (int i = 0; i < n; i++) {
				balloon[i] = Integer.parseInt(sc.next());
				sum += balloon[i];
			}

			/* dpテーブルを2つ用意する。
			 * 操作
			 * 1. まずdp2[0][0] = trueで初期化。
			 * 2. i 番目までの風船を用いて、(x, y)の組み合わせが作れる所をtrueにする。
			 *    このとき (i - 1)番目までに作ることができた(x, y)の組みに、風船の数を加えて物をtrueにする。
			 *    つまり dp1 で(x, y)がtrueのとき、dp2は
			 *    dp2[x][y], dp2[x + balloon[i]][y], dp2[x][y + balloon[i]] の3通り全てをtrueにする。
			 * 3. (i == n)になったとき(x, y, z)の最小値の最大値を出力する。
			 */

			int ans = 0;
			int i = 0;
			boolean[][] dp1 = new boolean[sum + 1][sum + 1];
			boolean[][] dp2 = new boolean[sum + 1][sum + 1];
			dp2[0][0] = true;
			while (i < n) {
				// dp1を埋めていく
				for (int x = 0; x < sum + 1; x++) {
					for (int y = 0; y < sum + 1; y++) {
						if (dp2[x][y]) {
							dp1[x][y] = true;
							dp1[x + balloon[i]][y] = true;
							dp1[x][y + balloon[i]] = true;
							int z = sum - x - y - balloon[i];
							ans = Math.max(Math.min(Math.min(x + balloon[i], y), z), Math.max(Math.min(Math.min(x, y + balloon[i]), z), ans));
						}
					}
				}
				i++;
				if (i == n) break;

				// dp2を埋めていく
				for (int x = 0; x < sum + 1; x++) {
					for (int y = 0; y < sum + 1; y++) {
						if (dp1[x][y]) {
							dp2[x][y] = true;
							dp2[x + balloon[i]][y] = true;
							dp2[x][y + balloon[i]] = true;
							int z = sum - x - y - balloon[i];
							ans = Math.max(Math.min(Math.min(x + balloon[i], y), z), Math.max(Math.min(Math.min(x, y + balloon[i]), z), ans));
						}
					}
				}
				i++;
			}
			System.out.println(ans);
		}
	}

}
