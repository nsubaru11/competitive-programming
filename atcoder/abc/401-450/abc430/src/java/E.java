import static java.util.Arrays.*;

import lib.io.*;
import lib.string.matching.*;

public final class E {

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


	private static final int[] dij = new int[]{-1, -1, -1, 0, 0, 1, 1, 1, -1, 0, 1, -1, 1, -1, 0, 1};


	// endregion

	// ------------------------ メインロジック ------------------------
	private static void solve() {
		int t = sc.nextInt();
		outer:
		while (t-- > 0) {
			char[] a = sc.nextChars();
			char[] b = sc.nextChars();
			int aLen = a.length;
			int bLen = b.length;
			char[] baa = new char[bLen + aLen + aLen];
			int pos = 0;
			System.arraycopy(b, 0, baa, pos, bLen);
			pos += bLen;
			System.arraycopy(a, 0, baa, pos, aLen);
			pos += aLen;
			System.arraycopy(a, 0, baa, pos, aLen);
			int[] s = ZAlgorithm.getZArray(baa);
			for (int i = 0; i < aLen; i++) {
				if (s[i + bLen] >= bLen) {
					out.println(i);
					continue outer;
				}
			}
			out.println(-1);
		}
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
