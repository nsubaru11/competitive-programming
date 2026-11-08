import java.util.*;

public class D2 {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int m = sc.nextInt();
		int n = sc.nextInt();
		int[][] A = nextIntMat(sc, m, n);
		int[] B = nextInts(sc, n);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			int sum = 0;
			for (int k = 0; k < n; k++) {
				sum += A[i][k] * B[k];
			}
			sb.append(sum).append("\n");
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
