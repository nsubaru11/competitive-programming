import java.util.*;

import lib.io.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt(), m = sc.nextInt();
		int[] goal = sc.nextInt(m);
		int cnt = 0;
		BitSet bs = new BitSet(m);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				goal[j] -= sc.nextInt();
				if (goal[j] <= 0 && !bs.get(j)) {
					bs.set(j);
					cnt++;
				}
			}
		}
		System.out.println(cnt < m ? "No" : "Yes");
	}
}
