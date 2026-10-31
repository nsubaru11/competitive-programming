import java.util.*;

public class E {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int n = sc.nextInt();
			if (n == 0) {
				sc.close();
				return;
			}
			int m = sc.nextInt();
			int[] a = new int[n];
			int temp = 0;
			int count = 1;
			TreeMap<Integer, Integer> go = new TreeMap<>();
			for (int i = 0; i < n; i++) {
				a[i] = sc.nextInt();
				temp += a[i];
				if (temp > m) {
					count++;
					go.put(i - 1, temp - a[i]);
					temp = a[i];
				}
			}
			go.put(n - 1, temp);

			temp = 0;
			int flag = 0;
			for (int i: go.keySet()) {
				if (i == go.size() - 1) {
					break;
				} else if (i + 1 < n && go.higherKey(i + 1) != null && i > 0) {
					if (go.containsKey(i - 1) && go.containsKey(i + 1) && go.get(i - 1) + a[i + 1] <= m && go.get(go.higherKey(i + 1)) + a[i] <= m) {
						flag = 2;
						break;
					} else if (go.containsKey(i + 1) && go.get(i) - a[i] + a[i + 1] <= m && go.get(go.higherKey(i + 1)) + a[i] <= m) {
						flag = 1;
					} else if (go.containsKey(i - 1) &&  go.containsKey(i + 1) && (go.get(i - 1) + a[i + 1] <= m || go.get(go.higherKey(i + 1)) + a[i] <= m)) {
						flag = 1;
					} else if (go.containsKey(i - 1) && go.higherKey(i) != null && go.higherKey(go.higherKey(i)) != null && go.get(i) - a[i] + a[i + 1] <= m && go.higherKey(i) - a[i + 1] + go.higherKey(go.higherKey(i)) <= m) {
						flag = 1;
					}
				} else if (i == 0 && go.higherKey(i + 1) != null) {
					if (go.containsKey(i + 1) && go.get(go.higherKey(i + 1)) + a[i] <= m) {
						flag = 1;
					}
				}
			}
			System.out.println((flag == 2) ? count - 2: (flag == 1) ? count - 1: count);
		}
	}

}