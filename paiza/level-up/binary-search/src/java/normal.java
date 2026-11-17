import java.io.*;
import java.util.*;

public class normal {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out, false);

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[] x = nextInts(n);
		int q = sc.nextInt();
		while (q-- > 0) {
			int k = sc.nextInt();
			out.println(BinarySearch.contains(0, n, idx -> x[(int) idx] > k ? 1 : x[(int) idx] == k ? 0 : -1) ? "Yes" : "No");
		}
		out.flush();
	}

	private static int[] nextInts(final int n) {
		final int[] a = new int[n];
		for (int i = 0; i < n; i++) a[i] = normal.sc.nextInt();
		return a;
	}

	/**
	 * 比較関数による整数範囲の二分探索。範囲は半開区間 {@code [l, r)} とし、l が負でもよい。
	 * 比較関数 {@code compare(i)} は添字 i の値が目標より小さければ負、一致すれば0、大きければ正を返し、i について単調とする。
	 * contains は一致する添字の有無を返す。lowerBound は最初の一致位置、upperBound は最後の一致位置を返し、
	 * 一致する添字がなければ {@code ~(挿入位置)} を返す。l が負の範囲では、この戻り値が見つかった添字と区別できない。
	 * count は一致する添字の個数を返す。
	 */
	@SuppressWarnings("unused")
	private static final class BinarySearch {

		private BinarySearch() {}

		// region contains
		public static boolean contains(final long l, final long r, final CompareFunction comparator) {
			return binarySearchNormal(l, r - 1, comparator);
		}
		// endregion

		// region lowerBound
		public static int lowerBound(final int l, final int r, final CompareFunction comparator) {
			return (int) binarySearchLowerBound(l, r - 1, comparator);
		}

		public static long lowerBound(final long l, final long r, final CompareFunction comparator) {
			return binarySearchLowerBound(l, r - 1, comparator);
		}
		// endregion

		// region upperBound
		public static int upperBound(final int l, final int r, final CompareFunction comparator) {
			return (int) binarySearchUpperBound(l, r - 1, comparator);
		}

		public static long upperBound(final long l, final long r, final CompareFunction comparator) {
			return binarySearchUpperBound(l, r - 1, comparator);
		}
		// endregion

		// region count
		public static int count(final int l, final int r, final CompareFunction comparator) {
			final int up = upperBound(l, r, comparator);
			if (up < l || up >= r || comparator.compare(up) != 0) return 0;
			return up - lowerBound(l, r, comparator) + 1;
		}

		public static long count(final long l, final long r, final CompareFunction comparator) {
			final long up = upperBound(l, r, comparator);
			if (up < l || up >= r || comparator.compare(up) != 0) return 0;
			return up - lowerBound(l, r, comparator) + 1;
		}
		// endregion

		// region binarySearch
		private static boolean binarySearchNormal(long l, long r, final CompareFunction comparator) {
			while (l <= r) {
				final long m = (l & r) + ((l ^ r) >> 1);
				final int c = comparator.compare(m);
				if (c > 0) r = m - 1;
				else if (c < 0) l = m + 1;
				else return true;
			}
			return false;
		}

		private static long binarySearchLowerBound(long l, long r, final CompareFunction comparator) {
			boolean found = false;
			long ans = l - 1;
			while (l <= r) {
				final long m = (l & r) + ((l ^ r) >> 1);
				final int c = comparator.compare(m);
				if (c < 0) {
					l = m + 1;
				} else {
					if (c == 0) {
						ans = m;
						found = true;
					}
					r = m - 1;
				}
			}
			return found ? ans : ~l;
		}

		private static long binarySearchUpperBound(long l, long r, final CompareFunction comparator) {
			boolean found = false;
			long ans = l - 1;
			while (l <= r) {
				final long m = (l & r) + ((l ^ r) >> 1);
				final int c = comparator.compare(m);
				if (c > 0) {
					r = m - 1;
				} else {
					if (c == 0) {
						ans = m;
						found = true;
					}
					l = m + 1;
				}
			}
			return found ? ans : ~l;
		}

		// endregion

		@FunctionalInterface
		public interface CompareFunction {
			int compare(long i);
		}
	}
}
