import static java.lang.Math.*;
import static java.util.Arrays.*;

import lib.ds.set.*;
import lib.io.*;

public final class D {

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

	// region initialization
	// ------------------------ 定数 ------------------------


	private static final int[] dij = new int[]{-1, -1, -1, 0, 0, 1, 1, 1, -1, 0, 1, -1, 1, -1, 0, 1};


	// endregion

	// ------------------------ メインロジック ------------------------
	private static void solve() {
		int n = sc.nextInt();
		IntAVLSet a = new IntAVLSet();
		a.add(0);
		long sum = 0;
		while (n-- > 0) {
			int b = sc.nextInt();
			Integer lower = a.lower(b);
			Integer higher = a.higher(b);
			int min = b;
			if (lower != null) {
				Integer lLower = a.lower(lower);
				Integer lHigher = a.higher(lower);
				int dl = Integer.MAX_VALUE;
				if (lLower != null) {
					dl = lower - lLower;
				}
				if (lHigher != null) {
					dl = min(dl, lHigher - lower);
				}
				int temp = b - lower;
				if (dl == Integer.MAX_VALUE) {
					sum += b - lower;
				} else if (dl > temp) {
					sum += temp - dl;
				}
				min = temp;
			}
			if (higher != null) {
				Integer hLower = a.lower(higher);
				Integer hHigher = a.higher(higher);
				int dh = Integer.MAX_VALUE;
				if (hLower != null) {
					dh = higher - hLower;
				}
				if (hHigher != null) {
					dh = min(dh, hHigher - higher);
				}
				int temp = higher - b;
				if (dh == Integer.MAX_VALUE) {
					sum += higher - b;
				} else if (dh > temp) {
					sum += temp - dh;
				}
				min = min(min, temp);
			}
			sum += min;
			out.println(sum);
			a.add(b);
		}
	}

	// region main() and debug() methods
	// ------------------------ main() 関数 ------------------------
	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	// ------------------------ デバッグ用 ------------------------
	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			System.err.println(deepToString(args));
		}
	}
}
