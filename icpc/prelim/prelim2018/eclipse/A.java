import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int n = Integer.parseInt(sc.next());
			if (n == 0) {
				sc.close();
				return;
			}
			Integer[] a = new Integer[n];
			int average = 0;
			for (int i = 0; i < n; i++) {
				a[i] = Integer.parseInt(sc.next());
				average += a[i];
			}

			// solve
			average /= n;
			Arrays.sort(a);
			int insertionPoint = Arrays.binarySearch(a, average, new UpperBoundComparator<>());
			System.out.println((insertionPoint < 0) ? ~insertionPoint : insertionPoint);
		}
	}

	private static class UpperBoundComparator<T extends Comparable<? super T>> implements Comparator<T> {
	    public int compare(T x, T y) {
	    	return (x.compareTo(y) > 0) ? 1 : -1;
	    }
	}

}
