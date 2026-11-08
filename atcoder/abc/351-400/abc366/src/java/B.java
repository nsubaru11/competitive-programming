import lib.io.*;

import static java.lang.Math.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		char[][] s = new char[n][];
		int m = 0;
		for (int i = 0; i < n; i++) {
			s[i] = sc.nextChars();
			m = max(s[i].length, m);
		}
		char[][] t = new char[m][n];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (s[n - j - 1].length - 1 < i) {
					t[i][j] = '*';
				} else {
					t[i][j] = s[n - j - 1][i];
				}
			}
			int end = t[i].length;
			while (t[i][--end] == '*') {
				t[i][end] = ' ';
			}
			sb.append(t[i]).append("\n");
		}
		System.out.println(sb);
	}
}
