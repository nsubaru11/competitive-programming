import lib.ds.unionfind.*;
import lib.io.*;

public class D {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int m = sc.nextInt();
		UnionFind uf = new UnionFind(n);
		for (int i = 0; i < m; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			uf.union(a, b);
		}
		long cnt = -m;
		for (int i = 0; i < n; i++) {
			if (uf.find(i) == i) {
				int s = uf.size(i);
				cnt += (long) s * (s - 1) / 2;
			}
		}
		System.out.println(cnt);
	}
}
