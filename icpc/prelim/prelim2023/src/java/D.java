import java.io.*;
import java.util.*;

import static java.util.Arrays.*;

public final class D {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out);
	// endregion

	private static void solve() {
		int n;
		while ((n = sc.nextInt()) != 0) {
			boolean[] f = new boolean[n + 1];
			boolean[] memo = new boolean[n + 1];
			char[] s = sc.next().toCharArray();
			for (int i = 0; i < n + 1; i++) f[i] = s[i] == 'o';
			int[] a = new int[8];
			for (int m = 1; m <= 7; m++) {
				if (!dfs(n, 0, 0, m, a, memo, f)) continue;
				out.println(m);
				break;
			}
		}
	}

	private static boolean dfs(int n, int i, int sum, int m, int[] a, boolean[] memo, boolean[] f) {
		if (i == m) {
			if (sum != n) return false;
			fill(memo, false);
			memo[0] = true;
			for (int j = 0; j < m; j++) {
				int aj = a[j];
				for (int k = n; k >= aj; k--) {
					memo[k] |= memo[k - aj];
				}
			}
			for (int j = 0; j <= n; j++) {
				if (!memo[j] && f[j]) return false;
			}
			return true;
		}
		for (int j = i == 0 ? 1 : a[i - 1]; j <= n - sum; j++) {
			a[i] = j;
			if (dfs(n, i + 1, sum + j, m, a, memo, f)) return true;
		}
		return false;
	}

	// region < I/O & Debug >
	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			sc.close();
			out.close();
		}
	}
	// endregion
}
