import java.util.*;

public class A {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//Subaru
		while (true) {
			int a = Integer.parseInt(sc.next());
			int b = Integer.parseInt(sc.next());
			int c = Integer.parseInt(sc.next());
			int d = Integer.parseInt(sc.next());
			if (a == 0 && b == 0 && c == 0 && d == 0) {
				sc.close();
				return;
			}
			System.out.println(GCD(GCD(GCD(a, b), c), d));
		}
	}

	private static long GCD(long x, long y) {
		//最大公約数を出力
		//互いに素ならば1を返す
		//片方が0なら0出ない方を返す
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
