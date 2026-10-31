import java.util.*;

import lib.io.*;

public class A {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();
	// endregion

	private static void solve() {
		HashMap<Character, Integer> hm = sc.nextCharMultisetHM(6);
		if (hm.getOrDefault('1', 0) == 1 && hm.getOrDefault('2', 0) == 2 && hm.getOrDefault('3', 0) == 3) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
