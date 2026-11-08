import java.io.*;
import java.util.*;

import static java.util.Arrays.*;

public class D {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] a = new int[n];
		setAll(a, i -> sc.nextInt());
		long ans = BinarySearch.normalSearch(a, (int) k, Arrays.stream(a).max().getAsInt(), Arrays.stream(a).sum());
		out.println(ans);
		out.flush();
	}

	private static class BinarySearch {
		private static long normalSearch(int[] a, long m, int left, int right) {
			while (left <= right) {
				int mid = (left + right) / 2;
				if (Comparator(mid, a, m)) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
			return left;
		}

		private static boolean Comparator(int mid, int[] a, long m) {
			long sum = 0;
			for (long b : a) {
				if (sum + b <= mid) {
					sum += b;
				} else {
					m--;
					sum = b;
				}
			}
			return --m >= 0;
		}
	}
}
