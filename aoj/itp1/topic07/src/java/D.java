import java.io.*;
import java.util.*;

public class D {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out, false);

	public static void main(String[] args) {
		int m = sc.nextInt();
		int n = sc.nextInt();
		int l = sc.nextInt();
		long[][] A = nextLongMat(sc, m, n);
		long[][] B = nextLongMat(sc, n, l);
		out.println(format(mul(A, B)));
		out.flush();
	}

	private static void checkMulSize(final long[][] a, final long[][] b) {
		if (a[0].length != b.length) throw new ArithmeticException("not defined.");
	}

	public static long[][] mul(final long[][] a, final long[][] b) {
		checkMulSize(a, b);
		final int n = a.length, m = a[0].length, l = b[0].length;
		final long[][] res = new long[n][l];
		for (int i = 0; i < n; i++) {
			for (int k = 0; k < m; k++) {
				if (a[i][k] == 0) continue;
				for (int j = 0; j < l; j++) res[i][j] += a[i][k] * b[k][j];
			}
		}
		return res;
	}

	private static long GCD(long x, long y) {
		if (x < 0) x *= -1;
		if (y < 0) y *= -1;
		return (y > 0) ? GCD(y, x % y) : x;
	}

	private static long[][] nextLongMat(final Scanner sc, final int h, final int w) {
		final long[][] a = new long[h][w];
		for (int i = 0; i < h; i++) for (int j = 0; j < w; j++) a[i][j] = sc.nextLong();
		return a;
	}

	private static String format(final long[][] a) {
		final StringJoiner rows = new StringJoiner("\n");
		for (final long[] row : a) {
			final StringJoiner sj = new StringJoiner(" ");
			for (final long v : row) sj.add(Long.toString(v));
			rows.add(sj.toString());
		}
		return rows.toString();
	}

	private static int[] nextInts(final Scanner sc, final int n) {
		final int[] a = new int[n];
		for (int i = 0; i < n; i++) a[i] = sc.nextInt();
		return a;
	}

	private static int[][] nextIntMat(final Scanner sc, final int h, final int w) {
		final int[][] a = new int[h][];
		for (int i = 0; i < h; i++) a[i] = nextInts(sc, w);
		return a;
	}
}
