import static java.lang.Math.*;

import java.util.*;

import lib.io.*;

public class C {

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
		int n = sc.nextInt();
		int k = sc.nextInt();
		int x = sc.nextInt() - 1;
		String[] s = sc.nextStrings(n);
		ArrayList<String> ans = new ArrayList<>((int) pow(n, k));
		comb(0, n, k, new StringBuilder(), s, ans);
		Collections.sort(ans);
		//		out.print(ans);
		//		out.println();
		out.println(ans.get(x));
	}

	private static void comb(int i, int n, int k, StringBuilder sb, String[] s, ArrayList<String> ans) {
		if (i == k) {
			ans.add(sb.toString());
			return;
		}
		for (int j = 0; j < n; j++) {
			sb.append(s[j]);
			comb(i + 1, n, k, sb, s, ans);
			sb.setLength(sb.length() - s[j].length());
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
