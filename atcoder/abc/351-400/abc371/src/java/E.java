import java.util.*;

import lib.io.*;

public class E {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[] a = new int[n];
		int[] b = new int[n];
		HashMap<Integer, Integer> hm = new HashMap<>();
		int sum = 0;
		for (int i = 0; i < n; i++) {
			int c = sc.nextInt();
			hm.put(c, hm.getOrDefault(c, 0) + 1);
			a[i] = hm.size();
			b[i] = c;
			sum += a[i];
		}
		int ans = 0;
		for (int i = 0; i < n; i++) {
			if (i > 0 && hm.getOrDefault(a[i - 1], 0) == 0) {
				ans += sum - n + i;
				sum -= n - i;
				hm.remove(a[i - 1]);
			} else {
				ans += sum;
				hm.put(a[i], hm.get(a[i]) - 1);
			}
		}
		System.out.println(ans);
	}
}
