import java.util.*;

public class A2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		//配列の中で平均以下の値をカウントする
		while (true) {
			// input
			int n = Integer.parseInt(sc.next());
			if (n == 0) {
				sc.close();
				return;
			}
			int[] a = new int[n];
			int average = 0;
			for (int i = 0; i < n; i++) {
				a[i] = Integer.parseInt(sc.next());
				average += a[i];
			}

			// solve
			average /= n;
			int count = 0;
			for (int i = 0; i < n; i++) {
				if (a[i] <= average) {
					count++;
				}
			}
			System.out.println(count);
		}
	}

}
