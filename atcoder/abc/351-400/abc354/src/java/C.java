import java.util.*;

import lib.io.*;

public class C {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		HashMap<Integer, Integer> map_a = new HashMap<>();
		TreeMap<Integer, Integer> map_c = new TreeMap<>();
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			int c = sc.nextInt();
			map_a.put(a, i);
			map_c.put(c, a);
		}
		int max = 0;
		TreeSet<Integer> ans = new TreeSet<>();
		for (int x : map_c.keySet()) {
			if (max < map_c.get(x)) {
				max = map_c.get(x);
				ans.add(map_a.get(map_c.get(x)) + 1);
			}
		}
		out.println(ans.size()).print(ans.toArray()).println().flush();
	}
}
