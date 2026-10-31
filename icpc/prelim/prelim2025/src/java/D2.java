import java.io.PrintWriter;
import java.util.Scanner;
import java.util.function.Predicate;

import static java.lang.Math.max;
//import static java.lang.System.out;

public class D2 {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out);

	private static void solve(int n) {
		Predicate<Character> blackTest = c -> c == '#';
		int m = sc.nextInt();
		char[][] mat = new char[n][];
		for (int i = 0; i < n; i++) {
			mat[i] = sc.next().toCharArray();
		}

		// 同じ色が連続する最大の文字数を計測
		// よこ
		int max = 0;
		for (int i = 0; i < n; i++) {
			int cnt = 0;
			boolean isBlack = blackTest.test(mat[i][0]);
			for (int j = 0; j < m; j++) {
				if (isBlack == blackTest.test(mat[i][j])) {
					cnt++;
				} else {
					max = max(max, cnt);
					cnt = 1;
					isBlack = !isBlack;
				}
			}
			max = max(max, cnt);
		}

		// たて
		for (int j = 0; j < m; j++) {
			int cnt = 0;
			boolean isBlack = blackTest.test(mat[0][j]);
			for (int i = 0; i < n; i++) {
				if (isBlack == blackTest.test(mat[i][j])) {
					cnt++;
				} else {
					max = max(max, cnt);
					cnt = 1;
					isBlack = !isBlack;
				}
			}
			max = max(max, cnt);
		}

		// oxxxxoの形があるかどうか（一意に定まるかどうかのフラグ）
		//  よこ向きにあるとき ↓
		//  #.....#  .#####.
		//  たて向きにあるとき ↓
 		//  #  .
 		//  .  #
 		//  .  #
 		//  .  #
 		//  .  #
 		//  #  .
		boolean isOnly = false;

		// 両サイドに補色が存在する連続する同色のブロックがmax未満なら不可（実現可能かどうかのフラグ）
		// max = 3とするとき
		// .##... これはNG
		// .###.. これはOK
		// たて向きも同様
		boolean possible = true;

		// よこ
		outer: for (int i = 0; i < n; i++) {
			boolean flag = true;
			boolean isBlack = blackTest.test(mat[i][0]);
			int cnt = 0;
			for (int j = 0; j < m; j++) {
				if (test1(mat, i, j, m, max)) {
					isOnly = true;
				}
				if (isBlack == blackTest.test(mat[i][j])) {
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

		// たて
		outer: for (int j = 0; j < m; j++) {
			boolean flag = true;
			boolean isBlack = blackTest.test(mat[0][j]);
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				if (test2(mat, i, j, n, max)) {
					isOnly = true;
				}
				if (isBlack == blackTest.test(mat[i][j])) {
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

		// L字が存在する
		// 上記の条件では以下の条件が考慮できていない
		//  #..  ..#  ###  ###
 		//  #..  ..#  #..  ..#
 		//  ###  ###  #..  ..#
		boolean isL = false;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < m - 1; j++) {
				boolean isBlack = blackTest.test(mat[i][j]);
				if (isBlack == blackTest.test(mat[i][j + 1]) && isBlack == blackTest.test(mat[i + 1][j]) && isBlack != blackTest.test(mat[i + 1][j + 1])) {
					isL = true;
					break;
				}
				if (isBlack != blackTest.test(mat[i][j + 1]) && isBlack != blackTest.test(mat[i + 1][j]) && isBlack != blackTest.test(mat[i + 1][j + 1])) {
					isL = true;
					break;
				}
				if (isBlack == blackTest.test(mat[i][j + 1]) && isBlack != blackTest.test(mat[i + 1][j]) && isBlack == blackTest.test(mat[i + 1][j + 1])) {
					isL = true;
					break;
				}
				if (isBlack != blackTest.test(mat[i][j + 1]) && isBlack == blackTest.test(mat[i + 1][j]) && isBlack == blackTest.test(mat[i + 1][j + 1])) {
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
