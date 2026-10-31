import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public final class s_rank_level_up_problems__dp_greedy_1 {

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
		out = new PrintWriter(System.out, false);
	}
	// endregion

	private static void solve() {
		int n = sc.nextInt();
		int[] cnt = new int[26];
		for (char c : sc.next().toCharArray()) cnt[c - 'a']++;
		FactorialTable ft = new FactorialTable(2 * n, MOD);
		int[][] dp = new int[27][n + 1];
		dp[0][0] = 1;
		for (int i = 0; i < 26; i++) {
			int c = cnt[i];
			for (int j = 0; j <= n; j++) { // 保留中の左端の個数
				if (dp[i][j] == 0) continue;
				for (int k = 0; k <= min(c, j); k++) { // 右端として選ぶ個数
					int nj = j + c - 2 * k;
					if (nj > n) continue;
					dp[i + 1][nj] = (int) ((dp[i + 1][nj] + ((((long) dp[i][j] * ft.nPr(j, k)) % MOD * ft.nCr(c, k)) % MOD)) % MOD);
				}
			}
		}
		out.println(dp[26][0]);
	}

	@SuppressWarnings("unused")
	private static final class FactorialTable {
		private final int mod;
		private final int[] fact;
		private final int[] invFact;

		public FactorialTable() {
			this(1_000_000, 998244353);
		}

		public FactorialTable(final int n) {
			this(n, 998244353);
		}

		public FactorialTable(final int n, final int mod) {
			this.mod = mod;
			fact = new int[n + 1];
			invFact = new int[n + 1];
			fact[0] = 1;
			for (int i = 1; i <= n; i++) {
				fact[i] = (int) ((long) i * fact[i - 1] % mod);
			}
			invFact[n] = modPow(fact[n], mod - 2);
			for (int i = n; i > 0; i--) {
				invFact[i - 1] = (int) ((long) invFact[i] * i % mod);
			}
		}

		private int modPow(int a, int b) {
			int ans = 1;
			for (; b > 0; a = (int) ((long) a * a % mod), b >>= 1) {
				if ((b & 1) == 1) ans = (int) ((long) ans * a % mod);
			}
			return ans;
		}

		public int fact(final int n) {
			return fact[n];
		}

		public int invFact(final int n) {
			return invFact[n];
		}

		public int nCr(final int n, final int r) {
			if (r < 0 || r > n) return 0;
			return (int) ((long) fact[n] * invFact[r] % mod * invFact[n - r] % mod);
		}

		public int nPr(final int n, final int r) {
			if (r < 0 || r > n) return 0;
			return (int) ((long) fact[n] * invFact[n - r] % mod);
		}

		/**
		 * `n` 種類のものから、重複を許して `r` 個選ぶ組み合わせ数
		 *
		 * @param n 種類数
		 * @param r 選択個数
		 * @return 重複組み合わせ
		 */
		public int nHr(final int n, final int r) {
			if (n < 0 || r < 0) return 0;
			if (n == 0 && r == 0) return 1;
			return nCr(n + r - 1, r);
		}
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
