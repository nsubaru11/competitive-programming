import java.util.*;

import lib.io.*;

public class E {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int q = sc.nextInt();
		HashMap<Integer, TreeSet<Integer>> graph = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			graph.computeIfAbsent(i, k -> new TreeSet<>((o1, o2) -> o2 - o1)).add(i);
		}
		while (q-- > 0) {
			int t = sc.nextInt();
			if (t == 1) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				graph.get(u).add(v);
				graph.get(v).add(u);
			} else {
				int v = sc.nextInt();
				int k = sc.nextInt();
				if (graph.getOrDefault(v, new TreeSet<>()).size() < k) {
					out.println(-1);
				} else {
					out.println(graph.get(v).stream().skip(k - 1).findFirst().get());
				}
			}
		}
		out.flush();
	}
}
