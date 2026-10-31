import java.util.*;

public class A {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			int n = Integer.parseInt(sc.next());
			if (n == 0) {
				sc.close();
				return;
			}
			int min = Integer.MAX_VALUE;
			int idx = 0;
			for (int i = 0; i < n; i++) {
				int a = Integer.parseInt(sc.next());
				if (min > Math.abs(a - 2023)) {
					min = Math.abs(a - 2023);
					idx = i + 1;
				}
			}
			System.out.println(idx);
		}
	}

}