import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		HashMap<Integer, Integer> row = new HashMap<>();
		HashMap<Integer, Integer> column = new HashMap<>();
		System.out.println(N * M);

		for (int i = 0; i < N / 2; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			System.out.println(A + " " + B);
			if (row.get(A) == null) {
				row.put(A, 1);
			} else {
				row.put(A, row.get(A) + 1);
			}
			if (column.get(B) == null) {
				column.put(B, 1);
			} else {
				column.put(B, column.get(B) + 1);
			}
		}

		for (int i = 1; i < N + 1; i++) {
			if (row.get(i) == null) {
				for (int j = 0; j < N; j++) {
					if (column.get(j) == null) {
						row.put(i, 1);
						column.put(j, 1);
					}
				}
			}
		}
	}
}
