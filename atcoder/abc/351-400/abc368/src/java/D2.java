import java.util.*;

import lib.graph.*;
import lib.io.*;

import static java.util.Arrays.*;

public class D2 {

	private static final FastScanner sc = new FastScanner();
	private static int[] V;

	public static void main(String[] args) {
		int n = sc.nextInt();
		int k = sc.nextInt();
		UndirectedGraph g = new UndirectedGraph(n, n - 1);
		for (int i = 1; i < n; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			g.add(a, b, 1);
		}
		V = new int[k];
		setAll(V, x -> x = sc.nextInt() - 1);
		int[] path = Dijkstra.solve(g, V[0]).parent;
		System.out.println(countVertices(path, V[0], n));
	}

	private static int countVertices(int[] path, int root, int n) {
		BitSet visit = new BitSet(n);
		int cnt = 0;
		for (int j : V) {
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
		return cnt;
	}
}
