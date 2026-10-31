import java.util.*;

public class A {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			// input
			int n = sc.nextInt();
			if (n == 0) {
				sc.close();
				return;
			}
			int sum = 0;
			for (int i = 0; i < n; i++) {
				int a = sc.nextInt();
				if (sum + a <= 300) {
					sum += a;
				}
			}
			System.out.println(sum);
		}
	}

}
