import java.math.*;
import java.util.*;

import lib.io.*;

public class E {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();
	// endregion

	private static int[][] scp;
	private static HashMap<Data, BigDecimal> memo;
	private static int n;

	private static void solve() {
		n = sc.nextInt();
		int x = sc.nextInt();
		scp = sc.nextIntMat(n, 3);
		memo = new HashMap<>();
		BigDecimal max = dfs(new Data(x, 0, 0, 1), 0);
		out.println(max);
		String s;
	}

	private static BigDecimal dfs(Data data, int cnt) {
		int remainCost = data.remainCost;
		int isAC = data.isAC;
		long score = data.score;
		long prob = data.prob;
		if (memo.containsKey(data)) {
			return memo.get(data);
		}
		BigDecimal max = BigDecimal.ZERO;
		for (int i = 0; i < n; i++) {
			if (((isAC >> i) & 1) == 1) continue;
			if (remainCost < scp[i][1]) continue;
			int s = scp[i][0];
			int c = scp[i][1];
			int p = scp[i][2];
			BigDecimal k = dfs(new Data(remainCost - c, isAC | (1 << i), score + s, prob * p), cnt + 1);
			BigDecimal l = dfs(new Data(remainCost - c, isAC, score, prob * (100 - p)), cnt + 1);
			max = max.max(k.add(l));
		}
		max = max.max(BigDecimal.valueOf(score * prob).divide(BigDecimal.valueOf(Math.pow(100, cnt)), 6, BigDecimal.ROUND_HALF_UP));
		memo.put(data, max);
		return max;
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private record Data(int remainCost, int isAC, long score, long prob) {}
}
