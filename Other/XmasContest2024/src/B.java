import lib.io.*;

public class B {

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
		char[] t = sc.nextChars();
		if (t.length == 1) {
			out.println("0");
			return;
		}
		for (int i = 1; i + 2 < t.length; i += 2) {
			if (t[i] == '0' || t[i] == ')') {
				if (t[i + 2] == '0' || t[i + 2] == '(') {
					t[i + 1] = '+';
				} else if (t[i + 2] == ')' || t[i + 2] == '+') {
					t[i + 1] = ')';
				}
			} else if (t[i] == '(' || t[i] == '+') {
				if (t[i + 2] == '0' || t[i + 2] == '(') {
					t[i + 1] = '(';
				} else if (t[i + 2] == ')' || t[i + 2] == '+') {
					t[i + 1] = '0';
				}
			}
		}
		if (t.length % 2 == 1) {
			if (t[1] == '0' || t[1] == '(') {
				t[0] = '(';
			} else if (t[1] == '+') {
				t[0] = '0';
			}
			int len = t.length - 1;
			if (t[len - 1] == '0' || t[len - 1] == ')') {
				t[len] = ')';
			} else if (t[len - 1] == '+') {
				t[len] = '0';
			}
		} else {
			if (t[1] == '0' || t[1] == '(') {
				t[0] = '(';
			} else if (t[1] == '+') {
				t[0] = '0';
			}
		}
		out.printChars(t).flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
