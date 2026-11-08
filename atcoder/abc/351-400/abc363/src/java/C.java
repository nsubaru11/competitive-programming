import lib.io.*;
import lib.util.*;

public class C {

	private static final FastPrinter out = new FastPrinter();
	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int k = sc.nextInt();
		char[] c = sc.nextSortedChars();
		int cnt = 0, max = 0;
		do {
			max++;
			for (int i = 0; i <= n - k; i++) {
				boolean f = true;
				for (int j = 0; j < k / 2; j++) {
					if (c[i + j] != c[i + k - 1 - j]) {
						f = false;
						break;
					}
				}
				if (f) {
					cnt++;
					break;
				}
			}
		} while (Permutation.next(c));
		out.println(max - cnt);
		out.flush();
	}
}
