import java.util.*;

public class D {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int w = sc.nextInt(), h = sc.nextInt();
		int x = sc.nextInt(), y = sc.nextInt(), r = sc.nextInt();
		System.out.println((x - r >= 0 && y - r >= 0 && x + r <= w && y + r <= h) ? "Yes" : "No");
	}
}
