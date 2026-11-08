import java.io.*;
import java.util.*;

public class D {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out, false);

	public static void main(String[] args) {
		int n = sc.nextInt();
		long low = Long.MAX_VALUE, high = Long.MIN_VALUE, sum = 0;
		for (int i = 0; i < n; i++) {
			final long v = sc.nextLong();
			low = Math.min(low, v);
			high = Math.max(high, v);
			sum += v;
		}
		out.print(low + " " + high + " " + sum);
		out.flush();
	}
}
