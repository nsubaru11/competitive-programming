import java.util.Map.*;
import java.util.*;

import lib.io.*;

public class D {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[][] xp = sc.nextIntMat(2, n);
		Village v = new Village();
		for (int i = 0; i < n; i++) {
			v.add(xp[0][i], xp[1][i]);
		}
		int q = sc.nextInt();
		for (int i = 0; i < q; i++) {
			int l = sc.nextInt();
			int r = sc.nextInt();
			out.println(v.getNumOfPeople(l, r));
		}
		out.flush();
	}

	private static class Village {
		private static TreeMap<Integer, Long> tm;

		Village() {
			tm = new TreeMap<>();
		}

		void add(int x, long p) {
			Entry<Integer, Long> el = tm.lowerEntry(x);
			if (el != null) {
				tm.put(x, p + (long) el.getValue());
			} else {
				tm.put(x, p);
			}
		}

		long getNumOfPeople(int l, int r) {
			Integer fl = tm.floorKey(r);
			if (fl == null) {
				return 0;
			} else {
				long high = tm.get(fl);
				Integer lw = tm.lowerKey(l);
				if (lw == null) {
					return high;
				} else {
					return high - tm.get(lw);
				}
			}
		}
	}
}
