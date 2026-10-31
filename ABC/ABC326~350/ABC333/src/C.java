import lib.ds.set.*;
import lib.io.*;

public final class C {

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
		LongAVLSet set = new LongAVLSet();
		for (long i = 1; i <= 111111111111L; i = i * 10 + 1) {
			for (long j = 1; j <= 111111111111L; j = j * 10 + 1) {
				for (long k = 1; k <= 111111111111L; k = k * 10 + 1) {
					set.add(i + j + k);
				}
			}
		}
		out.println(set.getByIndex(n - 1));
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
