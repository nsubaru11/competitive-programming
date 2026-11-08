import java.util.*;

public class B {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
		System.out.println((a < b && b < c) ? "Yes" : "No");
	}
}
