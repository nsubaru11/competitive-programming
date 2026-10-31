import java.util.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = Integer.parseInt(sc.next());
		long[] a = new long[n];
		for (int i = 0; i < n; i++) {
			a[i] = Long.parseLong(sc.next());
		}
		for (int i = 0; i < n - 1; i++) {
			long s = Long.parseLong(sc.next());
			long t = Long.parseLong(sc.next());
			a[i + 1] += t * (a[i] / s);
		}
		System.out.println(a[n - 1]);
	}

}
