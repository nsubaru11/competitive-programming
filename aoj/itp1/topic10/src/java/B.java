import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class B {

	private static final PrintWriter out = new PrintWriter(System.out, false);
	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		double a = sc.nextDouble();
		double b = sc.nextDouble();
		double c = sc.nextDouble() / 180 * PI;
		double S = a * b * sin(c) / 2;
		double L = sqrt(pow(a, 2) + pow(b, 2) - 2 * a * b * cos(c)) + a + b;
		double h = 2 * S / a;
		out.println(dtos(S, 4));
		out.println(dtos(L, 4));
		out.println(dtos(h, 4));
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
}
