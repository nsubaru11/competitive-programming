import java.util.*;

import lib.io.*;

public class No3 {

	private static final FastScanner sc = new FastScanner();
	private static HashMap<Integer, Integer> memo = new HashMap<>();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			min = Math.min(min, count(a));
		}
		System.out.println(min);
	}

	private static int count(int a) {
		if (memo.get(a) != null) return memo.get(a);
		if (a % 2 == 1) {
			memo.put(a, 0);
			return 0;
		}
		memo.put(a, 1 + count(a / 2));
		return memo.get(a);
	}
}
