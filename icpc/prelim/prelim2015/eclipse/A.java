import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int m = Integer.parseInt(sc.next());
			if (m == 0) {
				sc.close();
				return;
			}
			int min = Integer.parseInt(sc.next());
			int max = Integer.parseInt(sc.next());
			int[] a = new int[m];
			for (int i = 0; i < m; i++) {
				a[i] = Integer.parseInt(sc.next());
			}

			// solve
			int gap = 0;
			int count = 0;
			for (int i = min - 1; i < max; i++) {
				if (gap <= a[i] - a[i + 1]) {
					gap = a[i] - a[i + 1];
					count = i + 1;
				}

			}
			System.out.println(count);
		}
	}

}
