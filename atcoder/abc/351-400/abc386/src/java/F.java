import lib.io.*;

import static java.lang.Math.*;

public class F {

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
		int k = sc.nextInt();
		char[] s = sc.nextChars();
		char[] t = sc.nextChars();
		int ans = computeEditDistance(s, t, k);
		out.println(ans != -1);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	/**
	 * bandedDPを使用し、O(|S|K)で高速に評価します。最大許容量を超える場合は-1を返します。
	 *
	 * @param s 1つ目の文字配列
	 * @param t 2つ目の文字配列
	 * @param k 最大許容距離。この値を超える編集距離の場合は-1を返します
	 * @return 編集距離がk以下の場合はその距離、k超過の場合は-1
	 */
	private static int computeEditDistance(char[] s, char[] t, int k) {
		if (abs(s.length - t.length) > k) return -1;
		if (s.length > t.length) {
			char[] temp = s;
			s = t;
			t = temp;
		}
		int sLen = s.length, tLen = t.length;
		int band = k << 1 | 1;
		int inf = k + 1;
		int[] dp1 = new int[band], dp2 = new int[band];
		for (int i = 0; i < band; i++) dp1[i] = abs(i - k);
		for (int i = 0; i < sLen; i++) {
			char si = s[i];
			for (int l = max(k - i, 0), j = i + l - k; l < band && j < tLen; l++, j++) {
				if (si == t[j]) {
					dp2[l] = dp1[l];
				} else {
					int sub = dp1[l];
					int del = l + 1 < band ? dp1[l + 1] : inf;
					int ins = l - 1 >= 0 ? dp2[l - 1] : inf;
					dp2[l] = min(sub, min(del, ins)) + 1;
				}
			}
			int[] temp = dp1;
			dp1 = dp2;
			dp2 = temp;
		}
		int ans = dp1[tLen - sLen + k];
		return ans > k ? -1 : ans;
	}
}
