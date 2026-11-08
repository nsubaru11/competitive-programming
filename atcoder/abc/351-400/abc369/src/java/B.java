import lib.io.*;

import static java.lang.Math.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int ans = 0;
		int[] hand = new int[2];
		for (int i = 0, k = 0; i < n; i++) {
			int a = sc.nextInt();
			switch (sc.nextChar()) {
				case 'L':
					k = 0;
					break;
				case 'R':
					k = 1;
					break;
			}
			ans += (hand[k] != 0) ? abs(a - hand[k]) : 0;
			hand[k] = a;
		}
		System.out.println(ans);
	}
}
