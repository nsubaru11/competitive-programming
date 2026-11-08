import lib.io.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		char[] s = sc.nextChars();
		char[] t = sc.nextChars();
		int n = s.length;
		int m = t.length;
		for (int w = 1; w < n; w++) {
			for (int c = 1; c <= w; c++) {
				int strt = 0;
				int cnt = 0;
				while (strt + c - 1 < n && s[strt + c - 1] == t[cnt]) {
					strt += w;
					cnt++;
					if (strt + c - 1 < n && cnt >= m) {
						cnt++;
						break;
					}
				}
				if (cnt == m) {
					System.out.println("Yes");
					return;
				}
			}
		}
		System.out.println("No");
	}
}
