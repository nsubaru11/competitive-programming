import lib.io.*;

public class C {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		long m = sc.nextLong();
		int[] a = sc.nextInt(n);
		long sum = 0, low = Long.MAX_VALUE, high = Long.MIN_VALUE;
		for (int v : a) {
			sum += v;
			low = Math.min(low, v);
			high = Math.max(high, v);
		}
		if (sum <= m) {
			System.out.println("infinite");
		} else if (low * n >= m) {
			System.out.println(m / n);
		} else {
			long x = BinarySearch.normalSearch(a, m, (int) low + 1, (int) high - 1);
			System.out.println(x);
		}
	}

	private static class BinarySearch {
		private static long normalSearch(int[] a, long m, int left, int right) {
			int ans = 0;
			while (left <= right) {
				int mid = (left + right) / 2;
				if (Comparator(mid, a, m)) {
					ans = Math.max(ans, mid);
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
			return ans;
		}

		private static boolean Comparator(int mid, int[] a, long m) {
			long sum = 0;
			for (long b : a) sum += Math.min(b, mid);
			return sum <= m;
		}
	}
}
