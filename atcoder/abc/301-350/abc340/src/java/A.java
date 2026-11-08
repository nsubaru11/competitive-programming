import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int A = sc.nextInt();
		int B = sc.nextInt();
		int D = sc.nextInt();

		while (A < B) {
			System.out.println(A);
			A += D;
		}
		System.out.println(A);

	}

}
