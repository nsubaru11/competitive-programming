import java.util.*;

public class S {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			int d = sc.nextInt();
			if (a == 0 && b == 0 && c == 0 && d == 0) {
				sc.close();
				return;
			}

			System.out.println(GCD(GCD(GCD(a, b), c), d));

		}
	}

	private static long GCD(long x, long y) {
		/* 最大公約数を求めます。
		 * 互いに素ならば 1 を、片方が 0 ならもう片方の値を返します。
		 */
		if (x == 0 || y == 0) {
			return Math.max(x, y);
		}
		try {
			if (y > x) {
				long swap = x;
				x = y;
				y = swap;
			}
			long d = x % y;
			while (x % d != 0 || y % d != 0) {
				x = y;
				y = d;
				d = x % y;
			}
			return d;
		} catch (ArithmeticException zero) {
			return y;
		}
	}

}
