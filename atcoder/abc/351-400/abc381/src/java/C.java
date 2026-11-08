import java.util.*;

import lib.io.*;

import static java.lang.Math.*;

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
		Palindrome p = new Palindrome(sc.nextChars(n));
		System.out.println(p.getLongestLen());
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
		private final int n;
		private final String s;
		private final char[] c;
		private final int[] rad;
		private int i, j, max;
		private HashMap<Integer, HashSet<String>> plndrm;

		Palindrome(String str) {
			s = str;
			c = null;
			n = str.length();
			rad = new int[2 * n + 1];
		}

		Palindrome(char[] chr) {
			c = chr;
			s = null;
			n = chr.length;
			rad = new int[2 * n + 1];
		}

		/**
		 * 文字列が回文かどうかの判定をします。
		 *
		 * @param s String
		 * @return boolean
		 */
		public boolean isPalindrome(String s) {
			int n = s.length();
			for (int i = 0; i <= n / 2; i++) {
				if (s.charAt(i) != s.charAt(n - i - 1)) {
					return false;
				}
			}
			return true;
		}

		/**
		 * Manacher's algorithmに基づいて文字列内の回文をO(N)で列挙します。 各i (0 <= i <= 2n) に対して、l + r = i
		 * を満たす回文区間[l, r)の長さr - lをrad[i] = r - l と定める。
		 *
		 */
		private void SimpleManacher() {
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

		/**
		 * Manacher's algorithmに基づいて文字列内の回文をO(N)で列挙します。 各i (0 <= i <= 2n) に対して、l + r = i
		 * を満たす回文区間[l, r)の長さr - lをrad[i] = r - l と定める。
		 * 回文の長さをkeyとしてkeyと同じ長さの全ての回文を重複なしでValueに保存します。
		 */
		private void ExManacher() {
			plndrm = new HashMap<>(n);
			for (i = j = 1; i <= 2 * n; ) {
				int l = (i - j) / 2 - 1;
				int r = (i + j) / 2;
				while (j < i && i + j < 2 * n && s.charAt(l--) == s.charAt(r++)) {
					j += 2;
					HashSet<String> set = plndrm.getOrDefault(j, new HashSet<>());
					set.add(s.substring(l + 1, r));
					plndrm.put(j, set);
				}
				exceptionHandlingEx();
			}
		}

		/**
		 * 特定の長さの回文があるかどうか調べます。
		 *
		 * @param k length
		 * @return boolean
		 */
		public boolean isExistSpecifiedLength(int k) {
			for (i = j = 1; i <= 2 * n; ) {
				int l = (i - j) / 2 - 1;
				int r = (i + j) / 2;
				if (s == null) {
					while (j < i && i + j < 2 * n && c[l--] == c[r++]) {
						j += 2;
						if (k == j) return true;
					}
				} else {
					while (j < i && i + j < 2 * n && s.charAt(l--) == s.charAt(r++)) {
						j += 2;
						if (k == j) return true;
					}
				}
				exceptionHandling();
			}
			return false;
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
			max = max(c[i / 2] == '/' ? rad[i] : 0, max);
			i += k;
			j -= k;
		}

		private void exceptionHandlingEx() {
			rad[i] = j;
			if (j == 0) {
				i++;
				j = 1;
			} else {
				mirrorEx();
			}
		}

		private void mirrorEx() {
			int k = 1;
			HashSet<String> set = plndrm.getOrDefault(j, new HashSet<>());
			set.add(s.substring((i - j) / 2, (i + j) / 2));
			plndrm.put(j, set);
			while (k <= i && k + rad[i - k] < j) {
				int r = rad[i - k];
				rad[i + k] = r;
				set = plndrm.getOrDefault(r, new HashSet<>());
				set.add(s.substring((i - k - r) / 2, (i - k + r) / 2));
				plndrm.put(r, set);
				k++;
			}
			max = max(rad[i], max);
			i += k;
			j -= k;
		}

		/**
		 * 特定の範囲が回文となっているか判定します。
		 *
		 * @param l include
		 * @param r exclude
		 * @return boolean
		 */
		public boolean isPalindromeRange(int l, int r) {
			if (rad == null) SimpleManacher();
			return rad[l + r] % 2 == (r - l) % 2 && rad[l + r] >= r - l;
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

		/**
		 * 特定の長さの回文の個数を返します。
		 *
		 * @param n 回文の長さ
		 * @return int
		 */
		public int getCountLen(int n) {
			if (plndrm == null) ExManacher();
			return plndrm.get(n) == null ? 0 : plndrm.get(n).size();
		}
	}
}
