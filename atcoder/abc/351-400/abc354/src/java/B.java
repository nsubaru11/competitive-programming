import lib.io.*;

import static java.util.Arrays.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int sum = 0;
		String[] s = new String[n];
		for (int i = 0; i < n; i++) {
			s[i] = sc.next();
			sum += sc.nextInt();
		}
		sort(s);
		System.out.println(s[sum % n]);
	}
}
