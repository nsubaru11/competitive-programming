import java.util.*;

import lib.graph.*;
import lib.io.*;

public class D {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int k = sc.nextInt();
		UndirectedGraph g = new UndirectedGraph(n, n - 1);
		for (int i = 1; i < n; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			g.add(a, b, 1);
		}
		BitSet visit = new BitSet(n);
		int cnt = 0;
		int j = sc.nextInt() - 1;
		int root = j;
		int[] path = Dijkstra.solve(g, root).parent;
		for (int i = 0; i < k; i++) {
			j = (i != 0) ? sc.nextInt() - 1 : j;
			if (!visit.get(j)) {
				visit.set(j);
				cnt++;
			} else {
				continue;
			}
			while (j != root) {
				j = path[j];
				if (!visit.get(j)) {
					visit.set(j);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}
}
