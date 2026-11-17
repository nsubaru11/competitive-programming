import java.util.*;

import lib.ds.unionfind.*;
import lib.io.*;

public class D {

	private static final FastScanner sc = new FastScanner();
	//	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		HashMap<Integer, Integer> xi = new HashMap<>(n);
		HashMap<Integer, Integer> yi = new HashMap<>(n);
		UnionFind uf = new UnionFind(n);
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			uf.union(i, xi.getOrDefault(x, i));
			uf.union(i, yi.getOrDefault(y, i));
			xi.put(x, i);
			yi.put(y, i);
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>(n, (o1, o2) -> o2 - o1);
		for (List<Integer> l : uf.groups().values()) {
			pq.add(l.size());
		}
		if (pq.size() == 1) {
			System.out.println(pq.poll() + 1);
		} else {
			System.out.println(pq.poll() + pq.poll() + 1);
		}
	}
}
