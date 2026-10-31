import static java.lang.Math.*;
import static java.util.Arrays.*;

import lib.io.*;

public class C3 {

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
		int m = sc.nextInt();
		int[] c = new int[n];
		setAll(c, i -> min(sc.nextInt(), i > 0 ? c[i - 1] : Integer.MAX_VALUE));
		while (m-- > 0) {
			int b = sc.nextInt();
			AbstractBinarySearch bs = new AbstractBinarySearch() {
				@Override
				protected int comparator(long idx) {
					int i = (int) idx;
					return b < c[i] ? -1 : b == c[i] ? 0 : 1;
				}
			};
			int ans = bs.lowerBoundSearch(0, n - 1);
			ans = ans < 0 ? ~ans : ans;
			out.println(ans >= n ? -1 : ans + 1);
		}
		out.flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	/**
	 * 整数と長整数に対して通常の二分探索、上限探索(Upper Bound)、下限探索(Lower Bound)を行うための抽象クラスです。
	 * 探索に失敗した際の戻り値は-(挿入位置(境界値) - 1)となっています。
	 */
	@SuppressWarnings("unused")
	private static abstract class AbstractBinarySearch {

		/**
		 * 整数範囲での通常の二分探索を行います。comparatorが0を返した時点で探索を終了します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含む)
		 * @return int
		 */
		public final int normalSearch(int l, int r) {
			return binarySearch(l, r, SearchType.NORMAL);
		}

		/**
		 * 長整数範囲での通常の二分探索を行います。comparatorが0を返した時点で探索を終了します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含む)
		 * @return long
		 */
		public final long normalSearch(long l, long r) {
			return binarySearch(l, r, SearchType.NORMAL);
		}

		/**
		 * 整数範囲での上限探索(Upper Bound)を行います。 目的の条件にちょうど当てはまる際にcomparatorが0を返すことが好ましい。
		 * comparatorが0を返した際、その値を記憶します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含む)
		 * @return 条件にちょうど当てはまる整数。もしくは上限値+1(挿入位置)。
		 */
		public final int upperBoundSearch(int l, int r) {
			return binarySearch(l, r, SearchType.UPPER_BOUND);
		}

		/**
		 * 長整数範囲での上限探索(Upper Bound)を行います。 目的の条件にちょうど当てはまる際にcomparatorが0を返すことが好ましい。
		 * comparatorが0を返した際、その値を記憶します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含む)
		 * @return 条件にちょうど当てはまる長整数。もしくは上限値+1(挿入位置)。
		 */
		public final long upperBoundSearch(long l, long r) {
			return binarySearch(l, r, SearchType.UPPER_BOUND);
		}

		/**
		 * 整数範囲での下限探索(Lower Bound)を行います。 目的の条件にちょうど当てはまる際にcomparatorが0を返すことが好ましい。
		 * comparatorが0を返した際、その値を記憶します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含む)
		 * @return 条件にちょうど当てはまる整数。もしくは下限値-1(挿入位置)。
		 */
		public final int lowerBoundSearch(int l, int r) {
			return binarySearch(l, r, SearchType.LOWER_BOUND);
		}

		/**
		 * 長整数範囲での下限探索(Lower Bound)を行います。 目的の条件にちょうど当てはまる際にcomparatorが0を返すことが好ましい。
		 * comparatorが0を返した際、その値を記憶します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含む)
		 * @return 条件にちょうど当てはまる長整数。もしくは下限値-1(挿入位置)。
		 */
		public final long lowerBoundSearch(long l, long r) {
			return binarySearch(l, r, SearchType.LOWER_BOUND);
		}

		/**
		 * 整数範囲での汎用二分探索メソッド
		 */
		private final int binarySearch(int l, int r, SearchType type) {
			Integer k = null;
			while (l <= r) {
				int m = (l + r) >> 1;
				switch (comparator(m)) {
					case 1:
						r = m - 1;
						break;
					case 0:
						switch (type) {
							case UPPER_BOUND:
								l = m + 1;
								break;
							case LOWER_BOUND:
								r = m - 1;
								break;
							case NORMAL:
								return m;
						}
						k = m;
						break;
					case -1:
						l = m + 1;
						break;
				}
			}
			return k != null ? k : ~l;
		}

		/**
		 * 長整数範囲での汎用二分探索メソッド
		 */
		private final long binarySearch(long l, long r, SearchType type) {
			Long k = null;
			while (l <= r) {
				long m = (l + r) >> 1L;
				switch (comparator(m)) {
					case 1:
						r = m - 1;
						break;
					case 0:
						switch (type) {
							case UPPER_BOUND:
								l = m + 1;
								break;
							case LOWER_BOUND:
								r = m - 1;
								break;
							case NORMAL:
								return m;
						}
						k = m;
						break;
					case -1:
						l = m + 1;
						break;
				}
			}
			return k != null ? k : ~l;
		}

		/**
		 * 問題に応じた実装を必要とします。条件を超過する際は1, ちょうど合致する際は0、そうでない場合は-1を返すことが望ましい。
		 */
		abstract protected int comparator(long n);

		/**
		 * 内部的に利用される探索種別を示す列挙型
		 */
		private enum SearchType {
			NORMAL, UPPER_BOUND, LOWER_BOUND
		}
	}
}
