import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import lib.io.*;

public final class C {

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
		IntRingBuffer b = new IntRingBuffer(n);
		for (int i = 0; i < n; i++) {
			if ((i & 1) == 0) b.addFirst(sc.nextInt());
			else b.addLast(sc.nextInt());
		}
		if ((n & 1) == 0) out.printReverse(b.toArray()).println();
		else out.print(b.toArray()).println();
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

	// region < I/O & Debug >
	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
	// endregion

	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			System.err.println(deepToString(args));
		}
	}

	/**
	 * int型に特化したリングバッファ。
	 * <p>
	 * 固定長のリングバッファ（サーキュラーバッファ）を実装します。競技プログラミングでの利用を想定し、
	 * 内部容量を自動的に2のべき乗に正規化することで、剰余演算の代わりに高速なビット演算を利用します。
	 */
	@SuppressWarnings("unused")
	private static final class IntRingBuffer implements Iterable<Integer>, Cloneable {
		private final int capacity; // バッファの最大サイズ
		private int[] buf; // データを格納する配列
		private int head, size; // データの先頭を表すインデックスとデータの要素数

		/**
		 * 指定された推奨容量でIntRingBufferを構築します。
		 *
		 * @param capacity 推奨されるバッファ容量。内部で2のべき乗に正規化されます。正の値を指定してください。
		 */
		public IntRingBuffer(final int capacity) {
			this.capacity = 1 << (32 - Integer.numberOfLeadingZeros(capacity - 1));
			this.buf = new int[this.capacity];
			this.head = size = 0;
		}

		/**
		 * バッファの末尾に要素を追加します。
		 *
		 * @param e 追加する要素
		 * @return 要素が追加された場合は {@code true}、バッファが満杯で追加できなかった場合は {@code false}
		 */
		public boolean addLast(final int e) {
			if (isFull()) return false;
			buf[physicalIndex(size++)] = e;
			return true;
		}

		/**
		 * バッファの先頭に要素を追加します。
		 *
		 * @param e 追加する要素
		 * @return 要素が追加された場合は {@code true}、バッファが満杯で追加できなかった場合は {@code false}
		 */
		public boolean addFirst(final int e) {
			if (isFull()) return false;
			head = physicalIndex(capacity - 1);
			buf[head] = e;
			size++;
			return true;
		}

		/**
		 * 指定したインデックスの要素を取得します。負のインデックスは、末尾からの相対位置として解釈されます (例: -1は最後の要素)。
		 *
		 * @param index 取得する要素のインデックス。{@code -size() <= index < size()} を満たす必要があります。
		 * @return 指定されたインデックスの要素
		 * @throws RingBufferIndexException バッファが空の場合、またはインデックスが範囲外の場合
		 */
		public int get(int index) {
			if (isEmpty()) throw new RingBufferIndexException("Buffer is empty.");
			if (index >= size || index < -size) throw new RingBufferIndexException(index, -size, size - 1);
			if (index < 0) index += size;
			return buf[physicalIndex(index)];
		}

		/**
		 * 末尾の要素を取得して削除します。
		 *
		 * @return 末尾の要素
		 * @throws RingBufferIndexException バッファが空の場合
		 */
		public int pollLast() {
			if (isEmpty()) throw new RingBufferIndexException("Buffer is empty.");
			return buf[physicalIndex(--size)];
		}

		/**
		 * 先頭の要素を取得して削除します。
		 *
		 * @return 先頭の要素
		 * @throws RingBufferIndexException バッファが空の場合
		 */
		public int pollFirst() {
			if (isEmpty()) throw new RingBufferIndexException("Buffer is empty.");
			int first = buf[head];
			size--;
			head = physicalIndex(1);
			return first;
		}

		/**
		 * 末尾の要素を取得します。要素は削除されません。
		 *
		 * @return 末尾の要素
		 * @throws RingBufferIndexException バッファが空の場合
		 */
		public int peekLast() {
			if (isEmpty()) throw new RingBufferIndexException("Buffer is empty.");
			return buf[physicalIndex(size - 1)];
		}

		/**
		 * 先頭の要素を取得します。要素は削除されません。
		 *
		 * @return 先頭の要素
		 * @throws RingBufferIndexException バッファが空の場合
		 */
		public int peekFirst() {
			if (isEmpty()) throw new RingBufferIndexException("Buffer is empty.");
			return buf[head];
		}

		/**
		 * 指定されたインデックスの要素を上書きします。負のインデックスは、末尾からの相対位置として解釈されます (例: -1は最後の要素)。
		 *
		 * @param index 上書きする要素のインデックス。{@code -size() <= index < size()} を満たす必要があります。
		 * @param e     新しい要素
		 * @return このインスタンス自体
		 * @throws RingBufferIndexException バッファが空の場合、またはインデックスが範囲外の場合
		 */
		public IntRingBuffer set(int index, final int e) {
			if (isEmpty()) throw new RingBufferIndexException("Buffer is empty.");
			if (index < -size || size <= index) throw new RingBufferIndexException(index, -size, size - 1);
			if (index < 0) index += size;
			buf[physicalIndex(index)] = e;
			return this;
		}

		/**
		 * 現在のバッファの長さを変更します。
		 * <p>
		 * 長さが増加する場合、新しい位置には{@code 0}が挿入されます。
		 * 長さが減少する場合、末尾の要素が切り捨てられます。
		 *
		 * @param newLen 新しい長さ。{@code 0 <= newLen <= capacity()} を満たす必要があります。
		 * @return このインスタンス自体
		 * @throws RingBufferIndexException 指定された長さが {@code 0} 未満、または {@code capacity()} を超える場合
		 */
		public IntRingBuffer setLength(int newLen) {
			if (newLen > capacity || newLen < 0) throw new RingBufferIndexException(newLen, capacity);
			if (size < newLen) {
				while (size < newLen) addLast(0);
			} else {
				size = newLen;
			}
			return this;
		}

		/**
		 * 指定した要素がバッファに含まれているかを調べます。
		 *
		 * @param e 検索対象の要素
		 * @return 指定した要素が含まれている場合はtrue、含まれていない場合はfalse
		 */
		public boolean contains(final int e) {
			return stream().anyMatch(i -> i == e);
		}

		/**
		 * バッファが空かどうかを返します。
		 *
		 * @return 現在の要素数が0の場合はtrue、それ以外の場合はfalse
		 */
		public boolean isEmpty() {
			return size == 0;
		}

		/**
		 * バッファが満杯かどうかを返します。
		 *
		 * @return 現在の要素数がバッファの最大サイズに達している場合はtrue、それ以外の場合はfalse
		 */
		public boolean isFull() {
			return size == capacity;
		}

		/**
		 * 現在のバッファ内の要素数を返します。
		 *
		 * @return 現在の要素数
		 */
		public int size() {
			return size;
		}

		/**
		 * バッファの最大容量を返します。
		 *
		 * @return バッファの最大容量
		 */
		public int capacity() {
			return capacity;
		}

		/**
		 * バッファにこれ以上追加できる残りの容量を返します。
		 *
		 * @return {@code capacity() - size()}
		 */
		public int remainingCapacity() {
			return capacity - size;
		}

		/**
		 * 指定された関数を使用してバッファ内の全要素を初期化します。
		 *
		 * @param generator 要素を生成する関数。インデックス（0からsize-1まで）を引数として受け取り、初期化する値を返します。
		 * @return このインスタンス自体を返します。
		 */
		public IntRingBuffer setAll(final IntUnaryOperator generator) {
			head = 0;
			size = capacity;
			Arrays.setAll(buf, generator);
			return this;
		}

		/**
		 * 指定された値でバッファ全体を初期化します。
		 *
		 * @param e バッファの全ての要素を埋める値
		 * @return このインスタンス自体を返します。
		 */
		public IntRingBuffer fill(final int e) {
			head = 0;
			size = capacity;
			Arrays.fill(buf, e);
			return this;
		}

		/**
		 * バッファ内の全ての要素を削除し、空の状態にします。
		 *
		 * @return このインスタンス
		 */
		public IntRingBuffer clear() {
			head = size = 0;
			return this;
		}

		/**
		 * このRingBufferインスタンスのクローンを作成します。 クローンされたインスタンスは元のインスタンスの独立したコピーです。
		 *
		 * @return クローンされたRingBufferインスタンス
		 */
		public IntRingBuffer clone() {
			try {
				IntRingBuffer rb = (IntRingBuffer) super.clone();
				rb.buf = copyOf(this.buf, this.capacity);
				return rb;
			} catch (CloneNotSupportedException e) {
				throw new AssertionError();
			}
		}

		/**
		 * 指定されたオブジェクトがこのRingBufferと等しいかを判定します。
		 *
		 * @param o 比較対象のオブジェクト
		 * @return trueの場合、指定されたオブジェクトがRingBufferのインスタンスであり、
		 * バッファ内の要素と順序が完全に一致していることを示します。
		 */
		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof IntRingBuffer other)) return false;
			return size == other.size && IntStream.range(0, size).allMatch(i -> buf[physicalIndex(i)] == other.buf[other.physicalIndex(i)]);
		}

		/**
		 * バッファ内の要素をスペースで区切った文字列として返します。
		 *
		 * @return バッファ内の要素を表す文字列。バッファが空の場合は空文字列
		 */
		public String toString() {
			return stream().mapToObj(Integer::toString).collect(Collectors.joining(" "));
		}

		/**
		 * このRingBufferの論理状態に基づくハッシュコードを返します。 論理状態とは、バッファ内の現在有効な要素（headからsize分）の順序を指します。
		 *
		 * @return 論理状態に基づいたハッシュコード
		 */
		public int hashCode() {
			if (isEmpty()) return 0;
			int result = 1;
			for (int element : this) result = 31 * result + element;
			return result;
		}

		/**
		 * バッファの論理状態（headからsize個の要素）を含む新しいIntStreamを返します。
		 *
		 * @return 現在のバッファ内容を含むIntStream
		 */
		public IntStream stream() {
			int characteristics = Spliterator.NONNULL | Spliterator.SIZED | Spliterator.SUBSIZED;
			return StreamSupport.intStream(Spliterators.spliterator(iterator(), size, characteristics), false);
		}

		/**
		 * バッファの論理状態（headからsize個の要素）を含む新しい並列IntStreamを返します。
		 *
		 * @return 現在のバッファ内容を含む並列IntStream
		 */
		public IntStream parallelStream() {
			return stream().parallel();
		}

		/**
		 * バッファの論理状態（headからsize個の要素）を含む新しいint配列を返します。
		 *
		 * @return 現在のバッファ内容を含む配列
		 */
		public int[] toArray() {
			return stream().toArray();
		}

		/**
		 * 指定された配列にバッファの論理状態をコピーして返します。 渡された配列のサイズが足りない場合は、新しい配列が生成されます。
		 *
		 * @param data コピー先の配列
		 * @return バッファ内容がコピーされた配列
		 */
		public int[] toArray(final int[] data) {
			if (data.length < size) return toArray();
			for (int i = 0; i < size; i++) data[i] = buf[physicalIndex(i)];
			return data;
		}

		/**
		 * バッファの論理状態（headからsize個の要素）を含むリストを返します。
		 *
		 * @return バッファ内の要素のリスト
		 */
		public List<Integer> toList() {
			return stream().boxed().toList();
		}

		/**
		 * このRingBufferの論理状態（headからsize個の要素）を走査するIteratorを返します。
		 *
		 * @return バッファ内の要素を順に返すIterator
		 */
		public PrimitiveIterator.OfInt iterator() {
			return new PrimitiveIterator.OfInt() {
				private int index = 0;

				public boolean hasNext() {
					return index < size;
				}

				public int nextInt() {
					if (!hasNext()) throw new NoSuchElementException();
					return buf[physicalIndex(index++)];
				}
			};
		}

		/**
		 * 論理インデックスから実インデックスを取得します。
		 *
		 * @param logicalIndex 論理インデックス
		 * @return 実インデックス
		 */
		private int physicalIndex(int logicalIndex) {
			return (head + logicalIndex) & (capacity - 1);
		}

		/**
		 * RingBufferに関連する例外クラス。 主にインデックスや長さの不正使用時にスローされます。
		 */
		private static final class RingBufferIndexException extends RuntimeException {

			@Serial
			private static final long serialVersionUID = -7627324668888567160L;

			/**
			 * 指定されたエラーメッセージを使用して例外を初期化します。
			 *
			 * @param message エラーメッセージ
			 */
			public RingBufferIndexException(String message) {
				super(message);
			}

			/**
			 * 指定されたインデックスが有効範囲外である場合の例外を初期化します。
			 *
			 * @param index 不正なインデックス
			 * @param from  有効範囲の下限（含む）
			 * @param to    有効範囲の上限（含む）
			 */
			public RingBufferIndexException(int index, int from, int to) {
				super(String.format("Invalid index %d. Valid range is [%d, %d].", index, from, to));
			}

			/**
			 * 指定された長さが有効範囲外である場合の例外を初期化します。
			 *
			 * @param len    不正な長さ
			 * @param maxLen 許容される最大長
			 */
			public RingBufferIndexException(int len, int maxLen) {
				super(String.format("Invalid length %d. Max allowed: %d.", len, maxLen));
			}
		}
	}

	// endregion
}
