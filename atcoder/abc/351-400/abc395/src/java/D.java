import lib.io.*;

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
		int n = sc.nextInt();
		int q = sc.nextInt();
		int[] nest = new int[n];
		int[] labelToIndex = new int[n];
		int[] indexToLabel = new int[n];
		int[] pigeon = new int[n];
		for (int i = 0; i < n; i++) {
			nest[i] = pigeon[i] = i;
		}
		while (q-- > 0) {
			int op = sc.nextInt();
			if (op == 1) {
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				pigeon[a] = nest[b];
			} else if (op == 2) {
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				int temp = nest[a];
				nest[a] = nest[b];
				nest[b] = temp;
			} else {
				int a = sc.nextInt() - 1;
				out.println(nest[pigeon[a]] + 1);
			}
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
