import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public final class D {

	// region < Constants & Globals >
	private static final boolean DEBUG = false;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out, false);
	// endregion

	private static void solve() {
		PrimeTable table = new PrimeTable(1_000_000);
		int m, n;
		while (((m = sc.nextInt()) | (n = sc.nextInt())) != 0) {
			int hw = iSqrt(m);
			while (hw % 2 == 0 || hw * hw < m) hw++;
			int[][] arr = new int[hw][hw];
			for (int num = hw * hw, i = hw - 1, j = hw - 1, d = 0; num > 0; num--) {
				arr[i][j] = num;
				int ni = i + di[d], nj = j + dj[d];
				if (!isValidRange(ni, nj, hw, hw) || arr[ni][nj] != 0) {
					d = (d + 1) % 4;
				}
				i += di[d];
				j += dj[d];
			}
			int i = 0, j = 0;
			for (int k = 0; k < hw; k++) {
				for (int l = 0; l < hw; l++) {
					if (arr[k][l] == n) {
						i = k;
						j = l;
					} else if (arr[k][l] > m) {
						arr[k][l] = 0;
					}
				}
			}
			int[][] memo = new int[2][m + 1];
			fill(memo[1], -1);
			dfs(i, j, arr, hw, hw, table, memo);
			out.println(memo[0][n] + " " + max(memo[1][n], 0));
		}
	}

	private static void dfs(int i, int j, int[][] arr, int h, int w, PrimeTable table, int[][] memo) {
		int a = arr[i][j];
		if (memo[1][a] != -1) return;
		int cnt = 0, p = -1;
		for (int d : new int[]{3, 6, 7}) {
			int ni = i + di[d], nj = j + dj[d];
			if (isValidRange(ni, nj, h, w) && arr[ni][nj] != 0) {
				dfs(ni, nj, arr, h, w, table, memo);
				int m0 = memo[0][arr[ni][nj]], m1 = memo[1][arr[ni][nj]];
				if (cnt < m0) {
					cnt = m0;
					p = m1;
				} else if (cnt == m0) {
					p = max(p, m1);
				}
			}
		}
		if (table.isPrime(a)) {
			memo[0][a] = cnt + 1;
			memo[1][a] = p == -1 ? a : p;
		} else {
			memo[0][a] = cnt;
			memo[1][a] = p;
		}
	}

	// region < Utility Methods >
	private static boolean isValidRange(final int i, final int from, final int to) {
		return ((i - from) | (to - 1 - i)) >= 0;
	}

	private static boolean isValidRange(final int i, final int j, final int h, final int w) {
		return ((i | j | (h - 1 - i) | (w - 1 - j)) >>> 31) == 0;
	}

	private static void swap(final char[] a, final int i, final int j) {
		final char tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private static void swap(final int[] a, final int i, final int j) {
		final int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private static void swap(final long[] a, final int i, final int j) {
		final long tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private static boolean chmin(final char[] a, final int i, final char v) {
		if (a[i] <= v) return false;
		a[i] = v;
		return true;
	}

	private static boolean chmin(final int[] a, final int i, final int v) {
		if (a[i] <= v) return false;
		a[i] = v;
		return true;
	}

	private static boolean chmin(final long[] a, final int i, final long v) {
		if (a[i] <= v) return false;
		a[i] = v;
		return true;
	}

	private static boolean chmax(final char[] a, final int i, final char v) {
		if (a[i] >= v) return false;
		a[i] = v;
		return true;
	}

	private static boolean chmax(final int[] a, final int i, final int v) {
		if (a[i] >= v) return false;
		a[i] = v;
		return true;
	}

	private static boolean chmax(final long[] a, final int i, final long v) {
		if (a[i] >= v) return false;
		a[i] = v;
		return true;
	}

	private static int min(final int a, final int b) {
		return Math.min(a, b);
	}

	private static int min(final int a, final int b, final int c) {
		return Math.min(a, Math.min(b, c));
	}

	private static int min(int... a) {
		int len = a.length;
		int min = a[0];
		for (int i = 1; i < len; i++) if (min > a[i]) min = a[i];
		return min;
	}

	private static int max(final int a, final int b) {
		return Math.max(a, b);
	}

	private static int max(final int a, final int b, final int c) {
		return Math.max(a, Math.max(b, c));
	}

	private static int max(int... a) {
		int len = a.length;
		int max = a[0];
		for (int i = 1; i < len; i++) if (max < a[i]) max = a[i];
		return max;
	}

	private static long min(final long a, final long b) {
		return Math.min(a, b);
	}

	private static long min(final long a, final long b, final long c) {
		return Math.min(a, Math.min(b, c));
	}

	private static long min(long... a) {
		int len = a.length;
		long min = a[0];
		for (int i = 1; i < len; i++) if (min > a[i]) min = a[i];
		return min;
	}

	private static long max(final long a, final long b) {
		return Math.max(a, b);
	}

	private static long max(final long a, final long b, final long c) {
		return Math.max(a, Math.max(b, c));
	}

	private static long max(long... a) {
		int len = a.length;
		long max = a[0];
		for (int i = 1; i < len; i++) if (max < a[i]) max = a[i];
		return max;
	}

	private static double min(final double a, final double b) {
		return Math.min(a, b);
	}

	private static double min(final double a, final double b, final double c) {
		return Math.min(a, Math.min(b, c));
	}

	private static double min(double... a) {
		int len = a.length;
		double min = a[0];
		for (int i = 1; i < len; i++) if (min > a[i]) min = a[i];
		return min;
	}

	private static double max(final double a, final double b) {
		return Math.max(a, b);
	}

	private static double max(final double a, final double b, final double c) {
		return Math.max(a, Math.max(b, c));
	}

	private static double max(double... a) {
		int len = a.length;
		double max = a[0];
		for (int i = 1; i < len; i++) if (max < a[i]) max = a[i];
		return max;
	}

	private static int diff(final int a, final int b) {
		return a > b ? a - b : b - a;
	}

	private static long diff(final long a, final long b) {
		return a > b ? a - b : b - a;
	}

	private static double diff(final double a, final double b) {
		return a > b ? a - b : b - a;
	}

	private static long lModPow(long a, long b, final long mod) {
		if (b == 0) return 1;
		long ans = 1;
		for (a %= mod; b > 1; b >>= 1) {
			if ((b & 1) == 1) ans = ans * a % mod;
			a = a * a % mod;
		}
		return ans * a % mod;
	}

	private static int iModPow(int a, int b, final int mod) {
		if (b == 0) return 1;
		int ans = 1;
		for (a %= mod; b > 1; b >>= 1) {
			if ((b & 1) == 1) ans = (int) ((long) ans * a % mod);
			a = (int) ((long) a * a % mod);
		}
		return (int) ((long) ans * a % mod);
	}

	private static long lcmLong(final long x, final long y) {
		return x == 0 || y == 0 ? 0 : x / gcdLong(x, y) * y;
	}

	private static int lcmInt(final int x, final int y) {
		return x == 0 || y == 0 ? 0 : x / gcdInt(x, y) * y;
	}

	private static long gcdLong(long a, long b) {
		a = abs(a);
		b = abs(b);
		if (a == 0) return b;
		if (b == 0) return a;
		int commonShift = Long.numberOfTrailingZeros(a | b);
		a >>= Long.numberOfTrailingZeros(a);
		while (b != 0) {
			b >>= Long.numberOfTrailingZeros(b);
			if (a > b) {
				long tmp = a;
				a = b;
				b = tmp;
			}
			b -= a;
		}
		return a << commonShift;
	}

	private static int gcdInt(int a, int b) {
		a = abs(a);
		b = abs(b);
		if (a == 0) return b;
		if (b == 0) return a;
		int commonShift = Integer.numberOfTrailingZeros(a | b);
		a >>= Integer.numberOfTrailingZeros(a);
		while (b != 0) {
			b >>= Integer.numberOfTrailingZeros(b);
			if (a > b) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			b -= a;
		}
		return a << commonShift;
	}

	private static void compression(final int[] a, final int len) {
		final int[] b = copyOf(a, len);
		sort(b);
		int n = 1;
		for (int i = 1; i < len; i++) {
			if (b[i] == b[i - 1]) continue;
			b[n++] = b[i];
		}
		for (int i = 0; i < len; i++) {
			a[i] = binarySearch(b, 0, n, a[i]);
		}
	}

	private static void compression(final int[][] a, final int n, final int m) {
		final int len = n * m;
		final int[] b = new int[len];
		for (int i = 0, j = 0; i < n; i++, j += m) {
			System.arraycopy(a[i], 0, b, j, m);
		}
		sort(b);
		int nm = 1;
		for (int i = 1; i < len; i++) {
			if (b[i] == b[i - 1]) continue;
			b[nm++] = b[i];
		}
		for (int i = 0; i < n; i++) {
			int[] ai = a[i];
			for (int j = 0; j < m; j++) {
				ai[j] = binarySearch(b, 0, nm, ai[j]);
			}
		}
	}

	private static void compression(final int[][] a, final int n) {
		int len = 0;
		for (int i = 0; i < n; i++) len += a[i].length;
		final int[] b = new int[len];
		for (int i = 0, j = 0; i < n; i++) {
			int m = a[i].length;
			System.arraycopy(a[i], 0, b, j, m);
			j += m;
		}
		sort(b);
		int nm = 1;
		for (int i = 1; i < len; i++) {
			if (b[i] == b[i - 1]) continue;
			b[nm++] = b[i];
		}
		for (int i = 0; i < n; i++) {
			int[] ai = a[i];
			for (int j = 0, m = ai.length; j < m; j++) {
				ai[j] = binarySearch(b, 0, nm, ai[j]);
			}
		}
	}

	private static int digit2(long n) {
		if (n == 0) return 1;
		if (n == Long.MIN_VALUE) return 63;
		return 64 - Long.numberOfLeadingZeros(Math.abs(n));
	}

	private static int digit2(int n) {
		if (n == 0) return 1;
		if (n == Integer.MIN_VALUE) return 31;
		return 32 - Integer.numberOfLeadingZeros(Math.abs(n));
	}

	private static int digit10(long n) {
		if (n == Long.MIN_VALUE) return 19;
		if (n < 0) n = -n;
		int res = 0;
		do {
			res++;
			n /= 10;
		} while (n > 0);
		return res;
	}

	private static int digit10(int n) {
		if (n == Integer.MIN_VALUE) return 10;
		if (n < 0) n = -n;
		int res = 0;
		do {
			res++;
			n /= 10;
		} while (n > 0);
		return res;
	}

	private static long lSqrt(final long n) {
		if (n <= 0) return 0;
		long x = (long) sqrt(n);
		while (x * x > n) x--;
		while ((x + 1) * (x + 1) <= n) x++;
		return x;
	}

	private static int iSqrt(final int n) {
		if (n <= 0) return 0;
		return (int) sqrt(n);
	}
	// endregion

	// region < I/O & Debug >
	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			sc.close();
			out.close();
		}
	}

	private static void debugln(final Object... args) {
		if (DEBUG) {
			out.flush();
			System.err.println(stream(args).map(D::stringify).collect(Collectors.joining("\n", "\n", "")));
		}
	}

	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			System.err.println(stream(args).map(D::stringify).collect(Collectors.joining(", ", "", "")));
		}
	}

	private static String stringify(final Object obj) {
		if (obj == null) return "null";
		else if (obj instanceof int[][]) {
			int[][] arr = (int[][]) obj;
			return "\n" + stream(arr).map(Arrays::toString).collect(Collectors.joining("\n"));
		} else if (obj instanceof long[][]) {
			long[][] arr = (long[][]) obj;
			return "\n" + stream(arr).map(Arrays::toString).collect(Collectors.joining("\n"));
		} else if (obj instanceof char[][]) {
			char[][] arr = (char[][]) obj;
			return "\n" + stream(arr).map(String::valueOf).collect(Collectors.joining("\n"));
		} else if (obj instanceof Object[][]) {
			Object[][] arr = (Object[][]) obj;
			return "\n" + stream(arr).map(Arrays::deepToString).collect(Collectors.joining("\n"));
		} else if (obj instanceof int[]) {
			int[] arr = (int[]) obj;
			return Arrays.toString(arr);
		} else if (obj instanceof long[]) {
			long[] arr = (long[]) obj;
			return Arrays.toString(arr);
		} else if (obj instanceof double[]) {
			double[] arr = (double[]) obj;
			return Arrays.toString(arr);
		} else if (obj instanceof char[]) {
			char[] arr = (char[]) obj;
			return Arrays.toString(arr);
		} else if (obj instanceof boolean[]) {
			boolean[] arr = (boolean[]) obj;
			return Arrays.toString(arr);
		} else if (obj instanceof Object[]) {
			Object[] arr = (Object[]) obj;
			return deepToString(arr);
		} else if (obj instanceof Iterable<?>) {
			Iterable<?> it = (Iterable<?>) obj;
			final StringJoiner sj = new StringJoiner(", ", "[", "]");
			for (final Object e : it) sj.add(stringify(e));
			return sj.toString();
		}
		return obj.toString();
	}

	/**
	 * エラトステネスの篩を用いて最大Integer.MAXVALUEの値
	 */
	@SuppressWarnings("unused")
	private static final class PrimeTable implements Iterable<Long> {
		private final long MAX_VALUE;
		private final long[] oddBits;
		private long[] primes;
		private int cnt;

		public PrimeTable(final long n) {
			cnt = 0;
			MAX_VALUE = n;
			oddBits = new long[(int) (((n >>> 1) + 63) >>> 6) + 1];
			sieve(n);
		}

		public boolean isPrime(final long n) {
			if (MAX_VALUE < n) throw new IllegalArgumentException();
			if (n == 1) return false;
			if ((n & 1) == 0) return n == 2;
			return !isCompositeOdd(n);
		}

		public int countPrimesUpTo(final long n) {
			if (MAX_VALUE < n) throw new IllegalArgumentException();
			int index = binarySearch(primes, n);
			return index < 0 ? ~index : index + 1;
		}

		public long ceilingPrime(final long n) {
			if (MAX_VALUE < n) throw new IllegalArgumentException();
			int index = binarySearch(primes, n);
			index = index < 0 ? ~index : index;
			return index >= cnt ? -1 : primes[index];
		}

		public long higherPrime(final long n) {
			return ceilingPrime(n + 1);
		}

		public long floorPrime(final long n) {
			if (MAX_VALUE < n) throw new IllegalArgumentException();
			int index = binarySearch(primes, n);
			index = index < 0 ? ~index - 1 : index;
			return index < 0 ? -1 : primes[index];
		}

		public long lowerPrime(final long n) {
			return floorPrime(n - 1);
		}

		public long kthPrime(final int i) {
			if (i < 0 || cnt <= i) throw new IllegalArgumentException();
			return primes[i];
		}

		public Map<Long, Integer> primeFactorize(long n) {
			if (n < 0) throw new IllegalArgumentException();
			Map<Long, Integer> factors = new HashMap<>();
			int i = 0;
			while (n > 1) {
				if (i >= cnt) throw new IllegalArgumentException();
				long prime = primes[i++];
				while (n % prime == 0) {
					factors.put(prime, factors.getOrDefault(prime, 0) + 1);
					n /= prime;
				}
			}
			return factors;
		}

		public PrimitiveIterator.OfLong iterator() {
			return new PrimitiveIterator.OfLong() {
				private int index = 0;

				@Override
				public boolean hasNext() {
					return index < cnt;
				}

				@Override
				public long nextLong() {
					if (!hasNext()) throw new NoSuchElementException();
					return primes[index++];
				}
			};
		}

		public LongStream stream() {
			return Arrays.stream(primes);
		}

		private void sieve(final long n) {
			int estimatedCapacity = n < 20 ? 10 : (int) (1.05 * n / (log(n) - 1.0));
			primes = new long[estimatedCapacity];
			final long sqrtN = (long) sqrt(n);

			if (2 <= n) primes[cnt++] = 2;
			if (3 <= n) primes[cnt++] = 3;

			for (long i = 9; i <= n; i += 6) setCompositeOdd(i);
			for (long i = 5; i <= n; i += 6) {
				for (long j = i; j <= i + 2 && j <= n; j += 2) {
					if (isCompositeOdd(j)) continue;
					primes[cnt++] = j;
					if (j > sqrtN) continue;
					final long step = j * 6;
					final long delta2 = j * 2;
					for (long k = j * j; k <= n; k += step) {
						setCompositeOdd(k);
						long k2 = k + delta2;
						if (k2 > n) break;
						setCompositeOdd(k2);
						long k4 = k2 + delta2;
						if (k4 > n) break;
						setCompositeOdd(k4);
					}
				}
			}
			primes = copyOf(primes, cnt);
		}

		private int bitIndex(final long n) {
			return (int) (n >>> 7);
		}

		private long bitMask(final long n) {
			return 1L << ((n >>> 1) & 63);
		}

		private void setCompositeOdd(final long n) {
			oddBits[bitIndex(n)] |= bitMask(n);
		}

		private boolean isCompositeOdd(final long n) {
			return (oddBits[bitIndex(n)] & bitMask(n)) != 0;
		}
	}
	// endregion
}
