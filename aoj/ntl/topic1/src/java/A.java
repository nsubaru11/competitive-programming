import java.io.*;
import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, false);

		int n = sc.nextInt();
		out.print(n);
		out.print(":");
		while (n % 2L == 0) {
			n /= 2L;
			out.print(" ");
			out.print(2);
		}
		while (n % 3L == 0) {
			n /= 3L;
			out.print(" ");
			out.print(3);
		}
		for (long i = 5L; i * i <= n; i += 6L) {
			while (n % i == 0) {
				n /= i;
				out.print(" ");
				out.print(i);
			}
			while (n % (i + 2L) == 0) {
				n /= (i + 2L);
				out.print(" ");
				out.print(i + 2);
			}
			if (n == 1) break;
		}
		if (n != 1) {
			out.print(" ");
			out.print(n);
		}
		out.println("");
		out.flush();
	}
}
