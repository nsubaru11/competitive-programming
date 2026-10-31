import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class C {

	private static void solve(final Scanner sc, final PrintWriter out, int n) {
		int[][] a = new int[n][n];
		for (int i = 0; i < n; i++)
			Arrays.setAll(a[i], j -> sc.nextInt());
		int[][] ans = new int[n][n];
		int h = (n - 1) / 2;
		for (int i = 0; i < n; i++) {
			int y = i % 2 == 0 ? h - i / 2 : n - (i + 1) / 2;
			for (int j = 0; j < n; j++) {
				int x = j % 2 == 0 ? h - j / 2 : n - (j + 1) / 2;
				ans[x][y] = a[j][i];
			}
		}
		for (int i = 0; i < n; i++) {
			out.print(ans[i][0]);
			for (int j = 1; j < n; j++) {
				out.print(" ");
				out.print(ans[i][j]);
			}
			out.println();
		}
	}

	public static void main(String[] args) {
		try (final Scanner sc = new Scanner(System.in);
			 final PrintWriter out = new PrintWriter(System.out)) {
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
