import lib.io.*;

public class C {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		FastPrinter out = new FastPrinter();

		int k = sc.nextInt();
		int n = (int) pow(3, k);
		String[] S = carpet(k, n);
		for (int i = 0; i < n; i++) {
			out.println(S[i]);
		}
		out.flush();
	}

	private static String[] carpet(int k, int n) {
		String[] S = new String[n];
		if (k == 0) {
			S[0] = "#";
			return S;
		}
		int j = 0;
		String[] car = carpet(k - 1, n / 3);
		for (int i = 0; i < n; i++) {
			if (i >= n / 3 && i < n / 3 * 2) {
				S[i] = car[j];
				for (int l = 0; l < car[j].length(); l++) {
					S[i] += ".";
				}
				S[i] += car[j];
			} else {
				S[i] = car[j] + car[j] + car[j];
			}
			j++;
			if (j == car[j - 1].length()) j = 0;
		}
		return S;
	}

	private static long pow(long a, long b) {
		long ans = 1L;
		while (b > 0) {
			if ((b & 1) == 1) {
				ans *= a;
			}
			a *= a;
			b >>= 1;
		}
		return ans;
	}
}
