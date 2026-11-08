import lib.io.*;

public class C2 {

	private static void solve(final FastScanner sc, final FastPrinter out, int n) {
		int[][] a = sc.nextIntMat(n, n);
		int[][] ans = new int[n][n];
		int h = (n - 1) / 2;
		for (int i = 0; i < n; i++) {
			int y = i % 2 == 0 ? h - i / 2 : n - (i + 1) / 2;
			for (int j = 0; j < n; j++) {
				int x = j % 2 == 0 ? h - j / 2 : n - (j + 1) / 2;
				ans[x][y] = a[j][i];
			}
		}
		out.println(ans);
	}

	public static void main(String[] args) {
		final FastScanner sc = new FastScanner();
		try (final FastPrinter out = new FastPrinter(System.out)) {
			int n = sc.nextInt();
			while (n != 0) {
				solve(sc, out, n);
				n = sc.nextInt();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
