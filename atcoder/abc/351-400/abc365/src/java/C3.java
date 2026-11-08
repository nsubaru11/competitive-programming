import java.util.concurrent.atomic.*;

import lib.io.*;

import static java.lang.Math.*;

public class C3 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		long m = sc.nextLong();
		int[] a = sc.nextInt(n);
		long total = 0, lowest = Long.MAX_VALUE, highest = Long.MIN_VALUE;
		for (int v : a) {
			total += v;
			lowest = min(lowest, v);
			highest = max(highest, v);
		}
		if (total <= m) {
			System.out.println("infinite");
		} else if (lowest * n >= m) {
			System.out.println(m / n);
		} else {
			AbstractBinarySearch abs = new AbstractBinarySearch() {
				@Override
				protected int comparator(long mid) {
					long sum = 0;
					for (long b : a) sum += min(b, mid);
					return sum > m ? 1 : sum == m ? 0 : -1;
				}
			};
			int ans = abs.upperBoundSearch((int) lowest, (int) highest);
			System.out.println(ans < 0 ? ~ans - 1 : ans);
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

	@SuppressWarnings("unused")
	private static class MathFn {

		/**
		 * a / b以下で最大の長整数を返します。
		 *
		 * @param a 割られる値(long)
		 * @param b 割る値(long)
		 * @return ⌊a / b⌋
		 */
		public static long floorLong(long a, long b) {
			return a < 0 ? (a - b + 1) / b : a / b;
		}

		/**
		 * a / b以下で最大の整数を返します。
		 *
		 * @param a 割られる値(int)
		 * @param b 割る値(int)
		 * @return ⌊a / b⌋
		 */
		public static int floorInt(int a, int b) {
			return a < 0 ? (a - b + 1) / b : a / b;
		}

		/**
		 * a / b以上で最大の長整数を返します。
		 *
		 * @param a 割られる値(long)
		 * @param b 割る値(long)
		 * @return ⌈a / b⌉
		 */
		public static long ceilLong(long a, long b) {
			return a < 0 ? a / b : (a + b - 1) / b;
		}

		/**
		 * a / b以上で最大の整数を返します。
		 *
		 * @param a 割られる値(int)
		 * @param b 割る値(int)
		 * @return ⌈a / b⌉
		 */
		public static long ceilInt(int a, int b) {
			return a < 0 ? a / b : (a + b - 1) / b;
		}

		/**
		 * doubleの高速なフォーマット。小数点第n - 1位を四捨五入します。
		 *
		 * @param x フォーマットする対象
		 * @param n 少数点以下の桁数
		 * @return String
		 */
		public static String formatDouble(double x, int n) {
			StringBuilder sb = new StringBuilder();
			if (n == 0) return sb.append(round(x)).toString();
			if (x < 0) {
				sb.append("-");
				x = -x;
			}
			x += pow(10, -n) / 2;
			sb.append((long) x).append(".");
			x -= (long) x;
			while (n-- > 0) {
				x *= 10;
				sb.append((int) x);
				x -= (int) x;
			}
			return sb.toString();
		}

		/**
		 * a ^ b % mod を求めます。
		 *
		 * @param a   底
		 * @param b   指数
		 * @param mod 割る値(素数)
		 * @return a ^ b % mod
		 */
		public static double modPow(long a, long b, long mod) {
			long ans = 1;
			boolean f = b < 0;
			if (f) b = -b;
			b %= mod - 1;
			for (; b > 0; a = a * a % mod, b >>= 1) {
				if ((b & 1) == 1)
					ans = ans * a % mod;
			}
			return f ? (double) 1 / ans : ans;
		}

		/**
		 * aのb乗を求めます
		 *
		 * @param a 底
		 * @param b 指数
		 * @return a ^ b
		 */
		public static double pow(double a, long b) {
			double ans = 1;
			boolean f = b < 0;
			if (f) b = -b;
			for (; b > 0; a *= a, b >>= 1) {
				if ((b & 1) == 1)
					ans *= a;
			}
			return f ? (double) 1 / ans : ans;
		}

		/**
		 * nが20以下の階乗を返します。
		 *
		 * @param n int
		 * @return n!
		 */
		public static long factorial_max20(int n) {
			long[] SmallFactorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800,
					39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L,
					20922789888000L, 355687428096000L, 6402373705728000L,
					121645100408832000L, 2432902008176640000L};
			return SmallFactorials[n];
		}

		/**
		 * nの階乗をmodで割った余りを計算します。
		 *
		 * @param n   int
		 * @param mod long
		 * @return n! % mod
		 */
		public static long modFactorial(int n, long mod) {
			if (n <= 20) return factorial_max20(n) % mod;
			if (n >= mod) return 0;
			if (n == mod - 1) return n;
			int twoExponent = n - Integer.bitCount(n);
			long[] oddPart = new long[(n >> 1) + 1];
			oddPart[0] = 1;
			for (int i = 3; i <= n; i += 2) {
				int idx = i >> 1;
				oddPart[idx] = oddPart[idx - 1] * i % mod;
			}
			long ans = oddPart[(n - 1) >> 1];
			for (int i = 1; n >> i > 1; i++) {
				int k = n;
				if ((k >>= i) % 2 == 1) {
					ans = ans * oddPart[k >> 1] % mod;
				} else {
					ans = ans * oddPart[(k - 1) >> 1] % mod;
				}
			}
			long twoExp = (1L << 62) % mod;
			while (twoExponent > 62) {
				ans = ans * twoExp % mod;
				twoExponent -= 62;
			}
			return ans * ((1L << twoExponent) % mod) % mod;
		}

		/**
		 * 平方数かどうかの判定
		 *
		 * @param s 判定する対象
		 * @return boolean
		 */
		public static boolean isSquareNum(long s) {
			long sqrt = round(sqrt(s));
			return s == sqrt * sqrt;
		}

		/**
		 * 立方数かどうかの判定
		 *
		 * @param c 判定する対象
		 * @return boolean
		 */
		public static boolean isCubicNum(long c) {
			long cbrt = round(cbrt(c));
			return c == cbrt * cbrt * cbrt;
		}

		/**
		 * nCrを求めます。
		 *
		 * @param n 二項係数を求めるのに用いる値
		 * @param r 二項係数を求めるのに用いる値
		 * @return nCr
		 */
		public static long comb(long n, long r) {
			if (n < 0 || r < 0) return 0;
			long ans = 1;
			r = min(n - r, r);
			for (int i = 1; i <= r; i++) {
				ans *= n - i;
				ans /= i + 1;
			}
			return ans;
		}

		/**
		 * nCrをmodで割った余りを求めます。
		 *
		 * @param n   二項係数を求めるのに用いる値
		 * @param r   二項係数を求めるのに用いる値
		 * @param mod 法とする整数
		 * @return nCr % mod
		 */
		public static long modComb(long n, long r, long mod) {
			if (n < 0 || r < 0) return 0;
			long ans = 1;
			r = min(n - r, r);
			for (int i = 0; i < r; i++) {
				ans = ans * (n - i) % mod;
				ans = ans * (long) modPow(i + 1, mod - 2, mod) % mod;
			}
			return ans;
		}

		public static long isCrossed(int Ax1, int Ay1, int Ax2, int Ay2, int Bx1, int By1, int Bx2, int By2) {
			return 1;
		}

		/**
		 * 最小公倍数を求めます
		 *
		 * @param x long
		 * @param y long
		 * @return x * (y / GCD(x, y))
		 */
		public static long LCM(long x, long y) {
			return x == 0 || y == 0 ? 0 : x * (y / GCD(x, y));
		}

		/**
		 * 最大公約数を求めます
		 *
		 * @param x long
		 * @param y long
		 * @return GCD(x, y)
		 */
		public static long GCD(long x, long y) {
			return y > 0 ? GCD(y, x % y) : x;
		}

		/**
		 * ax + by = GCD(x, y)となるx, yの組を見つけます。
		 *
		 * @param a long
		 * @param b long
		 * @param x AtomicLong
		 * @param y AtomicLong
		 * @return |x| + |y|の最小値
		 */
		public static long exGCD(long a, long b, AtomicLong x, AtomicLong y) {
			if (b == 0) {
				x.set(1);
				y.set(0);
				return a;
			}
			AtomicLong xx = new AtomicLong(), yy = new AtomicLong();
			long d = exGCD(b, a % b, yy, xx);
			y.set(yy.get() - (a / b) * xx.get());
			x.set(xx.get());
			return d;
		}

		/**
		 * オイラーのトーシェント関数に基づき1からnまでのnと互いに素な数字の個数を調べます。
		 *
		 * @param n long
		 * @return long
		 */
		public static long EulerTotientFunction(long n) {
			long sum = 1;
			for (int i = 2; i <= 3; i++) {
				long temp = 1;
				while (n % i == 0) {
					n /= i;
					temp *= i;
				}
				sum *= temp - temp / i;
			}
			for (int i = 5; (long) i * i <= n; i += 6) {
				for (int j = i; j <= i + 2; j += 2) {
					if (n % j == 0) {
						long temp = 1;
						while (n % j == 0) {
							n /= j;
							temp *= j;
						}
						sum *= temp - temp / j;
					}
				}
			}
			return n == 1 ? sum : sum * (n - 1);
		}

	}
}
