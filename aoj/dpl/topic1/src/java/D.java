import java.util.*;

public class D {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = Integer.parseInt(sc.next());
		int[] a = new int[n];
		int max = 0;
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(sc.next());
			max = Math.max(max, a[i]);
		}
		Integer[] dp = new Integer[n + 1];
		Arrays.fill(dp, Integer.MIN_VALUE);
		int len = 0;
		for (int i = 0; i < n; i++) {
			int idx = Arrays.binarySearch(dp, a[i], new LowerBoundComparator<>());
			idx = (idx < 0) ? ~idx: idx;
			if (idx != n + 1) {
				 dp[idx] = a[i];
				 len = Math.max(len, idx);
			}
		}
		System.out.println(len);
		sc.close();
	}
	
	private static class LowerBoundComparator<T extends Comparable<? super T>> implements Comparator<T> {
		//二分探索をして、目的値の先頭の方のidxを探すComparator。
		public int compare(T x, T y) {
			return (x.compareTo(y) >= 0) ? 1 : -1;
		}
	}
	
}
