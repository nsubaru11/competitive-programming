import java.util.*;

public class D2 {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int m = sc.nextInt(), n = sc.nextInt(), l = sc.nextInt();
		int[][] A = nextIntMat(sc, m, n);
		int[][] B = nextIntMat(sc, n, l);
		long[][] AB = new long[m][l];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < l; j++) {
				for (int k = 0; k < n; k++) {
					AB[i][j] += (long) A[i][k] * B[k][j];
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < l; j++) {
				if (j > 0) sb.append(" ");
				sb.append(AB[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
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
