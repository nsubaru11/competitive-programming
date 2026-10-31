import java.util.*;

public class A {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			int n = Integer.parseInt(sc.next());
			if (n == 0) {
				sc.close();
				return;
			}
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = sc.nextInt();
			}
			int count = 0;
			for (int i = 1; i < n - 1; i++) {
				if (a[i] > a[i - 1] && a[i] > a[i + 1]) {
					count++;
				}
			}
			
			System.out.println(count);
		}
	}

}
