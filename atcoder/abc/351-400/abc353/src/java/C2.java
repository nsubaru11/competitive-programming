import java.util.*;

import lib.io.*;

import static java.util.Arrays.*;

public class C2 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		Integer[] a = stream(sc.nextSortedInt(n)).boxed().toArray(Integer[]::new);
		long sum = stream(a).mapToLong(Integer::longValue).sum() * (n - 1), cnt = 0;
		int mod = 100000000;
		Comparator<Integer> lbc = new LowerBoundComparator<>();
		for (int i = 0; i < n - 1; i++) {
			int j = binarySearch(a, i + 1, n, mod - a[i], lbc);
			j = j == -1 ? n : j < 0 ? ~j : j;
			cnt += n - j;
		}
		sum -= (long) cnt * mod;
		System.out.println(sum);
	}

	private static class LowerBoundComparator<T extends Comparable<? super T>> implements Comparator<T> {
		public int compare(T x, T y) {
			return (x.compareTo(y) >= 0) ? 1 : -1;
		}
	}
}
