import lib.io.*;

import static java.lang.Math.*;

public class D2 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt(), k = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[sc.nextInt() - 1] = i;
		}
		int[] min = new int[n];
		int[] max = new int[n];
		int ans = Integer.MAX_VALUE;
		int minL, minR, maxL, maxR;
		for (int i = minL = minR = maxL = maxR = 0; i < n; i++) {
			while (maxR >= maxL && a[i] >= a[max[maxR]]) {
				maxR--;
			}
			max[++maxR] = i;
			if (i - max[maxL] >= k) {
				if (maxL == maxR) {
					maxR++;
				}
				maxL++;
			}

			while (minR >= minL && a[i] <= a[min[minR]]) {
				minR--;
			}
			min[++minR] = i;
			if (i - min[minL] >= k) {
				if (minL == minR) {
					minR++;
				}
				minL++;
			}
			if (i >= k - 1) {
				ans = min(ans, a[max[maxL]] - a[min[minL]]);
			}
		}
		System.out.println(ans);
	}
}
