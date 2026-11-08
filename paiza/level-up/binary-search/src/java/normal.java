import java.io.*;
import java.util.*;

public class normal {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out, false);

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[] x = nextInts(sc, n);
		int q = sc.nextInt();
		while (q-- > 0) {
			int k = sc.nextInt();
			AbstractBinarySearch bs = new AbstractBinarySearch() {
				@Override
				protected int comparator(int n) {
					return x[n] > k ? 1 : x[n] == k ? 0 : -1;
				}
			};
			out.println(bs.normalSearch(0, n) < 0 ? "No" : "Yes");
		}
		out.flush();
	}

	private static int[] nextInts(final Scanner sc, final int n) {
		final int[] a = new int[n];
		for (int i = 0; i < n; i++) a[i] = sc.nextInt();
		return a;
	}

	@SuppressWarnings("unused")
	private static abstract class AbstractBinarySearch {

		public int normalSearch(int l, int r) {
			return binarySearch(l, r, SearchType.NORMAL);
		}

		public int upperBoundSearch(int l, int r) {
			return binarySearch(l, r, SearchType.UPPER_BOUND);
		}

		public int lowerBoundSearch(int l, int r) {
			return binarySearch(l, r, SearchType.LOWER_BOUND);
		}

		private int binarySearch(int l, int r, SearchType type) {
			Integer k = null;
			while (l <= r) {
				int m = (l + r) >> 1;
				switch (comparator(m)) {
					case 1:
						r = m - 1;
						break;
					case 0:
						switch (type) {
							case UPPER_BOUND:
								l = m + 1;
								break;
							case LOWER_BOUND:
								r = m - 1;
								break;
							case NORMAL:
								return m;
						}
						k = m;
						break;
					case -1:
						l = m + 1;
						break;
				}
			}
			return k != null ? k : ~l;
		}

		abstract protected int comparator(int n);

		private enum SearchType {
			NORMAL, UPPER_BOUND, LOWER_BOUND
		}
	}
}
