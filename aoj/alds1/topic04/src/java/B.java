import java.util.*;

import static java.util.Arrays.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		setAll(a, i -> sc.nextInt());
		int q = sc.nextInt();
		int cnt = 0;
		for (int i = 0; i < q; i++) {
			if (binarySearch(a, sc.nextInt()) >= 0) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
