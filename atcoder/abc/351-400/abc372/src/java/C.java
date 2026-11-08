import lib.io.*;

import static java.lang.Math.*;

public class C {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int q = sc.nextInt();
		char[] s = sc.nextChars();
		int cnt = 0;
		for (int i = 0; i < n - 2; i++) {
			if (isABC(s, i)) {
				cnt++;
				i += 2;
			}
		}
		while (q-- > 0) {
			int x = sc.nextInt();
			char c = sc.nextChar();
			if (s[x - 1] == c) {
				out.println(cnt);
				continue;
			}
			for (int j = max(0, x - 3); j <= min(n - 3, x - 1); j++) {
				if (isABC(s, j)) {
					cnt--;
				}
				char temp = s[x - 1];
				s[x - 1] = c;
				if (isABC(s, j)) {
					cnt++;
				}
				s[x - 1] = temp;
			}
			s[x - 1] = c;
			out.println(cnt);
		}
		out.flush();
	}

	private static boolean isABC(char[] c, int i) {
		return c[i] == 'A' && c[i + 1] == 'B' && c[i + 2] == 'C';
	}
}
