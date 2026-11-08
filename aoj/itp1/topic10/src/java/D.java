import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class D {

	private static final PrintWriter out = new PrintWriter(System.out, false);
	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[] x = nextInts(sc, n);
		int[] y = nextInts(sc, n);
		double p1 = 0, p2 = 0, p3 = 0, pINF = 0;
		for (int i = 0; i < n; i++) {
			double abs = abs(x[i] - y[i]);
			p1 += abs;
			p2 += pow(abs, 2);
			p3 += pow(abs, 3);
			pINF = max(pINF, abs);
		}
		out.println(p1);
		out.println(dtos(sqrt(p2), 5));
		out.println(dtos(cbrt(p3), 5));
		out.println(pINF);
		out.flush();
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
