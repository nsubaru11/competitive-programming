import lib.io.*;

import static java.util.Arrays.*;

public class C {

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
		int a = sc.nextInt();
		long n = sc.nextLong();
		int maxK = 63;
		int max10 = 13;
		char[] arrA = new char[maxK];
		char[] arr = new char[max10];
		fill(arrA, '0');
		arrA[0] = '1';
		long sum = 0;
		int cnt = 1;
		while (cnt < maxK) {
			long temp = toLong(arrA, a);
			if (temp > n) break;
			int k = toString(arr, temp, 10);
			if (isPalindrome(arr, k)) {
				sum += temp;
			}
			cnt = nextPalindrome(arrA, a, cnt);
		}
		out.println(sum);
	}

	public static int toString(char[] arr, long n, int a) {
		int k = 0;
		while (n > 0) {
			arr[k++] = (char) (n % a + '0');
			n /= a;
		}
		return k;
	}

	public static int nextPalindrome(char[] arr, int a, int b) {
		int t = b / 2;
		while (t < b && arr[t] == a - 1 + '0') {
			arr[t] = arr[b - t - 1] = '0';
			t++;
		}
		if (t != b) {
			char c = (char) (arr[t] + 1);
			arr[t] = arr[b - t - 1] = c;
		} else {
			arr[0] = arr[b] = '1';
			b++;
		}
		return b;
	}

	public static boolean isPalindrome(char[] s, int k) {
		for (int i = 0; i < k / 2; i++) {
			if (s[i] != s[k - i - 1]) {
				return false;
			}
		}
		return true;
	}

	public static long toLong(char[] arr, int a) {
		long d = 0;
		for (int i = arr.length - 1; i >= 0; i--)
			d = d * a + arr[i] - '0';
		return d;
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
