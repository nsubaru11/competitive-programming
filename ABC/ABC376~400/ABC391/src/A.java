import lib.io.*;

public class A {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter(true);
	// endregion

	private static void solve() {
		String s = sc.next();
		switch (s) {
			case "N":
				out.println("S");
				break;
			case "E":
				out.println("W");
				break;
			case "S":
				out.println("N");
				break;
			case "W":
				out.println("E");
				break;
			case "NE":
				out.println("SW");
				break;
			case "SE":
				out.println("NW");
				break;
			case "SW":
				out.println("NE");
				break;
			case "NW":
				out.println("SE");
				break;
		}
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
