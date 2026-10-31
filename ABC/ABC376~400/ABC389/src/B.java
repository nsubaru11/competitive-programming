import static java.lang.Math.*;

import java.util.concurrent.atomic.*;

import lib.io.*;

public class B {

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

	//	private static final FastPrinter out = new FastPrinter();

	private static void solve() {
		long x = sc.nextLong();
		for (int i = 2; i <= 20; i++) {
			if (x == MathFn.factorial_max20(i)) {
				System.out.println(i);
				return;
			}
		}
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
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
		 * a / b以上で最小の長整数を返します。
		 *
		 * @param a 割られる値(long)
		 * @param b 割る値(long)
		 * @return ⌈a / b⌉
		 */
		public static long ceilLong(long a, long b) {
			return a < 0 ? a / b : (a + b - 1) / b;
		}

		/**
		 * a / b以上で最小の整数を返します。
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
		public static String strDouble(double x, int n) {
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
				if ((b & 1) == 1) ans = ans * a % mod;
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
				if ((b & 1) == 1) ans *= a;
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
			long[] SmallFactorials = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
			return SmallFactorials[n];
		}

		/**
		 * nの階乗をmodで割った余りを計算します。modは素数であることを要求します。
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

		/**
		 * 二次元平面における線分A1-A2とB1-B2が交差しているかどうか判定します。
		 * A1-A2とA1-B1の外積とA1-A2とA1-B2の外積との内積を求めることで判定します。
		 *
		 * @return 完全に交差している場合は1、線分が全く触れていない場合-1、そうでない場合は0を返します。
		 */
		public static int isCrossed2D(int Ax1, int Ay1, int Ax2, int Ay2, int Bx1, int By1, int Bx2, int By2) {
			int z1 = (Ax2 - Ax1) * (By1 - Ay1) - (Ay2 - Ay1) * (Bx1 - Ax1);
			int z2 = (Ax2 - Ax1) * (By2 - Ay1) - (Ay2 - Ay1) * (Bx2 - Ax1);
			int ans = dotProduct3D(0, 0, z1, 0, 0, z2);
			z1 = (Bx2 - Bx1) * (Ay1 - By1) - (By2 - By1) * (Ax1 - Bx1);
			z2 = (Bx2 - Bx1) * (Ay2 - By1) - (By2 - By1) * (Ax2 - Bx1);
			int ans2 = dotProduct3D(0, 0, z1, 0, 0, z2);
			return ans < 0 && ans2 < 0 ? 1 : ans == 0 ? 0 : -1;
		}

		/**
		 * 三次元空間における線分A1-A2とB1-B2が交差しているかどうか判定します。
		 *
		 * @return 完全に交差している場合は1、線分が全く触れていない場合-1、そうでない場合は0を返します。
		 */
		public static int isCrossed3D(int Ax1, int Ay1, int Az1, int Ax2, int Ay2, int Az2, int Bx1, int By1, int Bz1, int Bx2, int By2, int Bz2) {
			int x1 = (Ay2 - Ay1) * (Bz1 - Az1) - (Az2 - Az1) * (By1 - Ay1);
			int y1 = (Az2 - Az1) * (Bx1 - Ax1) - (Ax2 - Ax1) * (Bz1 - Az1);
			int z1 = (Ax2 - Ax1) * (By1 - Ay1) - (Ay2 - Ay1) * (Bx1 - Ax1);
			int y2 = (Az2 - Az1) * (Bx2 - Ax1) - (Ax2 - Ax1) * (Bz2 - Az1);
			int x2 = (Ay2 - Ay1) * (Bz2 - Az1) - (Az2 - Az1) * (By2 - Ay1);
			int z2 = (Ax2 - Ax1) * (By2 - Ay1) - (Ay2 - Ay1) * (Bx2 - Ax1);
			int ans = dotProduct3D(x1, y1, z1, x2, y2, z2);
			x1 = (By2 - By1) * (Az1 - Bz1) - (Bz2 - Bz1) * (Ay1 - By1);
			y1 = (Bz2 - Bz1) * (Ax1 - Bx1) - (Bx2 - Bx1) * (Az1 - Bz1);
			z1 = (Bx2 - Bx1) * (Ay1 - By1) - (By2 - By1) * (Ax1 - Bx1);
			y2 = (Bz2 - Bz1) * (Ax2 - Bx1) - (Bx2 - Bx1) * (Az2 - Bz1);
			x2 = (By2 - By1) * (Az2 - Bz1) - (Bz2 - Bz1) * (Ay2 - By1);
			z2 = (Bx2 - Bx1) * (Ay2 - By1) - (By2 - By1) * (Ax2 - Bx1);
			int ans2 = dotProduct3D(x1, y1, z1, x2, y2, z2);
			return ans < 0 && ans2 < 0 ? 1 : ans == 0 ? 0 : -1;
		}

		/**
		 * 二次元ベクトルの内積を求めます。
		 *
		 * @return x1 * x2 + y1 * y2
		 */
		public static int dotProduct2D(int x1, int y1, int x2, int y2) {
			return x1 * x2 + y1 * y2;
		}

		/**
		 * 三次元ベクトルの内積を求めます。
		 *
		 * @return x1 * x2 + y1 * y2 + z1 * z2
		 */
		public static int dotProduct3D(int x1, int y1, int z1, int x2, int y2, int z2) {
			return x1 * x2 + y1 * y2 + z1 * z2;
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
