import lib.io.*;

import static java.lang.Math.*;

public class C {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[][] LR = sc.nextIntMat(n, 2);
		int[] x = findX(n, LR);
		if (x != null) {
			out.println("Yes").print(x).println();
		} else {
			out.println("No");
		}
		out.flush();
	}

	private static int[] findX(int n, int[][] LR) {
		long l = 0;
		long r = 0;
		for (int i = 0; i < n; i++) {
			l += LR[i][0];
			r += LR[i][1];
		}

		if (l <= 0 && 0 <= r) {
			int[] x = new int[n];
			long currentSum = l;

			for (int i = 0; i < n; i++) {
				x[i] = LR[i][0];
			}

			for (int i = 0; i < n; i++) {
				if (currentSum == 0) break;
				int L = LR[i][0];
				int R = LR[i][1];
				int maxIncrease = R - L;
				long needed = -currentSum;
				int increase = (int) min(maxIncrease, needed);
				x[i] += increase;
				currentSum += increase;
			}
			return x;
		} else {
			return null;
		}
	}
}
