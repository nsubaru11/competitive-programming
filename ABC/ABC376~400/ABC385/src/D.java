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
		int m = sc.nextInt();
		long sx = sc.nextInt();
		long sy = sc.nextInt();
		HashMap<Long, TreeSet<Long>> xy = new HashMap<>();
		HashMap<Long, TreeSet<Long>> yx = new HashMap<>();
		while (n-- > 0) {
			long x = sc.nextInt();
			long y = sc.nextInt();
			xy.computeIfAbsent(x, k -> new TreeSet<>()).add(y);
			yx.computeIfAbsent(y, k -> new TreeSet<>()).add(x);
		}
		int ans = 0;
		while (m-- > 0) {
			char d = sc.nextChar();
			int c = sc.nextInt();
			switch (d) {
				case 'U':
					TreeSet<Long> ts = xy.getOrDefault(sx, null);
					if (ts != null) {
						Long yy = ts.ceiling(sy);
						while (yy != null && yy <= sy + c) {
							ans++;
							ts.remove(yy);
							yx.get(yy).remove(sx);
							yy = ts.higher(yy);
						}
					}
					sy += c;
					break;
				case 'D':
					ts = xy.getOrDefault(sx, null);
					if (ts != null) {
						Long yy = ts.floor(sy);
						while (yy != null && yy >= sy - c) {
							ans++;
							ts.remove(yy);
							yx.get(yy).remove(sx);
							yy = ts.lower(yy);
						}
					}
					sy -= c;
					break;
				case 'L':
					ts = yx.getOrDefault(sy, null);
					if (ts != null) {
						Long xx = ts.floor(sx);
						while (xx != null && xx >= sx - c) {
							ans++;
							ts.remove(xx);
							xy.get(xx).remove(sy);
							xx = ts.lower(xx);
						}
					}
					sx -= c;
					break;
				case 'R':
					ts = yx.getOrDefault(sy, null);
					if (ts != null) {
						Long xx = ts.ceiling(sx);
						while (xx != null && xx <= sx + c) {
							ans++;
							ts.remove(xx);
							xy.get(xx).remove(sy);
							xx = ts.higher(xx);
						}
					}
					sx += c;
					break;
			}
		}
		out.print(sx, sy, ans).println().flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
