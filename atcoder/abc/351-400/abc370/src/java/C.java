import java.util.*;

import lib.io.*;

public class C {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		StringBuilder s = new StringBuilder(sc.next());
		StringBuilder t = new StringBuilder(sc.next());
		ArrayList<String> x = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) > t.charAt(i)) {
				s.deleteCharAt(i);
				s.insert(i, t.charAt(i));
				x.add(s.toString());
			}
		}
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) < t.charAt(i)) {
				s.deleteCharAt(i);
				s.insert(i, t.charAt(i));
				x.add(s.toString());
			}
		}
		out.println(x.size()).println(x.toArray()).flush();
	}
}
