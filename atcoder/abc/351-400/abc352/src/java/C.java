import lib.io.*;

import static java.lang.Math.*;

public class C {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		long shoulders = 0;
		int head = 0;
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			shoulders += a;
			head = max(head, sc.nextInt() - a);
		}
		shoulders += head;
		System.out.println(shoulders);
	}
}
