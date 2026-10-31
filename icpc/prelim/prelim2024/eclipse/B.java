import java.util.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			// input
			int n = Integer.parseInt(sc.next());
			if (n == 0) {
				sc.close();
				return;
			}
			int[] a = new int[n];
			int[] b = new int[n];
			
			
			for (int i = 0; i < n; i++) {
				a[i] = sc.nextInt(); 
			}
			
			for (int i = 0; i < n; i++) {
				b[i] = sc.nextInt(); 
			}
			int A = a[0];
			int B = b[0];
			int i = 0;
			while (A == B) {
				i++;
				if (i == n) break;
				A += a[i];
				B += b[i];
			}
			int count = 0;
			int j = 0;
			if (A > B) {
				j = 0;
			} else {
				j = 1;
			}
			for (i++; i < n; i++) {
				A += a[i];
				B += b[i];
				if (j == 1 && A > B) {
					count++;
					j = 0;
				} else if (j == 0 && A < B) {
					count++;
					j = 1;
				}
			}
			System.out.println(count);
			
		}
	}

}