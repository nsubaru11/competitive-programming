import java.util.*;

import lib.io.*;

public class D {

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
		List<ArrayDeque<Integer>> query = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			query.add(new ArrayDeque<>());
		}
		List<QueryData> list = new ArrayList<>(q);
		for (int i = 0; i < q; i++) {
			int type = sc.nextInt();
			int p = sc.nextInt() - 1;
			String s = null;
			if (type == 2) s = sc.next();
			list.add(new QueryData(type, p, s));
			query.get(p).add(i);
		}
		while (--q >= 0 && list.get(q).t != 3) ;
		List<Integer> qlist = new ArrayList<>();
		while (q >= 0) {
			QueryData data = list.get(q);
			int t = data.t;
			int p = data.p;
			boolean flag = true;
			if (t == 1) {
				while (--q >= 0 && list.get(q).t != 3) ;
				flag = q < 0;
			} else if (t == 2) {
				qlist.add(q);
				ArrayDeque<Integer> qdq = query.get(p);
				while (!qdq.isEmpty()) {
					int i = qdq.pollLast();
					if (i < q) {
						q = i;
						flag = false;
						break;
					}
				}
			} else if (t == 3) {
				ArrayDeque<Integer> qdq = query.get(p);
				while (!qdq.isEmpty()) {
					int i = qdq.pollLast();
					if (i < q) {
						q = i;
						flag = false;
						break;
					}
				}
			}
			if (flag) break;
		}
		for (int i = qlist.size() - 1; i >= 0; i--) {
			out.print(list.get(qlist.get(i)).s);
		}
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	;

	private record QueryData(int t, int p, String s) {}
}
