import lib.io.*;

public final class D {

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

	private static void solve() {
		int n = sc.nextInt();
		int q = sc.nextInt();
		int n2 = n << 1;
		int[][] grid = new int[n2][n2];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				char c = sc.nextChar();
				if (c == 'B') {
					grid[i][j] = 1;
					grid[i][j + n] = 1;
					grid[i + n][j] = 1;
					grid[i + n][j + n] = 1;
				}
			}
		}
		for (int j = 1; j < n2; j++) {
			grid[0][j] += grid[0][j - 1];
		}
		for (int i = 1; i < n2; i++) {
			grid[i][0] += grid[i - 1][0];
			for (int j = 1; j < n2; j++) {
				grid[i][j] += grid[i - 1][j] + grid[i][j - 1] - grid[i - 1][j - 1];
			}
		}
		while (q-- > 0) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			int d = sc.nextInt();
			int a2 = a % n, b2 = b % n, c2 = c % n, d2 = d % n;
			if (a2 > c2) c2 += n;
			if (b2 > d2) d2 += n;
			long A = sum(grid, a2, b2, c2, d2);
			long B = sum(grid, a2, 0, c2, n - 1);
			long C = sum(grid, 0, b2, n - 1, d2);
			long D = grid[n - 1][n - 1];
			int an = 1, bn = (d - d2 - (b - b2)) / n, cn = (c - c2 - (a - a2)) / n;
			long ans = A * an + B * bn + C * cn + D * bn * cn;
			out.println(ans);
		}
	}

	private static long sum(int[][] grid, int a, int b, int c, int d) {
		long res = grid[c][d];
		if (a > 0) res -= grid[a - 1][d];
		if (b > 0) res -= grid[c][b - 1];
		if (a > 0 && b > 0) res += grid[a - 1][b - 1];
		return res;
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
