import static java.lang.Math.*;

import java.io.*;
import java.math.*;
import java.util.*;

public class A {

	private static void solve(final FastScanner sc, final FastPrinter out) {
		int v = sc.nextInt();
		int e = sc.nextInt();
		Prim prim = new Prim(v);
		while (e-- > 0) {
			int s = sc.nextInt();
			int t = sc.nextInt();
			int w = sc.nextInt();
			prim.addEdge(s, t, w);
		}
		out.println(prim.solve());
	}

	/**
	 * Prim法を用いた全域木（MST）問題のソルバー。 最小全域木または最大全域木を求めることができます。 時間計算量: O(|E|log|V|)
	 * 空間計算量: O(|V| + |E|)
	 */
	@SuppressWarnings("unused")
	private static final class Prim {
		private final int v;
		private final boolean isMinimum;
		private final List<List<Edge>> edges;

		/**
		 * 指定された頂点数と最小/最大フラグでソルバーを初期化します。
		 *
		 * @param v         頂点数（0からv-1までの頂点番号が使用される）
		 * @param isMinimum trueの場合は最小全域木、falseの場合は最大全域木を求めます
		 */
		public Prim(int v, final boolean isMinimum) {
			this.v = v;
			this.isMinimum = isMinimum;
			this.edges = new ArrayList<>(v);
			while (v-- > 0) {
				edges.add(new ArrayList<>());
			}
		}

		/**
		 * 最小全域木を求めるソルバーを初期化します。
		 *
		 * @param v 頂点数（0からv-1までの頂点番号が使用される）
		 */
		public Prim(final int v) {
			this(v, true);
		}

		/**
		 * グラフに無向辺を追加します。
		 *
		 * @param u    辺の始点（0からv-1までの値）
		 * @param v    辺の終点（0からv-1までの値）
		 * @param cost 辺の重み
		 */
		public void addEdge(final int u, final int v, final long cost) {
			edges.get(u).add(new Edge(u, v, cost));
			edges.get(v).add(new Edge(v, u, cost));
		}

		/**
		 * Prim法を実行し、全域木の総コストを計算します。 設定に応じて最小全域木または最大全域木を求めます。 グラフが連結でない場合は-1を返します。
		 *
		 * @return 全域木の総コスト、または連結グラフでない場合は-1
		 */
		public long solve() {
			int start = 0;
			long ans = 0;
			int size = 0;
			BitSet visited = new BitSet(v);
			Comparator<Edge> comparator = isMinimum ? Comparator.comparingLong(Edge::cost)
					: Comparator.comparingLong(Edge::cost).reversed();
			PriorityQueue<Edge> heap = new PriorityQueue<>(comparator);
			visited.set(start);
			size++;
			heap.addAll(edges.get(start));
			while (!heap.isEmpty() && size < v) {
				Edge e = heap.poll();
				int v = e.v();
				if (visited.get(v))
					continue;
				visited.set(v);
				size++;
				ans += e.cost();
				for (Edge edge : edges.get(v)) {
					if (!visited.get(edge.v())) {
						heap.add(edge);
					}
				}
			}
			return size == v ? ans : -1;
		}

		/**
		 * グラフの辺を表すレコード。
		 */
		private static final class Edge {
			private final int u;
			private final int v;
			private final long cost;

			public Edge(int u, int v, long cost) {
				this.u = u;
				this.v = v;
				this.cost = cost;
			}

			public int u() {
				return u;
			}

			public int v() {
				return v;
			}

			public long cost() {
				return cost;
			}
		}
	}

	public static void main(String[] args) {
		try (final FastScanner sc = new FastScanner(); final FastPrinter out = new FastPrinter()) {
			solve(sc, out);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unused")
	private static final class FastScanner implements AutoCloseable {
		private static final int DEFAULT_BUFFER_SIZE = 65536;
		private final InputStream in;
		private final byte[] buffer;
		private int pos = 0, bufferLength = 0;

		public FastScanner() {
			this(System.in, DEFAULT_BUFFER_SIZE);
		}

		public FastScanner(final InputStream in) {
			this(in, DEFAULT_BUFFER_SIZE);
		}

		public FastScanner(final int bufferSize) {
			this(System.in, bufferSize);
		}

		public FastScanner(final InputStream in, final int bufferSize) {
			this.in = in;
			this.buffer = new byte[bufferSize];
		}

		private static boolean isWhitespace(final int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t';
		}

		@Override
		public void close() throws IOException {
			if (in != System.in)
				in.close();
		}

		private byte read() {
			if (pos >= bufferLength) {
				try {
					bufferLength = in.read(buffer, pos = 0, buffer.length);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				if (bufferLength < 0)
					throw new RuntimeException(new IOException("End of input reached"));
			}
			return buffer[pos++];
		}

		public final int nextInt() {
			int b = read();
			while (isWhitespace(b))
				b = read();
			boolean negative = b == '-';
			if (negative)
				b = read();
			int result = 0;
			while ('0' <= b && b <= '9') {
				result = result * 10 + b - '0';
				b = read();
			}
			return negative ? -result : result;
		}

		public final long nextLong() {
			int b = read();
			while (isWhitespace(b))
				b = read();
			boolean negative = b == '-';
			if (negative)
				b = read();
			long result = 0;
			while ('0' <= b && b <= '9') {
				result = result * 10 + b - '0';
				b = read();
			}
			return negative ? -result : result;
		}

		public final double nextDouble() {
			int b = read();
			while (isWhitespace(b))
				b = read();
			boolean negative = b == '-';
			if (negative)
				b = read();
			double result = 0;
			while ('0' <= b && b <= '9') {
				result = result * 10 + b - '0';
				b = read();
			}
			if (b == '.') {
				b = read();
				double factor = 10;
				while ('0' <= b && b <= '9') {
					result += (b - '0') / factor;
					factor *= 10;
					b = read();
				}
			}
			return negative ? -result : result;
		}

		public final char nextChar() {
			byte b = read();
			while (isWhitespace(b))
				b = read();
			return (char) b;
		}

		public final String next() {
			return nextStringBuilder().toString();
		}

		public final StringBuilder nextStringBuilder() {
			final StringBuilder sb = new StringBuilder();
			byte b = read();
			while (isWhitespace(b))
				b = read();
			while (!isWhitespace(b)) {
				sb.appendCodePoint(b);
				b = read();
			}
			return sb;
		}

		public final String nextLine() {
			final StringBuilder sb = new StringBuilder();
			int b = read();
			while (b != 0 && b != '\r' && b != '\n') {
				sb.appendCodePoint(b);
				b = read();
			}
			return sb.toString();
		}

		public final BigInteger nextBigInteger() {
			return new BigInteger(next());
		}

		public final BigDecimal nextBigDecimal() {
			return new BigDecimal(next());
		}

	}

	@SuppressWarnings("unused")
	private static final class FastPrinter implements AutoCloseable {
		protected static final int MAX_INT_DIGITS = 11;
		protected static final int MAX_LONG_DIGITS = 20;
		private static final int DEFAULT_BUFFER_SIZE = 65536;
		private static final byte[] TWO_DIGIT_NUMBERS = new byte[200];
		static {
			byte tens = '0', ones = '0';
			for (int i = 0; i < 100; i++) {
				TWO_DIGIT_NUMBERS[i << 1] = tens;
				TWO_DIGIT_NUMBERS[(i << 1) + 1] = ones;
				if (++ones > '9') {
					ones = '0';
					tens++;
				}
			}
		}
		protected final byte[] buffer;
		protected final boolean autoFlush;
		private final OutputStream out;
		protected int pos = 0;

		public FastPrinter() {
			this(System.out, DEFAULT_BUFFER_SIZE, false);
		}

		public FastPrinter(final OutputStream out) {
			this(out, DEFAULT_BUFFER_SIZE, false);
		}

		public FastPrinter(final int bufferSize) {
			this(System.out, bufferSize, false);
		}

		public FastPrinter(final boolean autoFlush) {
			this(System.out, DEFAULT_BUFFER_SIZE, autoFlush);
		}

		public FastPrinter(final OutputStream out, final boolean autoFlush) {
			this(out, DEFAULT_BUFFER_SIZE, autoFlush);
		}

		public FastPrinter(final int bufferSize, final boolean autoFlush) {
			this(System.out, bufferSize, autoFlush);
		}

		public FastPrinter(final OutputStream out, final int bufferSize) {
			this(out, bufferSize, false);
		}

		public FastPrinter(final OutputStream out, final int bufferSize, final boolean autoFlush) {
			this.out = out;
			buffer = new byte[max(bufferSize, 64)];
			this.autoFlush = autoFlush;
		}

		@Override
		public void close() throws IOException {
			flush();
			if (out != System.out)
				out.close();
		}

		public void flush() {
			try {
				if (pos > 0)
					out.write(buffer, 0, pos);
				out.flush();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			pos = 0;
		}

		public final void println() {
			ensureBufferSpace(1);
			buffer[pos++] = '\n';
			if (autoFlush)
				flush();
		}

		public final void println(final int i) {
			ensureBufferSpace(MAX_INT_DIGITS + 1);
			fillBuffer(i);
			buffer[pos++] = '\n';
			if (autoFlush)
				flush();
		}

		public final void println(final long l) {
			ensureBufferSpace(MAX_LONG_DIGITS + 1);
			fillBuffer(l);
			buffer[pos++] = '\n';
			if (autoFlush)
				flush();
		}

		public final void println(final double d) {
			print(Double.toString(d), true);
		}

		public final void println(final char c) {
			ensureBufferSpace(2);
			buffer[pos++] = (byte) c;
			buffer[pos++] = '\n';
			if (autoFlush)
				flush();
		}

		public final void println(final boolean b) {
			ensureBufferSpace(4);
			fillBuffer(b);
			buffer[pos++] = '\n';
			if (autoFlush)
				flush();
		}

		public final void println(final String s) {
			print(s, true);
		}

		public final void println(final BigInteger bi) {
			print(bi.toString(), true);
		}

		public final void println(final BigDecimal bd) {
			print(bd.toString(), true);
		}

		public final void print(final int i) {
			ensureBufferSpace(MAX_INT_DIGITS);
			fillBuffer(i);
			if (autoFlush)
				flush();
		}

		public final void print(final long l) {
			ensureBufferSpace(MAX_LONG_DIGITS);
			fillBuffer(l);
			if (autoFlush)
				flush();
		}

		public final void print(final double d) {
			print(Double.toString(d), false);
		}

		public final void print(final char c) {
			ensureBufferSpace(1);
			buffer[pos++] = (byte) c;
			if (autoFlush)
				flush();
		}

		public final void print(final boolean b) {
			ensureBufferSpace(3);
			fillBuffer(b);
			if (autoFlush)
				flush();
		}

		public final void print(final String s) {
			print(s, false);
		}

		public final void print(final BigInteger bi) {
			print(bi.toString(), false);
		}

		public final void print(final BigDecimal bd) {
			print(bd.toString(), false);
		}

		public final void printf(final String format, final Object... args) {
			print(String.format(format, args), false);
		}

		public final void printf(final Locale locale, final String format, final Object... args) {
			print(String.format(locale, format, args), false);
		}

		protected final void ensureBufferSpace(final int size) {
			if (pos + size > buffer.length) {
				try {
					out.write(buffer, 0, pos);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				pos = 0;
			}
		}

		protected final void print(final String s, final boolean newline) {
			fillBuffer(s);
			if (newline) {
				ensureBufferSpace(1);
				buffer[pos++] = '\n';
			}
			if (autoFlush)
				flush();
		}

		protected final void fillBuffer(final String s) {
			if (s == null)
				return;
			final int len = s.length();
			for (int i = 0; i < len;) {
				ensureBufferSpace(1);
				int limit = min(buffer.length - pos, len - i);
				while (limit-- > 0) {
					buffer[pos++] = (byte) s.charAt(i++);
				}
			}
		}

		protected final void fillBuffer(final boolean b) {
			if (b) {
				buffer[pos++] = 'Y';
				buffer[pos++] = 'e';
				buffer[pos++] = 's';
			} else {
				buffer[pos++] = 'N';
				buffer[pos++] = 'o';
			}
		}

		protected final void fillBuffer(int i) {
			if (i == Integer.MIN_VALUE) {
				buffer[pos++] = '-';
				buffer[pos++] = '2';
				buffer[pos++] = '1';
				buffer[pos++] = '4';
				buffer[pos++] = '7';
				buffer[pos++] = '4';
				buffer[pos++] = '8';
				buffer[pos++] = '3';
				buffer[pos++] = '6';
				buffer[pos++] = '4';
				buffer[pos++] = '8';
				return;
			}

			boolean negative = (i < 0);
			if (negative) {
				i = -i;
				buffer[pos++] = '-';
			}

			int numOfDigits = countDigits(i);
			int writePos = pos + numOfDigits;
			while (i >= 100) {
				int quotient = i / 100;
				int remainder = (i - quotient * 100) << 1;
				buffer[--writePos] = TWO_DIGIT_NUMBERS[remainder + 1];
				buffer[--writePos] = TWO_DIGIT_NUMBERS[remainder];
				i = quotient;
			}

			if (i < 10) {
				buffer[--writePos] = (byte) ('0' + i);
			} else {
				buffer[--writePos] = TWO_DIGIT_NUMBERS[(i << 1) + 1];
				buffer[--writePos] = TWO_DIGIT_NUMBERS[i << 1];
			}

			pos += numOfDigits;
		}

		protected final void fillBuffer(long l) {
			if ((int) l == l) {
				fillBuffer((int) l);
				return;
			}
			if (l == Long.MIN_VALUE) {
				buffer[pos++] = '-';
				buffer[pos++] = '9';
				buffer[pos++] = '2';
				buffer[pos++] = '2';
				buffer[pos++] = '3';
				buffer[pos++] = '3';
				buffer[pos++] = '7';
				buffer[pos++] = '2';
				buffer[pos++] = '0';
				buffer[pos++] = '3';
				buffer[pos++] = '6';
				buffer[pos++] = '8';
				buffer[pos++] = '5';
				buffer[pos++] = '4';
				buffer[pos++] = '7';
				buffer[pos++] = '7';
				buffer[pos++] = '5';
				buffer[pos++] = '8';
				buffer[pos++] = '0';
				buffer[pos++] = '8';
				return;
			}

			boolean negative = (l < 0);
			if (negative) {
				l = -l;
				buffer[pos++] = '-';
			}

			int numOfDigits = countDigits(l);
			int writePos = pos + numOfDigits;
			while (l >= 100) {
				long quotient = l / 100;
				int remainder = (int) (l - quotient * 100) << 1;
				buffer[--writePos] = TWO_DIGIT_NUMBERS[remainder + 1];
				buffer[--writePos] = TWO_DIGIT_NUMBERS[remainder];
				l = quotient;
			}

			if (l < 10) {
				buffer[--writePos] = (byte) ('0' + l);
			} else {
				buffer[--writePos] = TWO_DIGIT_NUMBERS[(int) (l << 1) + 1];
				buffer[--writePos] = TWO_DIGIT_NUMBERS[(int) l << 1];
			}

			pos += numOfDigits;
		}

		private int countDigits(final int i) {
			if (i < 10)
				return 1;
			if (i < 100)
				return 2;
			if (i < 1000)
				return 3;
			if (i < 10000)
				return 4;
			if (i < 100000)
				return 5;
			if (i < 1000000)
				return 6;
			if (i < 10000000)
				return 7;
			if (i < 100000000)
				return 8;
			if (i < 1000000000)
				return 9;
			return 10;
		}

		private int countDigits(final long l) {
			if (l < 10000000000L)
				return 10;
			if (l < 100000000000L)
				return 11;
			if (l < 1000000000000L)
				return 12;
			if (l < 10000000000000L)
				return 13;
			if (l < 100000000000000L)
				return 14;
			if (l < 1000000000000000L)
				return 15;
			if (l < 10000000000000000L)
				return 16;
			if (l < 100000000000000000L)
				return 17;
			if (l < 1000000000000000000L)
				return 18;
			return 19;
		}

	}

}
