import java.io.*;
import java.util.*;

public class C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, false);

		int n = sc.nextInt();

		long a = sc.nextInt();
		for (int i = 1; i < n; i++) {
			int b = sc.nextInt();
			a = LCM(a, b);
		}
		out.println(a);
		out.flush();
	}

	private static long LCM(long x, long y) {
		return (x == 0 || y == 0) ? 0 : x * (y / GCD(x, y));
	}

	private static long GCD(long x, long y) {
		if (x < y) {
			long swap = x;
			x = y;
			y = swap;
		}
		return (y > 0) ? GCD(y, x % y) : x;
	}
}
