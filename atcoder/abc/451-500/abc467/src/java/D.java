import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import lib.io.*;

public final class D {

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
		int t = sc.nextInt();
		while (t-- > 0) {
			long px = sc.nextLong(), py = sc.nextLong();
			long qx = sc.nextLong(), qy = sc.nextLong();
			long rx = sc.nextLong(), ry = sc.nextLong();
			long sx = sc.nextLong(), sy = sc.nextLong();
			long[][] a = {{2 * (px - qx), 2 * (py - qy)}, {2 * (rx - sx), 2 * (ry - sy)}};
			Matrix matA = new Matrix(a);
			matA.inverse();
			long[][] mat = {{2 * (px - qx), 2 * (py - qy), px * px + py * py - qx * qx - qy * qy}, {2 * (rx - sx), 2 * (ry - sy), rx * rx + ry * ry - sx * sx - sy * sy}};
			Matrix matrix = new Matrix(mat);
			matrix.inverse();
			out.println(matrix.rank == matA.rank);
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
		if (x * x > n) x--;
		else if ((x + 1) * (x + 1) <= n) x++;
		return x;
	}

	private static int iSqrt(final int n) {
		if (n <= 0) return 0;
		return (int) sqrt(n);
	}

	private static void prefix2D(final int[][] a, final IntBinaryOperator op) {
		for (int[] ai : a) parallelPrefix(ai, op);
		for (int i = 1, h = a.length, w = a[0].length; i < h; i++) {
			for (int j = 0; j < w; j++) {
				a[i][j] = op.applyAsInt(a[i][j], a[i - 1][j]);
			}
		}
	}

	private static void prefix2D(final long[][] a, final LongBinaryOperator op) {
		for (long[] ai : a) parallelPrefix(ai, op);
		for (int i = 1, h = a.length, w = a[0].length; i < h; i++) {
			for (int j = 0; j < w; j++) {
				a[i][j] = op.applyAsLong(a[i][j], a[i - 1][j]);
			}
		}
	}

	private static int rectSum(final int[][] a, final int i1, final int j1, final int i2, final int j2) {
		int ans = a[i2][j2];
		if (i1 > 0) ans -= a[i1 - 1][j2];
		if (j1 > 0) ans -= a[i2][j1 - 1];
		if (i1 > 0 && j1 > 0) ans += a[i1 - 1][j1 - 1];
		return ans;
	}

	private static long rectSum(final long[][] a, final int i1, final int j1, final int i2, final int j2) {
		long ans = a[i2][j2];
		if (i1 > 0) ans -= a[i1 - 1][j2];
		if (j1 > 0) ans -= a[i2][j1 - 1];
		if (i1 > 0 && j1 > 0) ans += a[i1 - 1][j1 - 1];
		return ans;
	}
	// endregion

	// region < main & debug >
	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static void debugln(final Object... args) {
		if (DEBUG) {
			out.flush();
			if (args == null) System.err.println("null");
			else if (args.getClass().getComponentType().isArray()) System.err.println(stringify(args));
			else System.err.println(stream(args).map(D::stringify).collect(Collectors.joining("\n", "\n", "")));
		}
	}

	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			if (args == null) System.err.println("null");
			else if (args.getClass().getComponentType().isArray()) System.err.println(stringify(args));
			else System.err.println(stream(args).map(D::stringify).collect(Collectors.joining(", ", "", "")));
		}
	}

	private static String stringify(final Object obj) {
		return switch (obj) {
			case null -> "null";
			case int[][] arr -> "\n" + stream(arr).map(Arrays::toString).collect(Collectors.joining("\n"));
			case long[][] arr -> "\n" + stream(arr).map(Arrays::toString).collect(Collectors.joining("\n"));
			case char[][] arr -> "\n" + stream(arr).map(String::valueOf).collect(Collectors.joining("\n"));
			case Object[][] arr -> "\n" + stream(arr).map(Arrays::deepToString).collect(Collectors.joining("\n"));
			case int[] arr -> Arrays.toString(arr);
			case long[] arr -> Arrays.toString(arr);
			case double[] arr -> Arrays.toString(arr);
			case char[] arr -> Arrays.toString(arr);
			case boolean[] arr -> Arrays.toString(arr);
			case Object[] arr -> deepToString(arr);
			case Iterable<?> it -> {
				final StringJoiner sj = new StringJoiner(", ", "[", "]");
				for (final Object e : it) sj.add(stringify(e));
				yield sj.toString();
			}
			default -> obj.toString();
		};
	}
	// endregion

	@SuppressWarnings("unused")
	private static class Matrix {
		// 行列 -> 分数の2次元配列
		// m * n次行列
		private final Fraction[][] matrix;
		private final int m;
		private final int n;
		private int rank;
		private Fraction determinant;

		Matrix(int[][] A) {
			m = A.length;
			n = A[m - 1].length;
			matrix = new Fraction[m][n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = new Fraction(A[i][j]);
				}
			}
		}

		Matrix(long[][] A) {
			m = A.length;
			n = A[m - 1].length;
			matrix = new Fraction[m][n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = new Fraction(A[i][j]);
				}
			}
		}

		Matrix(Fraction[][] matrix) {
			m = matrix.length;
			n = matrix[m - 1].length;
			this.matrix = matrix;
		}

		private void add(Matrix mat) {
			// 行列の和
			if (m != mat.m || n != mat.n) {
				throw new ArithmeticException("not defined!");
			}
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = matrix[i][j].add(mat.matrix[i][j]);
				}
			}
		}

		private void subtract(Matrix mat) {
			// 行列の差
			if (m != mat.m || n != mat.n) {
				throw new ArithmeticException("not defined!");
			}
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = matrix[i][j].subtract(mat.matrix[i][j]);
				}
			}
		}

		private Matrix multiply(Matrix mat) {
			// 行列の積
			if (n != mat.m) {
				throw new ArithmeticException("not defined!");
			}
			int p = mat.n;
			Matrix AB = new Matrix(new long[m][p]);
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < p; j++) {
					for (int k = 0; k < n; k++) {
						Fraction product = matrix[i][k].multiply(mat.matrix[k][j]);
						AB.matrix[i][j] = AB.matrix[i][j].add(product);
					}
				}
			}
			return AB;
		}

		private Matrix multiply(Fraction[][] mat) {
			// 行列の積
			if (n != mat.length) {
				throw new ArithmeticException("not defined!");
			}
			int p = mat[0].length;
			Matrix AB = new Matrix(new long[m][p]);
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < p; j++) {
					for (int k = 0; k < n; k++) {
						Fraction product = matrix[i][k].multiply(mat[k][j]);
						AB.matrix[i][j] = AB.matrix[i][j].add(product);
					}
				}
			}
			return AB;
		}

		private Matrix multiply(Fraction k) {
			// スカラー倍
			Matrix kA = new Matrix(new Fraction[m][n]);
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					kA.matrix[i][j] = matrix[i][j].multiply(k);
				}
			}
			return kA;
		}

		private Matrix multiply(long k) {
			// スカラー倍
			Matrix kA = new Matrix(new Fraction[m][n]);
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					kA.matrix[i][j] = matrix[i][j].multiply(k);
				}
			}
			return kA;
		}

		private Matrix pow(int k) {
			// 行列の累乗
			Matrix ans = identityMat();
			for (Matrix temp = new Matrix(matrix); k > 0; temp = temp.multiply(temp), k >>= 1) {
				if ((k & 1) == 1) ans = ans.multiply(temp);
			}
			return ans;
		}

		private Matrix transpose() {
			// 転置行列
			Matrix mat = new Matrix(new Fraction[n][m]);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					mat.matrix[i][j] = matrix[j][i];
				}
			}
			return mat;
		}

		private Fraction det2D() {
			// 2次の行列式
			if (m == 2 && n == 2) {
				Fraction det = matrix[0][0].multiply(matrix[1][1]);
				determinant = det.subtract(matrix[0][1].multiply(matrix[1][0]));
				return determinant;
			} else {
				throw new ArithmeticException("not 2D!");
			}
		}

		private Fraction det2D_ex() {
			// 2次の拡大係数行列の行列式
			if (m == 2 && n == 3) {
				Fraction det = matrix[0][0].multiply(matrix[1][1]);
				determinant = det.subtract(matrix[0][1].multiply(matrix[1][0]));
				return determinant;
			} else {
				throw new ArithmeticException("not 2D_ex!");
			}
		}

		private Fraction det3D() {
			// 3次の行列式
			if (m == 3 && n == 3) {
				Fraction det = matrix[0][0].multiply(matrix[1][1]).multiply(matrix[2][2]);
				det = det.add(matrix[0][1].multiply(matrix[1][2]).multiply(matrix[2][0]));
				det = det.add(matrix[0][2].multiply(matrix[1][0]).multiply(matrix[2][1]));
				det = det.subtract(matrix[0][0].multiply(matrix[1][2]).multiply(matrix[2][1]));
				det = det.subtract(matrix[0][1].multiply(matrix[1][0]).multiply(matrix[2][2]));
				determinant = det.subtract(matrix[0][2].multiply(matrix[1][1]).multiply(matrix[2][0]));
				return determinant;
			} else {
				throw new ArithmeticException("not 3D!");
			}
		}

		private Fraction det3D_ex() {
			// 3次の拡大係数行列の行列式
			if (m == 3 && n == 4) {
				Fraction det = matrix[0][0].multiply(matrix[1][1]).multiply(matrix[2][2]);
				det = det.add(matrix[0][1].multiply(matrix[1][2]).multiply(matrix[2][0]));
				det = det.add(matrix[0][2].multiply(matrix[1][0]).multiply(matrix[2][1]));
				det = det.subtract(matrix[0][0].multiply(matrix[1][2]).multiply(matrix[2][1]));
				det = det.subtract(matrix[0][1].multiply(matrix[1][0]).multiply(matrix[2][2]));
				determinant = det.subtract(matrix[0][2].multiply(matrix[1][1]).multiply(matrix[2][0]));
				return determinant;
			} else {
				throw new ArithmeticException("not 3D_ex!");
			}
		}

		private Fraction det() {
			// n次の行列式
			if (m == n) {
				if (m == 2) return det2D();
				if (m == 3) return det3D();
				Fraction det = new Fraction(0);
				for (int i = 0; i < m; i++) {
					if (matrix[i][0].equals(new Fraction(0))) continue;
					Fraction[][] minor = new Fraction[m - 1][m - 1];
					for (int k = 0, s = 0; k < m; k++) {
						if (k == i) continue;
						System.arraycopy(matrix[k], 1, minor[s], 0, m - 1);
						s++;
					}
					Fraction D = matrix[i][0].multiply(LaplaceExpansion(i, m - 1, minor));
					if (i % 2 != 0) {
						det = det.add(D.multiply(-1));
					} else {
						det = det.add(D);
					}
				}
				determinant = det;
				return det;
			} else {
				throw new ArithmeticException("not defined!");
			}
		}

		private Fraction LaplaceExpansion(int i, int m, Fraction[][] matrix) {
			if (m == 3) {
				Matrix mat = new Matrix(matrix);
				return mat.det3D();
			}
			Fraction det = new Fraction(0);
			for (i = 0; i < m; i++) {
				if (matrix[i][0].equals(new Fraction(0))) continue;
				Fraction[][] minor = new Fraction[m - 1][m - 1];
				for (int k = 0, s = 0; k < m; k++) {
					if (k == i) continue;
					System.arraycopy(matrix[k], 1, minor[s], 0, m - 1);
					s++;
				}
				Fraction D = matrix[i][0].multiply(LaplaceExpansion(i, m - 1, minor));
				if (i % 2 != 0) {
					det = det.add(D.multiply(-1));
				} else {
					det = det.add(D);
				}
			}
			return det;
		}

		private Matrix identityMat() {
			// min(m, n)次の単位行列の作成
			int k = Math.min(m, n);
			int[][] A = new int[k][k];
			for (int i = 0; i < k; i++) {
				A[i][i] = 1;
			}
			Matrix identity = new Matrix(A);
			return identity;
		}

		private Matrix inverse2D() {
			// 2次の逆行列
			if (m == 2 && n == 2) {
				Fraction det = det2D();
				Matrix inverse = new Matrix(new Fraction[2][2]);
				inverse.matrix[0][0] = matrix[1][1];
				inverse.matrix[0][1] = matrix[0][1].multiply(-1);
				inverse.matrix[1][1] = matrix[0][0];
				inverse.matrix[1][0] = matrix[1][0].multiply(-1);
				return inverse.multiply(det.getInverse());
			} else {
				throw new ArithmeticException("not 2D!");
			}
		}

		private Matrix inverse2D_ex() {
			// 2次の拡大係数行列の逆行列
			if (m == 2 && n == 3) {
				Fraction det = det2D_ex();
				Matrix inverse = new Matrix(new Fraction[2][2]);
				inverse.matrix[0][0] = matrix[1][1];
				inverse.matrix[0][1] = matrix[0][1].multiply(-1);
				inverse.matrix[1][1] = matrix[0][0];
				inverse.matrix[1][0] = matrix[1][0].multiply(-1);
				return inverse.multiply(det.getInverse());
			} else {
				throw new ArithmeticException("not 2D!");
			}
		}

		private Matrix inverse() {
			// n次の逆行列
			Matrix inverse = identityMat();
			rank = 0;
			for (int i = 0; i < n; i++) {
				for (int j = i; j < m; j++) {
					if (matrix[j][i].numerator != 0) {
						rank++;
						if (j != i) {
							swapRow(i, j);
							inverse.swapRow(i, j);
						}
						Fraction mul = matrix[i][i].getInverse();
						multiplyRow(i, mul);
						inverse.multiplyRow(i, mul);
						for (int k = 0; k < m; k++) {
							if (k == i) continue;
							long num = matrix[k][i].numerator;
							long den = matrix[k][i].denominator;
							addMultiplyRow(k, i, -num, den);
							inverse.addMultiplyRow(k, i, -num, den);
						}
						break;
					}
				}
			}
			return inverse;
		}

		private Matrix inverse_ex() {
			// n次の拡大係数行列の連立方程式
			rank = 0;
			Matrix inverse = identityMat();
			for (int i = 0; i < n - 1; i++) {
				for (int j = i; j < m; j++) {
					if (matrix[j][i].numerator != 0) {
						rank++;
						if (j != i) {
							swapRow(i, j);
							inverse.swapRow(i, j);
						}
						Fraction mul = matrix[i][i].getInverse();
						multiplyRow(i, mul);
						inverse.multiplyRow(i, mul);
						for (int k = 0; k < m; k++) {
							if (k == i) continue;
							long num = matrix[k][i].numerator;
							long den = matrix[k][i].denominator;
							addMultiplyRow(k, i, -num, den);
							inverse.addMultiplyRow(k, i, -num, den);
						}
						break;
					}
				}
			}
			return inverse;
		}

		private Fraction[] simultaneousEquation() {
			// 拡大係数行列の連立方程式
			rank = 0;
			Matrix inverse = identityMat();
			for (int i = 0; i < n - 1; i++) {
				for (int j = i; j < m; j++) {
					if (matrix[j][i].numerator != 0) {
						rank++;
						if (j != i) {
							swapRow(i, j);
						}
						Fraction mul = matrix[i][i].getInverse();
						multiplyRow(i, mul);
						for (int k = 0; k < m; k++) {
							if (k == i) continue;
							long num = matrix[k][i].numerator;
							long den = matrix[k][i].denominator;
							addMultiplyRow(k, i, -num, den);
						}
						break;
					}
				}
			}
			Fraction[] x = new Fraction[rank];
			for (int i = 0; i < rank; i++) {
				x[i] = matrix[i][n - 1];
			}
			return x;
		}

		private void swapRow(int i, int j) {
			// 行の入れ替え
			for (int idx = 0; idx < n; idx++) {
				Fraction temp = matrix[i][idx];
				matrix[i][idx] = matrix[j][idx];
				matrix[j][idx] = temp;
			}
		}

		private void addMultiplyRow(int i, int j, long k, long l) {
			// 行ｊ を k / l倍して行i に加える
			Fraction f = new Fraction(k, l);
			for (int idx = 0; idx < n; idx++) {
				matrix[i][idx] = matrix[i][idx].add(matrix[j][idx].multiply(f));
			}
		}

		private void addMultiplyRow(int i, int j, Fraction f) {
			// 行ｊ を f 倍して行i に加える
			for (int idx = 0; idx < n; idx++) {
				matrix[i][idx] = matrix[i][idx].add(matrix[j][idx].multiply(f));
			}
		}

		private void multiplyRow(int i, long k, long l) {
			// 行i を k / l倍
			Fraction f = new Fraction(k, l);
			for (int idx = 0; idx < n; idx++) {
				matrix[i][idx] = matrix[i][idx].multiply(f);
			}
		}

		private void multiplyRow(int i, Fraction f) {
			// 行i を f倍
			for (int idx = 0; idx < n; idx++) {
				matrix[i][idx] = matrix[i][idx].multiply(f);
			}
		}

		public boolean equals(Object mat) {
			// 二つの行列が等しいかどうか
			return toString().equals(mat.toString());
		}

		public String toString() {
			StringJoiner sj = new StringJoiner("\n");
			for (int i = 0; i < m; i++) {
				StringJoiner sj2 = new StringJoiner(" ");
				for (int j = 0; j < n; j++) {
					sj2.add(matrix[i][j].toString());
				}
				sj.add(sj2.toString());
			}
			return sj.toString();
		}
	}

	@SuppressWarnings("unused")
	private static class Fraction {
		private long numerator;
		private long denominator;

		Fraction(long numerator, long denominator) {
			if (denominator == 0) throw new ArithmeticException(" / by zero");
			this.numerator = numerator;
			this.denominator = denominator;
			reduction();
		}

		Fraction(long numerator) {
			this.numerator = numerator;
			this.denominator = 1;
		}

		Fraction(Fraction f) {
			numerator = f.numerator;
			denominator = f.denominator;
		}

		private void reduction() {
			long gcd = gcdLong(numerator, denominator);
			numerator /= gcd;
			denominator /= gcd;
			if (denominator < 0) {
				numerator *= -1L;
				denominator *= -1L;
			}
		}

		private Fraction add(Fraction f) {
			long n = numerator * f.denominator + f.numerator * denominator;
			long d = denominator * f.denominator;
			return new Fraction(n, d);
		}

		private Fraction add(long k) {
			long n = numerator + k * denominator;
			long d = denominator;
			return new Fraction(n, d);
		}

		private Fraction subtract(Fraction f) {
			long n = numerator * f.denominator - f.numerator * denominator;
			long d = denominator * f.denominator;
			return new Fraction(n, d);
		}

		private Fraction subtract(long k) {
			long n = numerator - k * denominator;
			long d = denominator;
			return new Fraction(n, d);
		}

		private Fraction multiply(Fraction f) {
			long n = numerator * f.numerator;
			long d = denominator * f.denominator;
			return new Fraction(n, d);
		}

		private Fraction multiply(long k) {
			long n = numerator * k;
			long d = denominator;
			return new Fraction(n, d);
		}

		private Fraction divide(Fraction f) {
			long n = numerator * f.denominator;
			long d = denominator * f.numerator;
			return new Fraction(n, d);
		}

		private Fraction divide(long k) {
			long n = numerator;
			long d = denominator * k;
			return new Fraction(n, d);
		}

		private void pow(int i) {
			long nmrtr = 1L;
			long dnmntr = 1L;
			while (i > 0) {
				if ((i & 1) == 1) {
					nmrtr *= numerator;
					dnmntr *= denominator;
				}
				numerator *= numerator;
				denominator *= denominator;
				i >>= 1;
			}
			numerator = nmrtr;
			denominator = dnmntr;
		}

		private long getNumerator() {
			return numerator;
		}

		private long getDenominator() {
			return denominator;
		}

		private Fraction getInverse() {
			return new Fraction(denominator, numerator);
		}

		private String getDecimal() {
			double d = (double) numerator / denominator;
			return String.format("%.5f", d);
		}

		public boolean equals(Object f) {
			return toString().equals(f.toString());
		}

		public String toString() {
			if (denominator == 1) {
				return Long.toString(numerator);
			}
			return numerator + "/" + denominator;
		}
	}
}
