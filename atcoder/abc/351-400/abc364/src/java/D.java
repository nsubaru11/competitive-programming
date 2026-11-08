import java.util.*;

import lib.io.*;

public class D {

	private static FastScanner sc = new FastScanner();
	private static FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int q = sc.nextInt();
		Integer[] a = Arrays.stream(sc.nextSortedInt(n)).boxed().toArray(Integer[]::new);
		for (int i = 0; i < q; i++) {
			int b = sc.nextInt();
			int k = sc.nextInt();
			int j = Arrays.binarySearch(a, b, new LowerBoundComparator<>());
			j = (j < 0) ? ~j - 1 : j - 1;
			if (j + k < n) {
				int temp = j;
				j += k;
				k = temp;
			} else {
				k = j;
				k -= n - j - 1;
				j = n - 1;
			}
		}
		out.flush();
	}

	private static class LowerBoundComparator<T extends Comparable<? super T>> implements Comparator<T> {
		public int compare(T x, T y) {
			return (x.compareTo(y) >= 0) ? 1 : -1;
		}
	}
}
