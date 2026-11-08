import java.util.*;

public class D {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int n = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			if (i % 3 == 0) {
				sb.append(" ").append(i);
			} else {
				for (int x = i; x > 0; x /= 10) {
					if (x % 10 == 3) {
						sb.append(" ").append(i);
						break;
					}
				}
			}
		}
		System.out.println(sb);
	}
}
