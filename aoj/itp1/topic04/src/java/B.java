import java.util.*;

import static java.lang.Math.*;

public class B {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		Double r = sc.nextDouble();
		StringBuilder sb = new StringBuilder();
		sb.append(dtos(r * r * PI, 5)).append(" ").append(dtos(2.0 * r * PI, 5));
		System.out.println(sb);
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
