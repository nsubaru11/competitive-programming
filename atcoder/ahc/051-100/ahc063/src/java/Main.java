import static java.util.Arrays.*;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

import lib.io.*;

public final class Main {

	// region < Constants & Globals >
	private static final long startTime = System.currentTimeMillis();
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final byte[] di = new byte[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final byte[] dj = new byte[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();
	private static long[][] ZOBRIST_GRID;
	private static long[] ZOBRIST_SNAKE, ZOBRIST_HEAD;
	private static byte[] init, target;
	private static int n, nn, m;
	// endregion

	private static void solve() {
		init();
		beamSearch();
	}

	private static int beamSearch() {
		int w = 10000;
		lib.ds.priorityqueue.PriorityQueue<Ouroboros> cur = new lib.ds.priorityqueue.PriorityQueue<>(w * 4, true);
		lib.ds.priorityqueue.PriorityQueue<Ouroboros> next = new lib.ds.priorityqueue.PriorityQueue<>(w * 4, true);
		Ouroboros best = new Ouroboros(init);
		cur.add(best);
		LongHashSet visited = new LongHashSet();
		for (int turn = 0; turn < 100000; turn++) {
			long elapsed = System.currentTimeMillis() - startTime;
			if (elapsed > 1900) break;
			int curW = max(1500, (int) (w * (2000 - elapsed) / 2000));
			for (Ouroboros state : cur) {
				for (byte d = 0; d < 4; d++) {
					Ouroboros nextState = state.clone();
					long res = nextState.move(d);
					if (res == -1) continue;
					if (best.score > nextState.score) best = nextState;
					if (nextState.len == m && nextState.e == 0) continue;
					long hash = nextState.hash ^ ((long) nextState.len << 32) ^ (nextState.e * 0x9E3779B97F4A7C15L) ^ nextState.index[nextState.head];
					if (visited.add(hash)) next.add(nextState);
				}
			}
			int size = next.size();
			while (size-- > curW) next.poll();
			if (next.isEmpty()) break;
			lib.ds.priorityqueue.PriorityQueue<Ouroboros> temp = cur;
			cur = next;
			next = temp;
			next.clear();
			visited.clear();
		}
		best.print();
		return best.score;
	}

	private static void init() {
		n = sc.nextInt(); // 8 <= n <= 16
		nn = n * n;
		m = sc.nextInt(); // 3 * 256 / 4 = 192 (n^2 / 4 <= m <= 3 * n^2 / 4)
		int c = sc.nextInt(); // 3 <= c <= 7
		target = new byte[m];
		for (int i = 0; i < m; i++) target[i] = (byte) (sc.nextChar() - '0');
		init = new byte[nn];
		for (int i = 0; i < nn; i++) init[i] = (byte) (sc.nextChar() - '0');

		ThreadLocalRandom rnd = ThreadLocalRandom.current();
		ZOBRIST_GRID = new long[c + 1][nn];
		ZOBRIST_SNAKE = new long[nn];
		ZOBRIST_HEAD = new long[nn];

		for (int i = 0; i < nn; i++) {
			ZOBRIST_SNAKE[i] = rnd.nextLong();
			ZOBRIST_HEAD[i] = rnd.nextLong();
		}
		for (int i = 0; i <= c; i++) setAll(ZOBRIST_GRID[i], _ -> rnd.nextLong());
	}

	// region < Utility Methods >
	private static boolean isValidRange(final int i, final int from, final int to) {
		return ((i - from) | (to - 1 - i)) >= 0;
	}

	private static boolean isValidRange(final int i, final int j, final int h, final int w) {
		return ((i | j | (h - 1 - i) | (w - 1 - j)) >>> 31) == 0;
	}

	private static void swap(final char[] a, final int i, final int j) {
		final char tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private static void swap(final int[] a, final int i, final int j) {
		final int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private static void swap(final long[] a, final int i, final int j) {
		final long tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	private static boolean chmin(final char[] a, final int i, final char v) {
		if (a[i] <= v) return false;
		a[i] = v;
		return true;
	}

	private static boolean chmin(final int[] a, final int i, final int v) {
		if (a[i] <= v) return false;
		a[i] = v;
		return true;
	}

	private static boolean chmin(final long[] a, final int i, final long v) {
		if (a[i] <= v) return false;
		a[i] = v;
		return true;
	}

	private static boolean chmax(final char[] a, final int i, final char v) {
		if (a[i] >= v) return false;
		a[i] = v;
		return true;
	}

	private static boolean chmax(final int[] a, final int i, final int v) {
		if (a[i] >= v) return false;
		a[i] = v;
		return true;
	}

	private static boolean chmax(final long[] a, final int i, final long v) {
		if (a[i] >= v) return false;
		a[i] = v;
		return true;
	}

	private static int min(final int a, final int b) {
		return Math.min(a, b);
	}

	private static int min(final int a, final int b, final int c) {
		return Math.min(a, Math.min(b, c));
	}

	private static int min(int... a) {
		int len = a.length;
		int min = a[0];
		for (int i = 1; i < len; i++) if (min > a[i]) min = a[i];
		return min;
	}

	private static int max(final int a, final int b) {
		return Math.max(a, b);
	}

	private static int max(final int a, final int b, final int c) {
		return Math.max(a, Math.max(b, c));
	}

	private static int max(int... a) {
		int len = a.length;
		int max = a[0];
		for (int i = 1; i < len; i++) if (max < a[i]) max = a[i];
		return max;
	}

	private static long min(final long a, final long b) {
		return Math.min(a, b);
	}

	private static long min(final long a, final long b, final long c) {
		return Math.min(a, Math.min(b, c));
	}

	private static long min(long... a) {
		int len = a.length;
		long min = a[0];
		for (int i = 1; i < len; i++) if (min > a[i]) min = a[i];
		return min;
	}

	private static long max(final long a, final long b) {
		return Math.max(a, b);
	}

	private static long max(final long a, final long b, final long c) {
		return Math.max(a, Math.max(b, c));
	}

	private static long max(long... a) {
		int len = a.length;
		long max = a[0];
		for (int i = 1; i < len; i++) if (max < a[i]) max = a[i];
		return max;
	}

	private static double min(final double a, final double b) {
		return Math.min(a, b);
	}

	private static double min(final double a, final double b, final double c) {
		return Math.min(a, Math.min(b, c));
	}

	private static double min(double... a) {
		int len = a.length;
		double min = a[0];
		for (int i = 1; i < len; i++) if (min > a[i]) min = a[i];
		return min;
	}

	private static double max(final double a, final double b) {
		return Math.max(a, b);
	}

	private static double max(final double a, final double b, final double c) {
		return Math.max(a, Math.max(b, c));
	}

	private static double max(double... a) {
		int len = a.length;
		double max = a[0];
		for (int i = 1; i < len; i++) if (max < a[i]) max = a[i];
		return max;
	}

	private static int diff(final int a, final int b) {
		return a > b ? a - b : b - a;
	}

	private static long diff(final long a, final long b) {
		return a > b ? a - b : b - a;
	}

	private static double diff(final double a, final double b) {
		return a > b ? a - b : b - a;
	}

	private static void fill2D(final char[][] a, final char v) {
		for (final char[] ai : a) fill(ai, v);
	}

	private static void fill2D(final int[][] a, final int v) {
		for (final int[] ai : a) fill(ai, v);
	}

	private static void fill2D(final long[][] a, final long v) {
		for (final long[] ai : a) fill(ai, v);
	}

	private static void fill2D(final double[][] a, final double v) {
		for (final double[] ai : a) fill(ai, v);
	}

	private static void fill3D(final char[][][] a, final char v) {
		for (final char[][] ai : a) fill2D(ai, v);
	}

	private static void fill3D(final int[][][] a, final int v) {
		for (final int[][] ai : a) fill2D(ai, v);
	}

	private static void fill3D(final long[][][] a, final long v) {
		for (final long[][] ai : a) fill2D(ai, v);
	}

	private static void fill3D(final double[][][] a, final double v) {
		for (final double[][] ai : a) fill2D(ai, v);
	}
	// endregion

	// region < main & debug >
	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static void debugln(final Object... args) {
		if (DEBUG) {
			out.flush();
			if (args == null) System.err.println("null");
			else if (args.getClass().getComponentType().isArray()) System.err.println(stringify(args));
			else System.err.println(stream(args).map(Main::stringify).collect(Collectors.joining("\n", "\n", "")));
		}
	}

	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			if (args == null) System.err.println("null");
			else if (args.getClass().getComponentType().isArray()) System.err.println(stringify(args));
			else System.err.println(stream(args).map(Main::stringify).collect(Collectors.joining(", ", "", "")));
		}
	}

	private static String stringify(final Object obj) {
		return switch (obj) {
			case null -> "null";
			case int[][] arr -> "\n" + stream(arr).map(Arrays::toString).collect(Collectors.joining("\n"));
			case long[][] arr -> "\n" + stream(arr).map(Arrays::toString).collect(Collectors.joining("\n"));
			case char[][] arr -> "\n" + stream(arr).map(String::valueOf).collect(Collectors.joining("\n"));
			case Object[][] arr -> "\n" + stream(arr).map(Arrays::deepToString).collect(Collectors.joining("\n"));
			case int[] arr -> Arrays.toString(arr);
			case long[] arr -> Arrays.toString(arr);
			case double[] arr -> Arrays.toString(arr);
			case char[] arr -> Arrays.toString(arr);
			case boolean[] arr -> Arrays.toString(arr);
			case Object[] arr -> deepToString(arr);
			case Iterable<?> it -> {
				final StringJoiner sj = new StringJoiner(", ", "[", "]");
				for (final Object e : it) sj.add(stringify(e));
				yield sj.toString();
			}
			default -> obj.toString();
		};
	}
	// endregion

	private static final class Ouroboros implements Comparable<Ouroboros>, Cloneable {
		private static final char[] opc = {'L', 'U', 'R', 'D'};
		private final int[] index;
		private final byte[] color, grid;
		private final boolean[] isSnake;
		private final Ouroboros prev;
		private int head, len, e, t, score;
		private char op;
		private long hash;

		public Ouroboros(final byte[] init) {
			index = new int[m];
			color = new byte[m];
			grid = new byte[nn];
			isSnake = new boolean[nn];
			System.arraycopy(init, 0, grid, 0, nn);
			head = 4;
			len = 5;
			for (int i = 0; i < 5; i++) {
				color[i] = 1;
				isSnake[index[i] = i * n] = true;
			}
			score = 10000 * (2 * (m - len));
			prev = null;
			long h = 0;
			h ^= ZOBRIST_HEAD[index[head]];
			for (int i = 0; i < nn; i++) {
				h ^= ZOBRIST_GRID[grid[i]][i];
				if (isSnake[i]) h ^= ZOBRIST_SNAKE[i];
			}
			this.hash = h;
		}

		private Ouroboros(Ouroboros prev) {
			index = new int[m];
			color = new byte[m];
			grid = new byte[nn];
			isSnake = new boolean[nn];
			this.prev = prev;
		}

		public Ouroboros clone() {
			Ouroboros o = new Ouroboros(this);
			System.arraycopy(index, 0, o.index, 0, m);
			System.arraycopy(color, 0, o.color, 0, len);
			System.arraycopy(grid, 0, o.grid, 0, nn);
			System.arraycopy(isSnake, 0, o.isSnake, 0, nn);
			o.e = e;
			o.t = t;
			o.head = head;
			o.len = len;
			o.score = score;
			o.hash = hash;
			return o;
		}

		public int move(final byte d) {
			int old = index[head];
			int ni = old / n + di[d], nj = old % n + dj[d];
			int idx = ni * n + nj;
			if (!isValidRange(ni, nj, n, n) || index[(head - 1 + m) % m] == idx) return -1;
			hash ^= ZOBRIST_HEAD[old];
			hash ^= ZOBRIST_HEAD[idx];
			op = opc[d];
			t++;
			head = (head + 1) % m;
			if (grid[idx] != 0) {
				byte eat = grid[idx];
				hash ^= ZOBRIST_GRID[eat][idx];
				hash ^= ZOBRIST_GRID[0][idx];
				if (target[len] != (color[len++] = eat)) e++;
				grid[idx] = 0;
			} else {
				int oldTail = index[(head - len + m) % m];
				isSnake[oldTail] = false;
				hash ^= ZOBRIST_SNAKE[oldTail];
				if (isSnake[idx]) {
					int tail = (head - len + 1 + m) % m;
					while (index[tail] != idx) {
						int i = index[tail];
						byte ci = color[--len];
						if (target[len] != (grid[i] = ci)) e--;
						isSnake[i] = false;
						hash ^= ZOBRIST_SNAKE[i];
						hash ^= ZOBRIST_GRID[0][i];
						hash ^= ZOBRIST_GRID[ci][i];
						tail = (tail + 1) % m;
					}
				}
			}
			if (!isSnake[idx]) hash ^= ZOBRIST_SNAKE[idx];
			isSnake[index[head] = idx] = true;

			score = t + 10000 * (e + 2 * (m - len));
			// evalScore = evaluate(idx);
			return score;
		}

		// private int evaluate(int idx) {
		// 	return score;
		// 	// if (len == m) return score;
		// 	// byte nextColor = target[len];
		// 	// int minDist = 100;
		// 	// int i = idx / n, j = idx % n;
		// 	// for (int ni = 0, ij = 0; ni < n; ni++) {
		// 	// 	for (int nj = 0; nj < n; nj++, ij++) {
		// 	// 		if (grid[ij] != nextColor) continue;
		// 	// 		int di = abs(i - ni) + abs(j - nj);
		// 	// 		if (di < minDist) {
		// 	// 			minDist = di;
		// 	// 			if (minDist == 1) break;
		// 	// 		}
		// 	// 	}
		// 	// }
		// 	// return minDist == 100 ? score : score + minDist * 8;
		// }

		public void print() {
			Ouroboros p = this;
			char[] ans = new char[t];
			int loop = t;
			while (p.op != 0) {
				ans[--loop] = p.op;
				p = p.prev;
			}
			out.println(ans);
		}

		public int compareTo(final Ouroboros o) {
			return Integer.compare(score, o.score);
		}
	}

	private static final class LongHashSet {
		private final int[] stamp;
		private final long[] keys;
		private final int mask;
		private int size, curStamp;

		public LongHashSet() {
			int capacity = 1 << 20;
			stamp = new int[capacity];
			keys = new long[capacity];
			mask = capacity - 1;
			curStamp = 1;
		}

		public boolean add(long key) {
			int idx = Long.hashCode(key) & mask;
			while (stamp[idx] == curStamp) {
				if (keys[idx] == key) return false;
				idx = (idx + 1) & mask;
			}
			stamp[idx] = curStamp;
			keys[idx] = key;
			size++;
			return true;
		}

		public void clear() {
			curStamp++;
			size = 0;
		}
	}
}
