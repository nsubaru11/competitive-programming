import lib.io.*;

public class C {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		// input
		int D = sc.nextInt();// 総日数
		int[] c = sc.nextInt(26);// タイプ'i'満足度の下がりやすさ
		int[][] s = sc.nextIntMat(D, 26);// 'd'日目のタイプ'i'の満足度上昇値

		// solve
		int[] t = sc.nextInt(D);
		int m = sc.nextInt();// クエリ数
		while (m-- > 0) {
			int d = sc.nextInt() - 1;
			int q = sc.nextInt();
			t[d] = q;
			int[] last = new int[26];
			long ans = 0;
			for (int i = 0; i < D; i++) {
				for (int j = 0; j < 26; j++) {
					ans -= c[j] * (i + 1 - last[j]);
				}
				ans += s[i][t[i] - 1] + c[t[i] - 1] * (i + 1 - last[t[i] - 1]);
				last[t[i] - 1] = i + 1;
			}
			out.println(ans);
		}

		out.flush();
	}
}
