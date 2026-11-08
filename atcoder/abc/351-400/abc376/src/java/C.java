import java.util.Map.*;
import java.util.*;

import lib.io.*;

public class C {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[] a = sc.nextSortedInt(n);
		TreeMap<Integer, Integer> tm = sc.nextIntMultisetTM(n - 1);
		boolean f = false;
		int size = 0;
		for (int i = n - 1; i >= 0; i--) {
			Entry<Integer, Integer> e = tm.ceilingEntry(a[i]);
			if (e == null) {
				if (!f) {
					f = true;
					size = a[i];
				} else {
					System.out.println(-1);
					return;
				}
			} else {
				if (e.getValue() > 1) {
					tm.put(e.getKey(), e.getValue() - 1);
				} else {
					tm.remove(e.getKey());
				}
			}
		}
		System.out.println(size);
	}
}
