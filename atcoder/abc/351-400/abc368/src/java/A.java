import java.util.*;

import lib.io.*;

public class A {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int r = sc.nextInt();
		ArrayList<Integer> al = new ArrayList<>(n);
		while (n-- > 0) {
			al.add(sc.nextInt());
		}
		Collections.rotate(al, r);
		out.print(al.toArray()).flush();
	}
}
