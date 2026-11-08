import java.io.*;
import java.util.*;

public class D {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, false);

		int n = sc.nextInt();
		out.println(EulerTotientFunction(n));
		out.flush();
	}

	private static long EulerTotientFunction(long n) {
		long sum = 1;
		for (int i = 2; i <= 3; i++) {
			long temp = 1;
			while (n % i == 0) {
				n /= i;
				temp *= i;
			}
			sum *= temp - temp / i;
		}
		for (int i = 5; i * i <= n; i += 6) {
			for (int j = i; j <= i + 2; j += 2) {
				if (n % j == 0) {
					long temp = 1;
					while (n % j == 0) {
						n /= j;
						temp *= j;
					}
					sum *= temp - temp / j;
				}
			}
		}
		return (n == 1) ? sum : sum * (n - 1);
	}
}
