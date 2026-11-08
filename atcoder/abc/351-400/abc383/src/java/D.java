import java.util.*;

import lib.io.*;

import static java.lang.Math.*;

public class D {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();
	// endregion

	private static void solve() {
		long n = sc.nextLong();
		long ans = 0;
		List<Integer> primes = primes((int) round(sqrt(n)) + 1);
		for (int i = 0; i < primes.size() - 1; i++) {
			int a = primes.get(i);
			int b = primes.get(i + 1);
			for (int j = i + 2; j < primes.size() && (long) b * b <= n / ((long) a * a); j++) {
				ans++;
				b = primes.get(j);
			}
		}
		for (int i : primes) {
			if (round(pow(i, 8)) <= n) {
				ans++;
			} else {
				break;
			}
		}
		System.out.println(ans);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static List<Integer> primes(int n) {
		List<Integer> primeNum = new ArrayList<>();
		boolean[] table = new boolean[n + 1];
		primeNum.add(2);
		primeNum.add(3);
		for (int i = 4; i <= n; i += 2) table[i] = true;
		for (int i = 9; i <= n; i += 6) table[i] = true;
		for (int i = 5; i <= n; i += 6) {
			for (int j = i; j <= i + 2 && j <= n; j += 2) {
				if (!table[j]) {
					primeNum.add(j);
					for (long k = (long) j * j; k <= n; k += (long) j + j) {
						table[(int) k] = true;
					}
				}
			}
		}
		return primeNum;
	}
}
