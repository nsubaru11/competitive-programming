import java.util.*;

public class C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int ans = 0;
			if (x * y >= 0) {
				ans = Math.abs(x + y);
			} else {
				if (x * y < 0 && (x + y) * x < 0) {
					ans = Math.abs(y);
				} else {
					ans = Math.abs(x + y) + Math.abs(y);
				}
			}
			System.out.println(ans);
		}
	}

}