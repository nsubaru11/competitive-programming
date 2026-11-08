import java.util.*;

public class A2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] a = new int[m];
		HashSet<Integer> set = new HashSet<>();
		HashSet<Integer> set2 = new HashSet<>();
		for (int i = 0; i < m; i++) {
			a[i] = sc.nextInt();
			set.add(a[i]);
			if (a[i] == n || a[i] == 1) {
				System.out.println(-1);
				return;
			}
		}
		Arrays.sort(a);

		if (a[m - 1] == n - 1) {
			for (int i = 1; i <= n; i++) {
				if (i != a[0]) {
					System.out.print(i + " ");
				}
			}
			System.out.println(a[0]);
			return;
		}

		int j = 0;
		System.out.print(1);
		set2.add(1);
		for (int i = 2; i < n + 1; i++) {
			if (!set2.contains(i)) {
				if (set.contains(i)) {
					operate(set, set2, i, n);
				} else {
					System.out.print(" " + i);
					set2.add(i);
				}
			}
		}
	}

	private static void operate(HashSet<Integer> set, HashSet<Integer> set2, int i, int n) {
		if (set2.size() + 1 == i) {
			int j = i;
			i++;
			System.out.print(" " + i);
			set2.add(i);
			if (!set.contains(i)) {
				System.out.print(" " + j);
				set2.add(j);
				return;
			} else {
				i++;
				while (set.contains(i)) {
					System.out.print(" " + i);
					set2.add(i);
					i++;
					if (i == n) {
						System.out.print(" " + j);
						set2.add(j);
						return;
					}
					if (!set.contains(i)) {
						System.out.print(" " + i);
						set2.add(i);
						System.out.print(" " + j);
						set2.add(j);
						return;
					}
				}
				System.out.print(" " + i);
				set2.add(i);
				System.out.print(" " + j);
				set2.add(j);
			}
		}
	}

}
