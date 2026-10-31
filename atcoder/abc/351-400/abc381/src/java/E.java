import static java.lang.Math.*;

import java.util.*;

import lib.io.*;

public class E {

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
		int q = sc.nextInt();
		Palindrome p = new Palindrome(sc.nextChars(n));
		p.SimpleManacher();
		while (q-- > 0) {
			out.println();
		}
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	/**
	 * 回文に関するクラスです。
	 */
	@SuppressWarnings("unused")
	private static class Palindrome {
		public static char[] c;
		private static HashMap<Integer, HashSet<String>> plndrm;
		private static int i, j, max, n;
		private static String s;
		private final int[] rad;

		Palindrome(char[] chr) {
			c = chr;
			n = chr.length;
			rad = new int[2 * n + 1];
		}

		/**
		 * Manacher's algorithmに基づいて文字列内の回文をO(N)で列挙します。 各i (0 <= i <= 2n) に対して、l + r = i
		 * を満たす回文区間[l, r)の長さr - lをrad[i] = r - l と定める。
		 *
		 */
		public void SimpleManacher() {
			for (i = j = 1; i <= 2 * n; ) {
				int l = (i - j) / 2 - 1;
				int r = (i + j) / 2;
				if (s == null) {
					while (j < i && i + j < 2 * n && c[l--] == '1' && c[r++] == '2') {
						j += 2;
					}
				} else {
					while (j < i && i + j < 2 * n && s.charAt(l--) == s.charAt(r++)) {
						j += 2;
					}
				}
				exceptionHandling();
			}
		}

		private void exceptionHandling() {
			rad[i] = j;
			if (j == 0) {
				i++;
				j = 1;
			} else {
				mirror();
			}
		}

		private void mirror() {
			int k = 1;
			while (k <= i && k + rad[i - k] < j) {
				rad[i + k] = rad[i - k];
				k++;
			}
			if (c[i / 2] == '/') {
				max = max(rad[i], max);
			}
			i += k;
			j -= k;
		}

		/**
		 * 最長回文の長さを返します。
		 *
		 * @return int
		 */
		public int getLongestLen() {
			if (max == 0) SimpleManacher();
			return max;
		}
	}
}
