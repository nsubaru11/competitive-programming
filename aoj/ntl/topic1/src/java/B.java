import java.io.*;
import java.util.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out, false);

		int m = sc.nextInt();
		int n = sc.nextInt();
		out.println(modPow(m, n, 1000000007));
		out.flush();
	}

	private static long modPow(long a, long b, long mod) {
		long ans = 1L;
		b %= mod - 1L;
		while (b > 0) {
			if ((b & 1) == 1) {
				ans = ans * a % mod;
			}
			a = a * a % mod;
			b >>= 1;
		}
		return ans;
	}
}
