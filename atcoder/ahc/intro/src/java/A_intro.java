import lib.io.*;

import static java.lang.Math.*;

public class A_intro {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int d = sc.nextInt();
		int[] c = sc.nextInt(26);
		int[][] s = sc.nextIntMat(d, 26);
		int[] last = new int[26];
		long ans = 0;
		for (int i = 0; i < d; i++) {
			int idx = 0;
			int max = -1;
			for (int j = 0; j < 26; j++) {
				int temp = max(s[i][j], c[j] * (i + 1 - last[j]));
				if (max < temp) {
					idx = j;
					max = temp;
				}
				ans -= c[j] * (i + 1 - last[j]);
			}
			ans += s[i][idx] + c[idx] * (i + 1 - last[idx]);
			last[idx] = i + 1;
			out.println(idx + 1);
		}
		out.println(max(1000000 + ans, 0));
		out.flush();
	}
}
