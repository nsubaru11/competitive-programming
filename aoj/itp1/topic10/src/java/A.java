import java.util.*;

import static java.lang.Math.*;

public class A {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		double a = sc.nextDouble(), b = sc.nextDouble();
		double c = sc.nextDouble(), d = sc.nextDouble();
		System.out.println(dtos(sqrt(pow(a - c, 2) + pow(b - d, 2)), 4));
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
