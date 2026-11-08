import lib.io.*;
import lib.util.*;

import static java.lang.Long.*;
import static java.lang.Math.*;
import static java.lang.Math.min;
import static java.util.Arrays.*;

public class D {

	private static final FastScanner sc = new FastScanner();
	private static double min = MAX_VALUE;
	private static int s, t;

	public static void main(String[] args) {
		int n = sc.nextInt();
		s = sc.nextInt();
		t = sc.nextInt();
		int[][] abcd = sc.nextIntMat(n, 4);
		int[] perm = new int[n];
		setAll(perm, i -> i);
		int[][] order = new int[n][];
		do {
			for (int i = 0; i < n; i++) order[i] = abcd[perm[i]];
			count(order, true, 0, distance(0, 0, order[0][0], order[0][1]) / s, n);
			count(order, false, 0, distance(0, 0, order[0][2], order[0][3]) / s, n);
		} while (Permutation.next(perm));
		System.out.println(dtos(min, 7));
	}

	private static void count(int[][] abcd, boolean f, int i, double cnt, int n) {
		if (i == n - 1) {
			min = min(min, cnt + distance(abcd[i][0], abcd[i][1], abcd[i][2], abcd[i][3]) / t);
			return;
		}
		double d = distance(abcd[i][0], abcd[i][1], abcd[i][2], abcd[i][3]) / t;
		d += (f) ? distance(abcd[i][2], abcd[i][3], abcd[i + 1][0], abcd[i + 1][1]) / s : distance(abcd[i][0], abcd[i][1], abcd[i + 1][0], abcd[i + 1][1]) / s;
		count(abcd, true, i + 1, cnt + d, n);

		d = distance(abcd[i][0], abcd[i][1], abcd[i][2], abcd[i][3]) / t;
		d += (f) ? distance(abcd[i][2], abcd[i][3], abcd[i + 1][2], abcd[i + 1][3]) / s : distance(abcd[i][0], abcd[i][1], abcd[i + 1][2], abcd[i + 1][3]) / s;
		count(abcd, false, i + 1, cnt + d, n);
	}

	private static double distance(double a, double b, double c, double d) {
		return sqrt(pow(a - c, 2) + pow(b - d, 2));
	}

	private static String dtos(double x, int n) {
		StringBuilder sb = new StringBuilder();
		if (n == 0) {
			return sb.append(round(x)).toString();
		}
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
}
