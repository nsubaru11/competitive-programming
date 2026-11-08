import java.util.*;

import lib.io.*;

public class B {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		String s = Integer.toString(sc.nextInt(), 3);
		ArrayList<Integer> al = new ArrayList<>();
		for (int i = s.length() - 1, j = 0; i >= 0; i--, j++) {
			if (s.charAt(i) == '1') {
				al.add(j);
			} else if (s.charAt(i) == '2') {
				al.add(j);
				al.add(j);
			}
		}
		out.println(al.size()).print(al.toArray());
		out.flush();
	}
}
