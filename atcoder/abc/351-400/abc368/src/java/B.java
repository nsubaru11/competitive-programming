import java.util.*;

import lib.io.*;

import static java.util.Arrays.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		Integer[] a = stream(sc.nextInt(n)).boxed().toArray(Integer[]::new);
		int cnt = 0;
		while (true) {
			sort(a, Collections.reverseOrder());
			a[0]--;
			a[1]--;
			cnt++;
			if (a[0] < 0 || a[1] < 0) {
				cnt--;
				break;
			}
		}
		System.out.println(cnt);
	}
}
