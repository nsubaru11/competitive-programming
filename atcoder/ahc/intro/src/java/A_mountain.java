import lib.io.*;

import static java.lang.Math.*;

public class A_mountain {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		// input
		int D = sc.nextInt();// 総日数
		int[] c = sc.nextInt(26);// タイプ'i'満足度の下がりやすさ
		int[][] s = sc.nextIntMat(D, 26);// 'd'日目のタイプ'i'の満足度上昇値

		// solve
		int[][] last = new int[D + 1][26];// 'd'日目以前で最後にタイプ'i'のコンテストを行った日
		long[] ans = new long[D + 1];// 'd'日目時点での暫定総満足度
		int[] choosed = new int[D + 1];// 'd'日目に選んだタイプ

		// 初期解
		for (int i = 1; i <= D; i++) {
			ans[i] = ans[i - 1];
			int t = (int) (random() * 26);// 'i'日目に選んだコンテストのタイプ
			for (int j = 0; j < 26; j++) {
				last[i][j] = last[i - 1][j];
				ans[i] -= c[j] * (i - last[i][j]);
			}
			ans[i] += s[i - 1][t] + c[t] * (i - last[i][t]);
			last[i][t] = i;
			choosed[i] = t;
		}

		// 局所探索法
		// 'd'日目のコンテストをpからqに変える
		int m = 10000;// クエリ数
		while (m-- > 0) {
			int d = (int) (random() * D + 1);
			int q = (int) (random() * 26);
			int p = choosed[d];
			long temp = ans[D];
			temp += c[q] * (d - last[d][q]) + s[d - 1][q];
			temp -= c[p] * (d - last[d - 1][p]) + s[d - 1][p];
			for (int d2 = d + 1; d2 <= D; d2++) {
				boolean flag = true;
				if (last[d2][q] < d) {
					temp += c[q] * (d - last[d2][q]);
					flag = false;
				}
				if (last[d2][p] == d) {
					temp += c[p] * (last[d - 1][p] - last[d2][p]);
					flag = false;
				}
				if (flag) break;
			}
			if (temp > ans[D]) {
				choosed[d] = q;
				for (int d2 = d; d2 <= D; d2++) {
					ans[d2] = ans[d2 - 1];
					int t = choosed[d2];// 'i'日目に選んだコンテストのタイプ
					for (int j = 0; j < 26; j++) {
						last[d2][j] = last[d2 - 1][j];
						ans[d2] -= c[j] * (d2 - last[d2][j]);
					}
					ans[d2] += s[d2 - 1][t] + c[t] * (d2 - last[d2][t]);
					last[d2][t] = d2;
				}
			}
		}
		for (int i = 1; i <= D; i++) {
			out.println(choosed[i] + 1);
		}
		out.flush();
	}
}
