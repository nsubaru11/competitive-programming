import java.io.PrintWriter;
import java.util.*;
import java.util.function.Predicate;

import static java.util.Arrays.*;
import static java.lang.Math.*;
//import static java.lang.System.out;

public class D {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out);

	private static void solve(int n) {
		Predicate<Character> isBlackTest = c -> c == '#';
		int m = sc.nextInt();
		char[][] mat = new char[n][];
		for (int i = 0; i < n; i++) {
			mat[i] = sc.next().toCharArray();
		}

		// max よこ
		int max = 0;
		for (int i = 0; i < n; i++) {
			int cnt = 0;
			boolean isBlack = isBlackTest.test(mat[i][0]);
			for (int j = 0; j < m; j++) {
				if (isBlack == isBlackTest.test(mat[i][j])) {
					cnt++;
				} else {
					max = max(max, cnt);
					cnt = 1;
					isBlack = !isBlack;
				}
			}
			max = max(max, cnt);
		}

		// max たて
		for (int j = 0; j < m; j++) {
			int cnt = 0;
			boolean isBlack = isBlackTest.test(mat[0][j]);
			for (int i = 0; i < n; i++) {
				if (isBlack == isBlackTest.test(mat[i][j])) {
					cnt++;
				} else {
					max = max(max, cnt);
					cnt = 1;
					isBlack = !isBlack;
				}
			}
			max = max(max, cnt);
		}

		// 横
		boolean possible = true;
		outer: for (int i = 0; i < n; i++) {
			boolean flag = true;
			boolean isBlack = isBlackTest.test(mat[i][0]);
			int cnt = 0;
			for (int j = 0; j < m; j++) {
				if (isBlack == isBlackTest.test(mat[i][j])) {
					cnt++;
				} else {
					isBlack = !isBlack;
					if (flag) {
						flag = false;
					} else {
						if (cnt < max) {
							possible = false;
							break outer;
						}
					}
					cnt = 1;
				}
			}
		}
		if (!possible) {
			out.println(-1);
			return;
		}

		// 縦
		outer: for (int j = 0; j < m; j++) {
			boolean flag = true;
			boolean isBlack = isBlackTest.test(mat[0][j]);
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				if (isBlack == isBlackTest.test(mat[i][j])) {
					cnt++;
				} else {
					isBlack = !isBlack;
					if (flag) {
						flag = false;
					} else {
						if (cnt < max) {
							possible = false;
							break outer;
						}
					}
					cnt = 1;
				}
			}
		}
		if (!possible) {
			out.println(-1);
			return;
		}

		// 一意 よこ
		boolean isOnly = false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (test1(mat, i, j, m, max)) {
					isOnly = true;
					break;
				}
			}
		}

		// 一意 たて
		for (int j = 0; j < m; j++) {
			for (int i = 0; i < n; i++) {
				if (test2(mat, i, j, n, max)) {
					isOnly = true;
					break;
				}
			}
		}

		// L字が存在する
		boolean isL = false;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < m - 1; j++) {
				boolean isBlack = isBlackTest.test(mat[i][j]);
				if (isBlack == isBlackTest.test(mat[i][j + 1]) && isBlack == isBlackTest.test(mat[i + 1][j]) && isBlack != isBlackTest.test(mat[i + 1][j + 1])) {
					isL = true;
					break;
				}
				if (isBlack != isBlackTest.test(mat[i][j + 1]) && isBlack != isBlackTest.test(mat[i + 1][j]) && isBlack != isBlackTest.test(mat[i + 1][j + 1])) {
					isL = true;
					break;
				}
				if (isBlack == isBlackTest.test(mat[i][j + 1]) && isBlack != isBlackTest.test(mat[i + 1][j]) && isBlack == isBlackTest.test(mat[i + 1][j + 1])) {
					isL = true;
					break;
				}
				if (isBlack != isBlackTest.test(mat[i][j + 1]) && isBlack == isBlackTest.test(mat[i + 1][j]) && isBlack == isBlackTest.test(mat[i + 1][j + 1])) {
					isL = true;
					break;
				}
			}
		}

		if (isL) {
			out.println(-1);
		} else {
			out.println(isOnly ? max : 0);
		}
	}

	private static boolean test1(char[][] mat, int i, int j, int m, int max) {
		boolean flag = true;
		boolean isBlack = mat[i][j] == '#';
		for (int k = 1; k <= max; k++) {
			if (j + k >= m) {
				flag = false;
				break;
			}
			if (isBlack == (mat[i][j + k] == '#')) {
				flag = false;
				break;
			}
		}
		return flag && j + max + 1 < m && mat[i][j] == mat[i][j + max + 1];
	}

	private static boolean test2(char[][] mat, int i, int j, int n, int max) {
		boolean flag = true;
		boolean isBlack = mat[i][j] == '#';
		for (int k = 1; k <= max; k++) {
			if (i + k >= n) {
				flag = false;
				break;
			}
			if (isBlack == (mat[i + k][j] == '#')) {
				flag = false;
				break;
			}
		}
		return flag && i + max + 1 < n && mat[i][j] == mat[i + max + 1][j];
	}

	public static void main(String[] args) {
		try {
			// 最初の入力
			int n;
			while ((n = sc.nextInt()) != 0) {
				solve(n); // メインロジック
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
			out.close();
		}
	}

}
