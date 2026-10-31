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
		int T = sc.nextInt();
		int[] L = new int[200000];
		int[] R = new int[200000];
		for (int i = 0; i < T; i++) {
			int N = sc.nextInt();
			int count = 0;
			int sum = 0;
			boolean isZero = true;
			for (int j = 0; j < N; j++) {
				char c = sc.nextChar();
				if (isZero && c == '1') {
					L[count] = j;
					isZero = false;
					if (count > 0) {
						sum += j - R[count - 1];
					}
				} else if (!isZero && c == '0') {
					R[count] = j;
					count++;
					isZero = true;
				}
			}
			if (!isZero) {
				R[count] = N;
				count++;
			}
			int min = sum;
			for (int j = 0; j < count; j++) {
				int preSum = sum;
				for (int k = count - 1; k > j; k--) {
					sum = sum - (L[k] - R[k - 1]) + R[k] - L[k];
					if (sum < min) {
						min = sum;
					}
				}
				if (j < count - 1) {
					sum = preSum + R[j] - L[j] - (L[j + 1] - R[j]);
					if (sum < min) {
						min = sum;
					}
				}
			}
			out.println(min);
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
