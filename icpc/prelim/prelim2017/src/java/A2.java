import java.util.*;

public class A2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int n = Integer.parseInt(sc.next());
			if (n == 0) {
				sc.close();
				return;
			}
			int m = Integer.parseInt(sc.next());
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = Integer.parseInt(sc.next());
			}

			// sortしてbinarySearchを行う。
			int sum = 0;
			Arrays.sort(a);
			for (int i = 0; i < n; i++) {
				int j = Arrays.binarySearch(a, i + 1, n, m - a[i]);
				j = (j < 0) ? ~j - 1: j;
				sum = (j != n && j != i) ? Math.max(sum, a[i] + a[j]): sum;
			}
			System.out.println((sum == 0) ? "NONE": sum);
		}
	}

}
