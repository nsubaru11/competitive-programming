import lib.io.*;

import static java.lang.Math.*;

public class C {

	private static final FastScanner sc = new FastScanner();
	private static int[] a;
	private static int n;
	private static int m;
	private static int max;

	public static void main(String[] args) {
		//recursive memo
		n = sc.nextInt();
		m = sc.nextInt();
		a = new int[n];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.replace(0, sb.length(), sc.next());
			for (int j = 0; j < m; j++) {
				if (sb.charAt(j) == 'o') {
					a[i] |= 1 << (j);
				}
			}
		}
		max = 1 << m;
		int ans = visitCount(0, 0, new int[n + 1][max]);
		System.out.println(ans);
	}

	private static int visitCount(int idx, int taste, int[][] memo) {
		int ans = n;
		if (memo[idx][taste] != 0) {
			return memo[idx][taste];
		}
		if (taste == (max - 1)) {
			return 0;
		} else if (idx == n) {
			return n;
		}
		ans = min(ans, visitCount(idx + 1, taste | a[idx], memo) + 1);
		ans = min(ans, visitCount(idx + 1, taste, memo));
		memo[idx][taste] = ans;
		return ans;
	}
}
