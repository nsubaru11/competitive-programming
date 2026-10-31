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
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = Integer.parseInt(sc.next());
			}

			// solve
			Arrays.sort(a);
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < n - 1; i++) {
				min = Math.min(min, a[i + 1] - a[i]);
			}
			System.out.println(min);
		}

	}
}
