import lib.io.*;

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

	//	private static final FastPrinter out = new FastPrinter();

	private static void solve() {
		String L = sc.next();
		String R = sc.next();
		String l = getNextSnakeNum(L);
		String r = getNextSnakeNum(R);
		long ans = countSnakeNums(r) - countSnakeNums(l);
		System.out.println(R.compareTo(r) == 0 ? ans + 1 : ans);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static long countSnakeNums(String s) {
		long cnt = 0;
		char head = s.charAt(0);
		if (head == '1') {
			cnt = 1;
		} else {
			cnt = Long.parseLong(s.substring(1), head - '0') + 1;
		}
		for (int i = 1; i <= head - '1'; i++) {
			cnt += pow(i, s.length() - 1);
		}
		for (int i = 1; i < s.length() - 1; i++) {
			for (int j = 1; j <= 9; j++) {
				cnt += pow(j, s.length() - i - 1);
			}
		}
		return cnt;
	}

	private static String getNextSnakeNum(String s) {
		char head = s.charAt(0);
		long snakeNum = Long.parseLong(s);
		long ten = pow(10, s.length() - 1);
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) >= head) {
				if (i == 1) {
					snakeNum = (snakeNum / ten + 1) * ten;
					break;
				}
				snakeNum = (Long.parseLong(s.substring(0, i)) + 1) * ten;
				s = Long.toString(snakeNum);
				i -= 2;
				ten *= 10;
			} else {
				ten /= 10;
			}
		}
		return Long.toString(snakeNum);
	}

	public static long pow(long a, long b) {
		long ans = 1;
		for (; b > 0; a *= a, b >>= 1) {
			if ((b & 1) == 1) ans *= a;
		}
		return ans;
	}
}
