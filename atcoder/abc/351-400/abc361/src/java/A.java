import java.util.*;

import lib.io.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int k = sc.nextInt();
		String x = sc.next();
		StringJoiner sj = new StringJoiner(" ");

		for (int i = 0; i < n; i++) {
			sj.add(sc.next());
			if (i + 1 == k) sj.add(x);
		}
		System.out.println(sj);
	}
}
