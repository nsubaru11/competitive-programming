import lib.io.*;

public class B {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int d = sc.nextInt();
		int[] c = sc.nextInt(26);
		int[][] s = sc.nextIntMat(d, 26);
		int[] last = new int[26];
		long ans = 0;
		for (int i = 0; i < d; i++) {
			int t = sc.nextInt() - 1;
			for (int j = 0; j < 26; j++) {
				ans -= c[j] * (i + 1 - last[j]);
			}
			ans += s[i][t] + c[t] * (i + 1 - last[t]);
			last[t] = i + 1;
		}
		out.println(ans);
		out.flush();
	}
}
