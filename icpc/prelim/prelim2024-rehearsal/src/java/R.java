import java.util.*;

public class R {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int n = sc.nextInt();
			int[] v = new int[n];
			if (n == 0) {
				sc.close();
				return;
			}

			for (int i = 0; i < n; i++) {
				v[i] = sc.nextInt();
			}

			int count = 0;
			for (int i = 0; i < n - 2; i++) {
				if (v[i] < v[i + 1] && v[i + 1] > v[i + 2]) count++;
			}
			System.out.println(count);
		}
	}

}
