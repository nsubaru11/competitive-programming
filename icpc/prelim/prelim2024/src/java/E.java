import java.io.*;
import java.util.*;

public class E {

	private final static int[][] ans = new int[50][50];
	private final static int[] a = new int[50];

	private static boolean solve(int n) {
		/*
		 * 両端の値をa, bとしたとき、
		 * if a == b -> 全ての値が最も外側に並ぶ。
		 * else
		 *   a と同じ値が右端からみて最初に現れる場所を r
		 *   b と同じ値が左端から見て最初に現れる場所を l とするとき、
		 *   if l > r -> 解なし。
		 *   if l == 0 || r == n - 1 -> どちらか片方でも見つからなかったら解なし
		 *   l <= i <= r を満たすa[i]は全て外側
		 *   0 < i < l, r < j < n - 1 を満たすi, jは内側にスイスの形で配置。
		 */
		int l = 0, r = n - 1;
		if (a[0] != a[n - 1]) {
			for (int i = n - 2; i > 0; i--) {
				if (a[i] == a[0]) {
					r = i;
					break;
				}
			}
			for (int i = 1; i < n - 1; i++) {
				if (a[i] == a[n - 1]) {
					l = i;
					break;
				}
			}
			if (l > r || l == 0 || r == n - 1) return false;
		}
		for (int i = l; i <= r; i++) {
			ans[n - 1][i] = ans[0][n - i - 1] = ans[i][0] = ans[n - i - 1][n - 1] = a[i];
		}
		for (int i = 1; i < l; i++) {
			ans[i][n - r - 1] = ans[n - r - 1][n - i - 1] = ans[n - i - 1][r] = ans[r][i] = a[i];
		}
		for (int i = r + 1; i < n - 1; i++) {
			ans[l][n - i - 1] = ans[n - i - 1][n - l - 1] = ans[n - l - 1][i] = ans[i][l] = a[i];
		}
		return true;
	}

	private static void print(PrintWriter out, boolean flag, int n) {
		if (!flag) {
			out.println("No");
			return;
		}
		out.println("Yes");
		for (int i = 0; i < n; i++) {
			out.print(ans[i][0]);
			ans[i][0] = 0;
			for (int j = 1; j < n; j++) {
				out.print(" " + ans[i][j]);
				ans[i][j] = 0;
			}
			out.println();
		}
	}

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
			 PrintWriter out = new PrintWriter(System.out)) {
			int n;
			while ((n = sc.nextInt()) != 0) {
				for (int i = 0; i < n; i++) {
					a[i] = sc.nextInt();
				}
				boolean flag = solve(n);
				print(out, flag, n);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
