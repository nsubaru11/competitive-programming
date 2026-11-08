import lib.io.*;

import static java.lang.Math.*;

public class D2 {

	private static FastScanner sc = new FastScanner();
	private static FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		sc.next();
		int r = 0, p = 0, s = 0;
		for (char c : sc.nextChars()) {
			if (c == 'R') {
				int pp = p;
				p = max(r, s) + 1;
				r = max(pp, s);
				s = 0;
			} else if (c == 'P') {
				int ss = s;
				s = max(r, p) + 1;
				p = max(ss, r);
				r = 0;
			} else if (c == 'S') {
				int rr = r;
				r = max(p, s) + 1;
				s = max(rr, p);
				p = 0;
			}
		}
		out.println(max(r, max(p, s)));
		out.flush();
	}
}
