import java.util.*;

import lib.io.*;

public class A15_1 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[] a = new int[n];
		TreeSet<Integer> ts = new TreeSet<>();
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
			ts.add(a[i]);
		}

		HashMap<Integer, Integer> hm = new HashMap<>(ts.size());
		int idx = 1;
		for (int t : ts) {
			hm.put(t, idx++);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (i > 0) sb.append(" ");
			a[i] = hm.get(a[i]);
			sb.append(a[i]);
		}
		System.out.println(sb);
	}
}
