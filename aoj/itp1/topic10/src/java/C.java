import java.util.*;

import static java.lang.Math.*;

public class C {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int n;
		StringBuilder sb = new StringBuilder();
		while ((n = sc.nextInt()) != 0) {
			int[] s = nextInts(sc, n);
			double average = (double) Arrays.stream(s).asLongStream().sum() / n;
			double a = 0;
			for (int x : s) {
				a += pow((double) x - average, 2);
			}
			sb.append(dtos(sqrt(a / n), 4)).append("\n");
		}
		System.out.print(sb);
	}

	private static String dtos(double x, int n) {
		StringBuilder sb = new StringBuilder();
		if (x < 0) {
			sb.append('-');
			x = -x;
		}
		x += pow(10, -n) / 2;
		sb.append((long) x).append(".");
		x -= (long) x;
		for (int i = 0; i < n; i++) {
			x *= 10;
			sb.append((int) x);
			x -= (int) x;
		}
		return sb.toString();
	}

	private static int[] nextInts(final Scanner sc, final int n) {
		final int[] a = new int[n];
		for (int i = 0; i < n; i++) a[i] = sc.nextInt();
		return a;
	}
}
