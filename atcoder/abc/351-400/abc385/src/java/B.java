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

	private static int pos;
	private static char[] s;

	private static void solve() {
		int h = sc.nextInt();
		int w = sc.nextInt();
		pos = (sc.nextInt() - 1) * w + sc.nextInt() - 1;
		s = sc.nextChars(h * w);
		int[] move = new int['Z'];
		move['U'] = -w;
		move['D'] = w;
		move['L'] = -1;
		move['R'] = 1;
		int ans = 0;
		for (char c : sc.nextChars()) {
			ans += isHouse(move[c]);
		}
		out.print(pos / w + 1, pos % w + 1, ans).println().flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static int isHouse(int d) {
		switch (s[pos + d]) {
			case '.':
				pos += d;
				return 0;
			case '@':
				pos += d;
				s[pos] = '.';
				return 1;
			default:
				return 0;
		}
	}
}
