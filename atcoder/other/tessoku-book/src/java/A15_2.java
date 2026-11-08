import java.util.*;

import lib.io.*;

public class A15_2 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[] a = new int[n];
		TreeSet<Integer> ts = new TreeSet<>();
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
			ts.add(a[i]);
		}

		ArrayList<Integer> al = new ArrayList<>(ts);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (i > 0) sb.append(" ");
			a[i] = Collections.binarySearch(al, a[i]) + 1;
			sb.append(a[i]);
		}
		System.out.println(sb);
	}
}
