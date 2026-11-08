import lib.io.*;

import static java.lang.Math.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		char[] s = sc.nextChars();
		int[] x = new int['Z' + 1];
		for (int i = 0; i < 26; i++) {
			x[s[i]] = i;
		}
		int cnt = 0;
		for (char a = 'B'; a <= 'Z'; a++) {
			cnt += abs(x[a] - x[a - 1]);
		}
		System.out.println(cnt);
	}
}
