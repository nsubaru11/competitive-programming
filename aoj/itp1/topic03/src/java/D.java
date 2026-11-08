import java.util.*;

public class D {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
		int cnt = 0;
		for (int i = a; i <= b; i++) {
			if (c % i == 0) cnt++;
		}
		System.out.println(cnt);
	}
}
