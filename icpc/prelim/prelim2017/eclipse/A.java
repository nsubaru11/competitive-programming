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
			int m = Integer.parseInt(sc.next());
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = Integer.parseInt(sc.next());
			}

			// 2重for文で全探索を行う。
			int sum = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i != j && a[i] + a[j] <= m) {
						sum = Math.max(sum, a[i] + a[j]);
					}
				}
			}
			System.out.println((sum == 0) ? "NONE": sum);
		}
	}

}
