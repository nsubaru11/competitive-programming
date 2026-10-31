import java.util.Scanner;

public class A{

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
			if (i > 0) {
				System.out.print(A[i - 1] * A[i] + " ");
			}
		}
	}
}
