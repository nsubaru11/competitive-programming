import java.io.*;
import java.util.*;

public class D {

	private static void solve(final Scanner sc, final PrintWriter out, int n) {
		int[][] dp = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {

			}
		}
	}

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
			 PrintWriter out = new PrintWriter(System.out)) {
			int n;
			while ((n = sc.nextInt()) != 0) {
				solve(sc, out, n);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static class Wallet {
		private int one, five, ten, fifty, oneHundread;

		public Wallet() {
			one = five = ten = fifty = oneHundread = 0;
		}

		public boolean isExistSpecifiedPrice(int m) {
			if (m % 10 < 5) {
				if (m % 10 > one) return false;
			} else {
				if (m % 10 <= one || (five >= 1 && m % 10 - 5 <= one)) {

				} else {
					return false;
				}
			}
			return true;
		}
	}
}
