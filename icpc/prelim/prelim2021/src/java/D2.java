import java.util.*;

public class D2 {

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

			boolean[][] dp = new boolean[sum + 1][sum + 1];
			HashMap<Integer, HashSet<Integer>> xy = new HashMap<>();
			dp[0][0] = true;
			int ans = 0;
			for(int i = 0; i < n; i++) {
				for (int x = 0; x < sum + 1; x++) {
					for (int y = 0; y < sum + 1; y++) {
						if (dp[x][y]) {

							if (xy.get(x) == null) {
								HashSet<Integer> Y = new HashSet<>();
								Y.add(y + balloon[i]);
								Y.add(y);
								xy.put(x, Y);
							} else {
								HashSet<Integer> Y = xy.get(x);
								Y.add(y);
								Y.add(y + balloon[i]);
							}

							if (xy.get(x + balloon[i]) == null) {
								HashSet<Integer> Y = new HashSet<>();
								Y.add(y);
								xy.put(x + balloon[i], Y);
							} else {
								HashSet<Integer> Y = xy.get(x + balloon[i]);
								Y.add(y);
							}

							ans = Math.max(ans, ans(x, y, balloon[i], sum));
						}
					}
				}
				for (int x: xy.keySet()) {
					for (int y: xy.get(x)) {
						dp[x][y] = true;
					}
				}
			}
			System.out.println(ans);
		}
	}

	private static int ans(int x, int y, int balloon, int sum) {
		int ans = 0;
		int z = sum - x - y;
		ans = Math.min(Math.min(x, y), z);
		z = sum - x - y - balloon;
		ans = Math.max(Math.min(Math.min(x + balloon, y), z), ans);
		ans = Math.max(Math.min(Math.min(x, y + balloon), z), ans);
		return ans;
	}

}
