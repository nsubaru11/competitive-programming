import lib.io.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public final class C {

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

	// region initialization
	// ------------------------ 定数 ------------------------


	private static final int[] dij = new int[]{0, -1, 0, 1, -1, -1, 1, 1, -1, 0, 1, 0, -1, 1, 1, -1};


	// endregion

	// ------------------------ メインロジック ------------------------
	private static void solve() {
		String t = sc.next();
		int tLen = t.length();
		char[] s = new char[tLen + 1];
		for (int i = 0; i < tLen; i++) s[i] = t.charAt(i);
		s[tLen] = 'a';
		int ans = 0;
		for (int i = 1, cnt = 1, len = s.length, prevCnt = 0, prevChar = 0; i < len; i++) {
			if (s[i] == s[i - 1]) {
				cnt++;
			} else {
				if (prevChar + 1 == s[i - 1]) {
					ans += min(cnt, prevCnt);
				}
				prevCnt = cnt;
				prevChar = s[i - 1];
				cnt = 1;
			}
		}
		out.println(ans);
	}

	// region main() and debug() methods
	// ------------------------ main() 関数 ------------------------
	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	// ------------------------ デバッグ用 ------------------------
	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			System.err.println(deepToString(args));
		}
	}
}
