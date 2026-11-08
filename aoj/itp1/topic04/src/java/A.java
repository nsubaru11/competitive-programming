import java.util.*;

public class A {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int a = sc.nextInt(), b = sc.nextInt();
		System.out.printf("%d %d %.5f\n", a / b, a % b, (double) a / b);
	}
}
