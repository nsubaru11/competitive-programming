import java.util.*;

import lib.io.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt(), m = sc.nextInt();
		TreeMap<Integer, Boolean> c = new TreeMap<>();
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			c.put(x, true);
		}
		for (int i = 0; i < m; i++) {
			int x = sc.nextInt();
			c.put(x, false);
		}

		boolean i = false;
		for (boolean j : c.values()) {
			if (i && j) {
				System.out.println("Yes");
				return;
			}
			i = j;
		}
		System.out.println("No");
	}
}
