import java.util.*;

import lib.io.*;

import static java.lang.Math.*;

public class D {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt(), k = sc.nextInt();
		int[] a = new int[n];
		TreeSet<Integer> p = new TreeSet<>();
		for (int i = 0; i < n; i++) {
			int num = sc.nextInt() - 1;
			a[num] = i;
			if (num < k) p.add(i);
		}
		int min = p.last() - p.first();
		for (int i = k; i < n; i++) {
			p.remove(a[i - k]);
			p.add(a[i]);
			min = min(min, p.last() - p.first());
		}
		System.out.println(min);
	}
}
