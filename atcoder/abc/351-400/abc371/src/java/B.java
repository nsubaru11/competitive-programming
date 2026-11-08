import java.util.*;

import lib.io.*;

public class B {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int m = sc.nextInt();
		BitSet bs = new BitSet(n);
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt() - 1;
			char b = sc.nextChar();
			switch (b) {
				case 'M':
					if (!bs.get(a)) {
						out.println("Yes");
						bs.set(a);
					} else {
						out.println("No");
					}
					break;
				case 'F':
					out.println("No");
					break;
			}
		}
		out.flush();
	}
}
