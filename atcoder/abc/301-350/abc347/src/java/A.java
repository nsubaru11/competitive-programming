import java.util.*;

public class A {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		for (int i = 0; i < N; i++) {
			int A = sc.nextInt();
			if (A % K == 0) {
				System.out.print(A / K + " ");
			}
		}
	}
}
