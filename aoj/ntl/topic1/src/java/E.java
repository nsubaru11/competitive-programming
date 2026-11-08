import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.*;

public class E {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, false);

		int a = sc.nextInt();
		int b = sc.nextInt();
		AtomicLong x = new AtomicLong();
		AtomicLong y = new AtomicLong();
		exGCD(a, b, x, y);
		out.print(x.get());
		out.print(" ");
		out.println(y.get());
		out.flush();
	}

	private static long exGCD(long a, long b, AtomicLong x, AtomicLong y) {
		if (b == 0) {
			x.set(1);
			y.set(0);
			return a;
		}
		AtomicLong xx = new AtomicLong(), yy = new AtomicLong();
		long d = exGCD(b, a % b, yy, xx);
		y.set(yy.get() - (a / b) * xx.get());
		x.set(xx.get());
		return d;
	}
}
