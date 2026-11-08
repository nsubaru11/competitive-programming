import java.util.*;


public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			if ((i + 1) % 3 == 0) {
				System.out.print("×");
			} else {
				System.out.print("○");
			}
		}
	}
}
