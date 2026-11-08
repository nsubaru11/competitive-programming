import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import lib.io.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public final class C {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter(64);
	// endregion

	private static void solve() {
		int n = sc.nextInt();
		BaseIntIntMap hm0 = new BaseIntIntMap(n);
		BaseIntIntMap hm1 = new BaseIntIntMap(n);
		for (int i = 0; i < n; i++) {
			int v = sc.nextInt();
			if (i % 2 == 0) hm0.increment(v);
			else hm1.increment(v);
		}
		int[][] kv0 = new int[2][2];
		int[][] kv1 = new int[2][2];
		hm0.forEach((k, v) -> {
			if (kv0[0][1] < v) {
				kv0[1][0] = kv0[0][0];
				kv0[1][1] = kv0[0][1];
				kv0[0][0] = k;
				kv0[0][1] = v;
			} else if (kv0[1][1] < v) {
				kv0[1][0] = k;
				kv0[1][1] = v;
			}
		});
		hm1.forEach((k, v) -> {
			if (kv1[0][1] < v) {
				kv1[1][0] = kv1[0][0];
				kv1[1][1] = kv1[0][1];
				kv1[0][0] = k;
				kv1[0][1] = v;
			} else if (kv1[1][1] < v) {
				kv1[1][0] = k;
				kv1[1][1] = v;
			}
		});
		if (kv0[0][0] == kv1[0][0]) {
			out.println(min(n - kv0[0][1] - kv1[1][1], n - kv0[1][1] - kv1[0][1]));
		} else {
			out.println(n - kv0[0][1] - kv1[0][1]);
		}
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

	private static long lModPow(long a, long b, final long mod) {
		if (b == 0) return 1;
		long ans = 1;
		for (a %= mod; b > 1; b >>= 1) {
			if ((b & 1) == 1) ans = ans * a % mod;
			a = a * a % mod;
		}
		return ans * a % mod;
	}

	private static int iModPow(int a, int b, final int mod) {
		if (b == 0) return 1;
		int ans = 1;
		for (a %= mod; b > 1; b >>= 1) {
			if ((b & 1) == 1) ans = (int) ((long) ans * a % mod);
			a = (int) ((long) a * a % mod);
		}
		return (int) ((long) ans * a % mod);
	}

	private static long lcmLong(final long x, final long y) {
		return x == 0 || y == 0 ? 0 : x / gcdLong(x, y) * y;
	}

	private static int lcmInt(final int x, final int y) {
		return x == 0 || y == 0 ? 0 : x / gcdInt(x, y) * y;
	}

	private static long gcdLong(long a, long b) {
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

	private static int gcdInt(int a, int b) {
		a = abs(a);
		b = abs(b);
		if (a == 0) return b;
		if (b == 0) return a;
		int commonShift = Integer.numberOfTrailingZeros(a | b);
		a >>= Integer.numberOfTrailingZeros(a);
		while (b != 0) {
			b >>= Integer.numberOfTrailingZeros(b);
			if (a > b) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			b -= a;
		}
		return a << commonShift;
	}

	private static void compression(final int[] a, final int len) {
		final int[] b = copyOf(a, len);
		sort(b);
		int n = 1;
		for (int i = 1; i < len; i++) {
			if (b[i] == b[i - 1]) continue;
			b[n++] = b[i];
		}
		for (int i = 0; i < len; i++) {
			a[i] = binarySearch(b, 0, n, a[i]);
		}
	}

	private static void compression(final int[][] a, final int n, final int m) {
		final int len = n * m;
		final int[] b = new int[len];
		for (int i = 0, j = 0; i < n; i++, j += m) {
			System.arraycopy(a[i], 0, b, j, m);
		}
		sort(b);
		int nm = 1;
		for (int i = 1; i < len; i++) {
			if (b[i] == b[i - 1]) continue;
			b[nm++] = b[i];
		}
		for (int i = 0; i < n; i++) {
			int[] ai = a[i];
			for (int j = 0; j < m; j++) {
				ai[j] = binarySearch(b, 0, nm, ai[j]);
			}
		}
	}

	private static void compression(final int[][] a, final int n) {
		int len = 0;
		for (int i = 0; i < n; i++) len += a[i].length;
		final int[] b = new int[len];
		for (int i = 0, j = 0; i < n; i++) {
			int m = a[i].length;
			System.arraycopy(a[i], 0, b, j, m);
			j += m;
		}
		sort(b);
		int nm = 1;
		for (int i = 1; i < len; i++) {
			if (b[i] == b[i - 1]) continue;
			b[nm++] = b[i];
		}
		for (int i = 0; i < n; i++) {
			int[] ai = a[i];
			for (int j = 0, m = ai.length; j < m; j++) {
				ai[j] = binarySearch(b, 0, nm, ai[j]);
			}
		}
	}
	// endregion

	// region < I/O & Debug >
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
			System.err.println(stream(args).map(C::stringify).collect(Collectors.joining("\n", "\n", "")));
		}
	}

	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			System.err.println(stream(args).map(C::stringify).collect(Collectors.joining(", ", "\n", "")));
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

	@SuppressWarnings("unused")
	private static final class BaseIntIntMap {
		private int[] keys, values, stamps;
		private int stamp, size, capacity, occupied, resizeThreshold, mask;

		public BaseIntIntMap(final int initialCapacity) {
			capacity = normalizeCapacity(initialCapacity);
			size = 0;
			occupied = 0;
			stamp = 1;
			resizeThreshold = capacity - (capacity >>> 2);
			stamps = new int[capacity];
			keys = new int[capacity];
			values = new int[capacity];
			mask = capacity - 1;
		}

		private static int normalizeCapacity(final int c) {
			final long required = (long) c * 4 / 3 + 1;
			int cap = max(16, (int) required);
			if ((cap & (cap - 1)) == 0) return cap;
			cap--;
			cap |= cap >>> 1;
			cap |= cap >>> 2;
			cap |= cap >>> 4;
			cap |= cap >>> 8;
			cap |= cap >>> 16;
			return cap + 1;
		}

		public int get(final int key) {
			for (int hash = hash(key), s = stamps[hash]; s == stamp || s == -stamp; hash = (hash + 1) & mask, s = stamps[hash]) {
				if (s < 0 || keys[hash] != key) continue;
				return values[hash];
			}
			throw new NoSuchElementException();
		}

		public int getOrDefault(final int key, final int defaultValue) {
			for (int hash = hash(key), s = stamps[hash]; s == stamp || s == -stamp; hash = (hash + 1) & mask, s = stamps[hash]) {
				if (s < 0 || keys[hash] != key) continue;
				return values[hash];
			}
			return defaultValue;
		}

		public int increment(final int key) {
			return addOrDefault(key, 1, 1);
		}

		public int decrement(final int key) {
			return addOrDefault(key, -1, -1);
		}

		public int add(final int key, final int delta) {
			return addOrDefault(key, delta, delta);
		}

		public int addOrDefault(final int key, final int delta, final int defaultValue) {
			if (occupied >= resizeThreshold) resize();
			int pos = -1;
			int hash = hash(key);
			for (int s = stamps[hash]; s == stamp || s == -stamp; hash = (hash + 1) & mask, s = stamps[hash]) {
				if (s == -stamp) {
					if (pos == -1) pos = hash;
					continue;
				} else if (keys[hash] != key) continue;
				return values[hash] += delta;
			}
			if (pos == -1) {
				pos = hash;
				occupied++;
			}
			stamps[pos] = stamp;
			keys[pos] = key;
			size++;
			return values[pos] = defaultValue;
		}

		public int put(final int key, final int value) {
			if (occupied >= resizeThreshold) resize();
			int pos = -1;
			int hash = hash(key);
			for (int s = stamps[hash]; s == stamp || s == -stamp; hash = (hash + 1) & mask, s = stamps[hash]) {
				if (s == -stamp) {
					if (pos == -1) pos = hash;
					continue;
				} else if (keys[hash] != key) continue;
				return values[hash] = value;
			}
			if (pos == -1) {
				pos = hash;
				occupied++;
			}
			stamps[pos] = stamp;
			keys[pos] = key;
			size++;
			return values[pos] = value;
		}

		public boolean remove(final int key) {
			for (int hash = hash(key), s = stamps[hash]; s == stamp || s == -stamp; hash = (hash + 1) & mask, s = stamps[hash]) {
				if (s < 0 || keys[hash] != key) continue;
				stamps[hash] = -stamp;
				size--;
				return true;
			}
			return false;
		}

		public boolean containsKey(final int key) {
			for (int hash = hash(key), s = stamps[hash]; s == stamp || s == -stamp; hash = (hash + 1) & mask, s = stamps[hash]) {
				if (s < 0 || keys[hash] != key) continue;
				return true;
			}
			return false;
		}

		public int merge(final int key, final int value, final IntBinaryOperator op) {
			if (occupied >= resizeThreshold) resize();
			int pos = -1;
			int hash = hash(key);
			for (int s = stamps[hash]; s == stamp || s == -stamp; hash = (hash + 1) & mask, s = stamps[hash]) {
				if (s == -stamp) {
					if (pos == -1) pos = hash;
					continue;
				} else if (keys[hash] != key) continue;
				return values[hash] = op.applyAsInt(values[hash], value);
			}
			if (pos == -1) {
				pos = hash;
				occupied++;
			}
			stamps[pos] = stamp;
			keys[pos] = key;
			size++;
			return values[pos] = value;
		}

		public int putIfAbsent(final int key, final int value) {
			if (occupied >= resizeThreshold) resize();
			int pos = -1;
			int hash = hash(key);
			for (int s = stamps[hash]; s == stamp || s == -stamp; hash = (hash + 1) & mask, s = stamps[hash]) {
				if (s == -stamp) {
					if (pos == -1) pos = hash;
					continue;
				} else if (keys[hash] != key) continue;
				return values[hash];
			}
			if (pos == -1) {
				pos = hash;
				occupied++;
			}
			stamps[pos] = stamp;
			keys[pos] = key;
			size++;
			return values[pos] = value;
		}

		public void clear() {
			size = 0;
			occupied = 0;
			stamp++;
		}

		public int size() {
			return size;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		public void forEach(final IntIntConsumer action) {
			for (int i = 0; i < capacity; i++) {
				if (stamps[i] != stamp) continue;
				action.accept(keys[i], values[i]);
			}
		}

		public void forEachKey(final IntConsumer action) {
			for (int i = 0; i < capacity; i++) {
				if (stamps[i] != stamp) continue;
				action.accept(keys[i]);
			}
		}

		public void forEachValue(final IntConsumer action) {
			for (int i = 0; i < capacity; i++) {
				if (stamps[i] != stamp) continue;
				action.accept(values[i]);
			}
		}

		public long reduce(final long identity, final EntryToLongAccumulator accumulator) {
			long result = identity;
			for (int i = 0; i < capacity; i++) {
				if (stamps[i] != stamp) continue;
				result = accumulator.apply(result, keys[i], values[i]);
			}
			return result;
		}

		public long reduceKeys(final long identity, final LongBinaryOperator accumulator) {
			long result = identity;
			for (int i = 0; i < capacity; i++) {
				if (stamps[i] != stamp) continue;
				result = accumulator.applyAsLong(result, keys[i]);
			}
			return result;
		}

		public long reduceValues(final long identity, final LongBinaryOperator accumulator) {
			long result = identity;
			for (int i = 0; i < capacity; i++) {
				if (stamps[i] != stamp) continue;
				result = accumulator.applyAsLong(result, values[i]);
			}
			return result;
		}

		public int[] keys() {
			final int[] res = new int[size];
			for (int i = 0, idx = 0; i < capacity; i++) {
				if (stamps[i] != stamp) continue;
				res[idx++] = keys[i];
			}
			return res;
		}

		public int[] values() {
			final int[] res = new int[size];
			for (int i = 0, idx = 0; i < capacity; i++) {
				if (stamps[i] != stamp) continue;
				res[idx++] = values[i];
			}
			return res;
		}

		public int[][] entries() {
			final int[][] res = new int[2][size];
			for (int i = 0, idx = 0; i < capacity; i++) {
				if (stamps[i] != stamp) continue;
				res[0][idx] = keys[i];
				res[1][idx] = values[i];
				idx++;
			}
			return res;
		}

		private void resize() {
			final int oldCapacity = capacity;
			final int[] oldKeys = keys, oldValues = values, oldStamps = stamps;
			capacity <<= 1;
			resizeThreshold = capacity - (capacity >>> 2);
			keys = new int[capacity];
			values = new int[capacity];
			stamps = new int[capacity];
			occupied = 0;
			mask = capacity - 1;
			for (int i = 0; i < oldCapacity; i++) {
				if (oldStamps[i] != stamp) continue;
				final int key = oldKeys[i];
				int hash = hash(key);
				while (stamps[hash] == stamp) hash = (hash + 1) & mask;
				stamps[hash] = stamp;
				keys[hash] = key;
				values[hash] = oldValues[i];
				occupied++;
			}
		}

		private int hash(final int key) {
			int h = key;
			h ^= h >>> 16;
			h *= 0x7feb352d;
			h ^= h >>> 15;
			h *= 0x846ca68b;
			h ^= h >>> 16;
			return h & mask;
		}

		public interface EntryToLongAccumulator {
			long apply(long accumulator, int key, int value);
		}

		public interface IntIntConsumer {
			void accept(int key, int value);
		}
	}

	// endregion
}
