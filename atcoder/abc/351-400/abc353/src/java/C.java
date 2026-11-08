import java.util.*;

import lib.io.*;

public class C {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		//二分探索
		int n = sc.nextInt();
		int[] a = sc.nextSortedInt(n);
		long sum = (long) Arrays.stream(a).asLongStream().sum() * (n - 1);
		long cnt = 0;
		int mod = 100000000;
		for (int i = 0; i < n - 1; i++) {
			int low = i + 1;
			int high = n - 1;
			while (low < high) {
				int mid = (low + high) / 2;
				if (a[i] + a[mid] >= mod) high = mid - 1;
				else low = mid + 1;
			}
			if (a[i] + a[low] >= mod) cnt += n - low;
			else cnt += n - low - 1;
		}
		sum -= (long) mod * cnt;
		System.out.println(sum);
	}
}
