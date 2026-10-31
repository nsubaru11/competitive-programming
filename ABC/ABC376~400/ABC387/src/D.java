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

	//	private static final FastPrinter out = new FastPrinter();

	private static void solve() {
		int h = sc.nextInt();
		int w = sc.nextInt();
		char[] c = new char[h * w];
		int strt = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int idx = i * w + j;
				c[idx] = sc.nextChar();
				if (c[idx] == 'S') {
					strt = idx;
				}
			}
		}
		Deque<Tuple> dq = new ArrayDeque<>(h * w);
		dq.add(new Tuple(strt, 0, true));
		dq.add(new Tuple(strt, 0, false));
		while (!dq.isEmpty()) {
			Tuple t = dq.poll();
			if (c[t.i] == 'G') {
				System.out.println(t.cnt);
				return;
			} else if (c[t.i] == '#') {
				continue;
			} else {
				if (t.tate) { // true tate t
					if (c[t.i] == 'y') {
						c[t.i] = '#';
					} else if (c[t.i] == 't') {
						continue;
					} else {
						c[t.i] = 't';
					}
					if (t.i % w + 1 < w) {
						dq.add(new Tuple(t.i + 1, t.cnt + 1, false));
					}
					if (t.i % w - 1 >= 0) {
						dq.add(new Tuple(t.i - 1, t.cnt + 1, false));
					}
				} else {
					if (c[t.i] == 't') {
						c[t.i] = '#';
					} else if (c[t.i] == 'y') {
						continue;
					} else {
						c[t.i] = 'y';
					}
					if (t.i + w < h * w) {
						dq.add(new Tuple(t.i + w, t.cnt + 1, true));
					}
					if (t.i - w >= 0) {
						dq.add(new Tuple(t.i - w, t.cnt + 1, true));
					}
				}
			}
		}
		System.out.println(-1);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static class Tuple {
		int i, cnt;
		boolean tate; // trueなら前回縦移動、falseなら前回横移動

		Tuple(int i, int cnt, boolean flag) {
			this.i = i;
			this.cnt = cnt;
			this.tate = flag;
		}
	}
}
