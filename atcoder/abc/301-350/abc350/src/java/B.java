import java.util.*;

import lib.io.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int q = sc.nextInt();
		BitSet bs = new BitSet(n);
		int cnt = n;
		for (int i = 0; i < q; i++) {
			int t = sc.nextInt() - 1;
			bs.set(t, !bs.get(t));
			if (bs.get(t))
				cnt--;
			else
				cnt++;
		}
		System.out.println(cnt);
	}
}
