import java.util.*;

import lib.io.*;

public final class E {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();
	// endregion

	private static void solve() {
		int n = sc.nextInt();
		int q = sc.nextInt();
		int[] a = new int[n];
		TreeMap<Integer, Boolean> lr = new TreeMap<>();
		TreeMap<Integer, Integer> tm = new TreeMap<>();
		for (int i = 0; i < n; i++) {
			int k = sc.nextInt();
			a[i] = k;
			int cnt = tm.getOrDefault(k, 0) + 1;
			tm.put(k, cnt);
			if (cnt == 1 && outRange(lr, k)) set(lr, k);
		}
		while (q-- > 0) {
			int i = sc.nextInt() - 1;
			int x = sc.nextInt();
			int cnt = tm.get(a[i]);
			if (cnt == 1) {
				Boolean f = lr.get(a[i]);
				if (f == null) { // 範囲内の時
					lr.putIfAbsent(a[i] - 1, false);
					lr.put(a[i] + 1, true);
				} else if (f) { // 左端または一点の時
					Map.Entry<Integer, Boolean> e = lr.higherEntry(a[i]);
					if (e != null && !e.getValue()) {
						lr.put(a[i] + 1, true);
					}
					lr.remove(a[i]);
				} else { // 右端の時
					lr.putIfAbsent(a[i] - 1, false);
					lr.remove(a[i]);
				}
				tm.remove(a[i]);
			} else {
				tm.put(a[i], cnt - 1);
			}
			a[i] = x;

			int cnt2 = tm.getOrDefault(x, 0) + 1;
			tm.put(x, cnt2);
			if (cnt2 == 1 && outRange(lr, x)) set(lr, x);

			out.println(mex(lr));
		}
	}

	private static void set(TreeMap<Integer, Boolean> lr, int x) {
		Boolean f = lr.get(x - 1);
		Boolean f2 = lr.get(x + 1);
		if (f != null && f2 != null) {
			Map.Entry<Integer, Boolean> e = lr.higherEntry(x + 1);
			if (e != null && !e.getValue()) {
				lr.remove(x + 1);
			} else {
				lr.put(x + 1, false);
			}
			if (!f) lr.remove(x - 1);
		} else if (f != null) {
			lr.put(x, false);
			if (!f) lr.remove(x - 1);
		} else if (f2 != null) {
			lr.put(x, true);
			Map.Entry<Integer, Boolean> e = lr.higherEntry(x + 1);
			if (e != null && !e.getValue()) {
				lr.remove(x + 1);
			} else {
				lr.put(x + 1, false);
			}
		} else {
			lr.put(x, true);
		}
	}

	private static int mex(TreeMap<Integer, Boolean> lr) {
		int k = lr.firstKey();
		if (k > 0) return 0;
		Map.Entry<Integer, Boolean> e = lr.higherEntry(k);
		if (e == null) return k + 1;
		if (!e.getValue()) return e.getKey() + 1;
		return k + 1;
	}

	private static boolean outRange(TreeMap<Integer, Boolean> map, int k) {
		boolean floor = map.floorKey(k) != null ? map.floorEntry(k).getValue() : false;
		boolean ceiling = map.ceilingKey(k) != null ? map.ceilingEntry(k).getValue() : true;
		return !floor || ceiling;
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
