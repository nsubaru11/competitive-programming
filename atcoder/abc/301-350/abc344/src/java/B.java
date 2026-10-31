import java.util.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A[] = new int[100];
		int N = 1;
		
		for (int i = 0; i < 100; i++) {
			A[i] = sc.nextInt();
			if (A[i] == 0) {
				N = i;
				break;
			}
		}
		
		for(int i = 0; i < N; i++) {
			System.out.println(A[N - i]);
		}
		
		System.out.println(A[0]);
	}

}
