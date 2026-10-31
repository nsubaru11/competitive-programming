import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public final class dag_memoization__problems_step5_2 {

	// region < Constants & Globals >
	private static final boolean DEBUG;
	private static final int MOD;
	private static final int[] di;
	private static final int[] dj;
	private static final Scanner sc;
	private static final PrintWriter out;

	static {
		DEBUG = true;
		// MOD = 998244353;
		MOD = 1_000_000_007;
		di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
		dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
		sc = new Scanner(System.in);
		out = new PrintWriter(System.out);
	}
	// endregion

	private static void solve() {
		int w = sc.nextInt();
		int h = sc.nextInt();
		int hw = w * h;
		int b = sc.nextInt();
		boolean[] grid = new boolean[hw];
		fill(grid, true);
		while (b-- > 0) {
			int x = sc.nextInt() - 1;
			int y = sc.nextInt() - 1;
			grid[x + y * w] = false;
		}
		int[] memo = new int[hw];
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.add(0);
		memo[0] = 1;
		boolean[] visited = new boolean[hw];
		visited[0] = true;
		while (!q.isEmpty()) {
			int u = q.poll();
			int i = u / w, j = u % w;
			for (int d = 2; d < 4; d++) {
				int ni = i + di[d], nj = j + dj[d];
				if (!isValidRange(ni, nj, h, w)) continue;
				int v = ni * w + nj;
				if (!grid[v]) continue;
				if (!visited[v]) {
					q.add(v);
					visited[v] = true;
				}
				memo[v] = (memo[v] + memo[u]) % MOD;
			}
		}
		out.println(memo[hw - 1]);
	}

	// region < Utility Methods >
	private static boolean isValidRange(final int i, final int j, final int h, final int w) {
		return 0 <= i && i < h && 0 <= j && j < w;
	}

	private static long modPow(long a, long b, final long mod) {
		long ans = 1;
		for (a %= mod; b > 0; a = a * a % mod, b >>= 1) {
			if ((b & 1) == 1) ans = ans * a % mod;
		}
		return ans;
	}

	private static long floorLong(final long a, final long b) {
		return a < 0 ? (a - b + 1) / b : a / b;
	}

	private static int floorInt(final int a, final int b) {
		return a < 0 ? (a - b + 1) / b : a / b;
	}

	private static long ceilLong(final long a, final long b) {
		return a < 0 ? a / b : (a + b - 1) / b;
	}

	private static long ceilInt(final int a, final int b) {
		return a < 0 ? a / b : (a + b - 1) / b;
	}

	private static long LCM(final long x, final long y) {
		return x == 0 || y == 0 ? 0 : x * (y / GCD(x, y));
	}

	public static long GCD(long a, long b) {
		a = abs(a);
		b = abs(b);
		if (a == 0) return b;
		if (b == 0) return a;
		int commonShift = Long.numberOfTrailingZeros(a | b);
		a >>= Long.numberOfTrailingZeros(a);
		while (b != 0) {
			b >>= Long.numberOfTrailingZeros(b);
			if (a > b) {
				long tmp = a;
				a = b;
				b = tmp;
			}
			b -= a;
		}
		return a << commonShift;
	}
	// endregion

	// region < I/O & Debug >
	public static void main(final String[] args) {
		try {
			solve();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
			out.close();
		}
	}

	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			System.err.println(deepToString(args));
		}
	}
	// endregion
}
