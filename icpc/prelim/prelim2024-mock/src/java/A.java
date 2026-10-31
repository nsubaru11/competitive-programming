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

			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = sc.nextInt();
			}
			
			int max = a[0] + a[1] + a[2];
			for (int i = 3; i < n; i++) {
				max = Math.max(max, a[i] + a[i - 1] + a[i - 2]);
			}
			System.out.println(max);
		}
	}

}
