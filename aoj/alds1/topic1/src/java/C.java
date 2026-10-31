import java.io.*;
import java.math.*;
import java.util.*;
import java.util.function.*;

import static java.lang.Math.*;

public final class C {

	private static void solve(final FastScanner sc, final FastPrinter out) {
		int n = sc.nextInt();
		int cnt = 0;
		while (n-- > 0) {
			int a = sc.nextInt();
			if (PrimeUtils.isPrime(a)) cnt++;
		}
		out.println(cnt);
	}

	public static void main(String[] args) {
		try (final FastScanner sc = new FastScanner();
			 final FastPrinter out = new FastPrinter()) {
			solve(sc, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 素数に関するクラス
	 */
	@SuppressWarnings("unused")
	private static final class PrimeUtils {
		private static final int INF = Integer.MAX_VALUE;

		/**
		 * 素数判定
		 *
		 * @param n long
		 * @return {@code true}なら素数、{@code false}なら合成数
		 */
		public static boolean isPrime(long n) {
			if (n <= 1) return false;
			if (n == 2 || n == 3) return true;
			if (n % 2L == 0 || n % 3L == 0) return false;
			for (long i = 5L; i * i <= n; i += 6L) {
				if (n % i == 0 || n % (i + 2L) == 0) return false;
			}
			return true;
		}

		/**
		 * 素数の可能性が高いかを判定
		 * {@code k}の値が大きいほど精度が上がります。
		 * 合成数であることが確定している場合は{@code false}を返します。
		 *
		 * @param n 判定する数
		 * @param k 精度を決めるパラメータ
		 * @return {@code true}なら素数の可能性が高く、{@code false}なら合成数
		 */
		public static boolean isProbablePrime(long n, int k) {
			BigInteger a = BigInteger.valueOf(n);
			return (a.isProbablePrime(k));
		}

		/**
		 * 2以上n以下の素数の個数を返します。
		 *
		 * @param n 上限値
		 * @return 素数の個数
		 */
		public static int elements(int n) {
			return elements(2, n);
		}

		/**
		 * n以上k以下の素数の個数を返します。
		 *
		 * @param min 下限値
		 * @param max 上限値
		 * @return 素数の個数
		 */
		public static int elements(int min, int max) {
			BitSet table = new BitSet(max + 1);
			int count = 0;
			if (2 > min) count++;
			if (3 > min) count++;
			for (int i = 4; i <= max; i += 2) table.set(i);
			for (int i = 9; i <= max; i += 6) table.set(i);
			for (int i = 5; i <= max; i += 6) {
				for (int j = i; j <= i + 2 && j <= max; j += 2) {
					if (!table.get(j)) {
						if (min <= j) count++;
						for (long k = (long) j * j; k <= max; k += (long) j + j) {
							table.set((int) k);
						}
					}
				}
			}
			return count;
		}

		/**
		 * 2以上n以下の素数をセットで返します。
		 *
		 * @param n 上限値
		 * @return 素数のセット
		 */
		public static Set<Integer> getPrimeSet(int n) {
			return Eratosthenes(2, n, HashSet::new);
		}

		/**
		 * n以上k以下の素数をセットで返します。
		 *
		 * @param min 下限値
		 * @param max 上限値
		 * @return 素数のセット
		 */
		public static Set<Integer> getPrimeSet(int min, int max) {
			return Eratosthenes(min, max, HashSet::new);
		}

		/**
		 * 2以上n以下の素数をリストで返します。
		 *
		 * @param n 上限値
		 * @return 素数のリスト
		 */
		public static List<Integer> getPrimeList(int n) {
			return Eratosthenes(2, n, ArrayList::new);
		}

		/**
		 * n以上k以下の素数をリストで返します。
		 *
		 * @param min 下限値
		 * @param max 上限値
		 * @return 素数のリスト
		 */
		public static List<Integer> getPrimeList(int min, int max) {
			return Eratosthenes(min, max, ArrayList::new);
		}

		private static <T extends Collection<Integer>> T Eratosthenes(int min, int max, Supplier<T> supplier) {
			T primeNum = supplier.get();
			BitSet table = new BitSet(max + 1);
			if (min <= 2) primeNum.add(2);
			if (min <= 3) primeNum.add(3);
			for (int i = 4; i <= max; i += 2) table.set(i);
			for (int i = 9; i <= max; i += 6) table.set(i);
			for (int i = 5; i <= max; i += 6) {
				for (int j = i; j <= i + 2 && j <= max; j += 2) {
					if (!table.get(j)) {
						if (min <= j) primeNum.add(j);
						for (long k = (long) j * j; k <= max; k += (long) j + j) {
							table.set((int) k);
						}
					}
				}
			}
			return primeNum;
		}
	}

	@SuppressWarnings("unused")
	private static final class FastScanner implements AutoCloseable {

		private static final int DEFAULT_BUFFER_SIZE = 65536;

		private final InputStream in;
		private final byte[] buffer;
		private int pos = 0;
		private int bufferLength = 0;

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

		private int skipSpaces() {
			int b = read();
			while (b <= 32) b = read();
			return b;
		}

		@Override
		public void close() throws IOException {
			if (in != System.in) in.close();
		}

		private int read() {
			if (pos >= bufferLength) {
				try {
					bufferLength = in.read(buffer, pos = 0, buffer.length);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				if (bufferLength <= 0) throw new RuntimeException(new EOFException());
			}
			return buffer[pos++] & 0xFF;
		}

		public int peek() {
			try {
				int b = skipSpaces();
				pos--;
				return b;
			} catch (RuntimeException e) {
				return 0;
			}
		}

		public boolean hasNext() {
			return peek() != 0;
		}

		public int nextInt() {
			int b = skipSpaces();
			boolean negative = false;
			if (b != '-') {
				// fall through
			} else {
				negative = true;
				b = read();
			}
			int result = 0;
			while ('0' <= b && b <= '9') {
				result = (result << 3) + (result << 1) + (b & 15);
				b = read();
			}
			return negative ? -result : result;
		}

		public long nextLong() {
			int b = skipSpaces();
			boolean negative = false;
			if (b != '-') {
				// fall through
			} else {
				negative = true;
				b = read();
			}
			long result = 0;
			while ('0' <= b && b <= '9') {
				result = (result << 3) + (result << 1) + (b & 15);
				b = read();
			}
			return negative ? -result : result;
		}

		public double nextDouble() {
			int b = skipSpaces();
			boolean negative = false;
			if (b != '-') {
				// fall through
			} else {
				negative = true;
				b = read();
			}
			long intPart = 0;
			while ('0' <= b && b <= '9') {
				intPart = (intPart << 3) + (intPart << 1) + (b & 15);
				b = read();
			}
			double result = intPart;
			if (b == '.') {
				b = read();
				double scale = 0.1;
				while ('0' <= b && b <= '9') {
					result += (b & 15) * scale;
					scale *= 0.1;
					b = read();
				}
			}
			return negative ? -result : result;
		}

		public char nextChar() {
			int b = skipSpaces();
			return (char) b;
		}

		public String next() {
			return nextStringBuilder().toString();
		}

		public StringBuilder nextStringBuilder() {
			final StringBuilder sb = new StringBuilder();
			int b = skipSpaces();
			while (b > 32) {
				sb.append((char) b);
				b = read();
			}
			return sb;
		}

		public String nextLine() {
			final StringBuilder sb = new StringBuilder();
			int b = read();
			while (b != 0 && b != '\n' && b != '\r') {
				sb.append((char) b);
				b = read();
			}
			if (b == '\r') {
				int c = read();
				if (c != '\n') pos--;
			}
			return sb.toString();
		}

		public BigInteger nextBigInteger() {
			return new BigInteger(next());
		}

		public BigDecimal nextBigDecimal() {
			return new BigDecimal(next());
		}
	}

	@SuppressWarnings("unused")
	private static final class FastPrinter implements AutoCloseable {

		private static final int MAX_INT_DIGITS = 11;
		private static final int MAX_LONG_DIGITS = 20;
		private static final long[] POW10 = {
				1, 10, 100, 1_000, 10_000, 100_000, 1_000_000, 10_000_000, 100_000_000,
				1_000_000_000, 10_000_000_000L, 100_000_000_000L, 1_000_000_000_000L,
				10_000_000_000_000L, 100_000_000_000_000L, 1_000_000_000_000_000L,
				10_000_000_000_000_000L, 100_000_000_000_000_000L, 1_000_000_000_000_000_000L
		};
		private static final int DEFAULT_BUFFER_SIZE = 65536;
		private static final byte[] DigitTens = {
				'0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
				'1', '1', '1', '1', '1', '1', '1', '1', '1', '1',
				'2', '2', '2', '2', '2', '2', '2', '2', '2', '2',
				'3', '3', '3', '3', '3', '3', '3', '3', '3', '3',
				'4', '4', '4', '4', '4', '4', '4', '4', '4', '4',
				'5', '5', '5', '5', '5', '5', '5', '5', '5', '5',
				'6', '6', '6', '6', '6', '6', '6', '6', '6', '6',
				'7', '7', '7', '7', '7', '7', '7', '7', '7', '7',
				'8', '8', '8', '8', '8', '8', '8', '8', '8', '8',
				'9', '9', '9', '9', '9', '9', '9', '9', '9', '9',
		};
		private static final byte[] DigitOnes = {
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
		};
		private static final byte[] TRUE_BYTES = {'Y', 'e', 's'};
		private static final byte[] FALSE_BYTES = {'N', 'o'};

		private final byte[] buffer;
		private final boolean autoFlush;
		private final int BUFFER_SIZE;
		private final OutputStream out;
		private int pos = 0;

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
			this.BUFFER_SIZE = max(bufferSize, 64);
			this.buffer = new byte[BUFFER_SIZE];
			this.autoFlush = autoFlush;
		}

		@Override
		public void close() throws IOException {
			flush();
			if (out != System.out) out.close();
		}

		public void flush() {
			try {
				if (pos > 0) out.write(buffer, 0, pos);
				out.flush();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			pos = 0;
		}

		public FastPrinter println() {
			ensureBufferSpace(1);
			buffer[pos++] = '\n';
			if (autoFlush) flush();
			return this;
		}

		public FastPrinter println(final int i) {
			ensureBufferSpace(MAX_INT_DIGITS + 1);
			fillBuffer(i);
			buffer[pos++] = '\n';
			if (autoFlush) flush();
			return this;
		}

		public FastPrinter println(final long l) {
			ensureBufferSpace(MAX_LONG_DIGITS + 1);
			fillBuffer(l);
			buffer[pos++] = '\n';
			if (autoFlush) flush();
			return this;
		}

		public FastPrinter println(final double d) {
			return println(Double.toString(d));
		}

		public FastPrinter println(final char c) {
			ensureBufferSpace(2);
			buffer[pos++] = (byte) c;
			buffer[pos++] = '\n';
			if (autoFlush) flush();
			return this;
		}

		public FastPrinter println(final boolean b) {
			ensureBufferSpace(4);
			fillBuffer(b);
			buffer[pos++] = '\n';
			if (autoFlush) flush();
			return this;
		}

		public FastPrinter println(final String s) {
			final byte[] src = s.getBytes();
			fillBuffer(src, s.length());
			ensureBufferSpace(1);
			buffer[pos++] = '\n';
			if (autoFlush) flush();
			return this;
		}

		public FastPrinter println(final StringBuilder s) {
			final byte[] src = s.toString().getBytes();
			final int len = s.length();
			fillBuffer(src, len);
			ensureBufferSpace(1);
			buffer[pos++] = '\n';
			if (autoFlush) flush();
			return this;
		}

		public FastPrinter println(final BigInteger bi) {
			return println(bi.toString());
		}

		public FastPrinter println(final BigDecimal bd) {
			return println(bd.toString());
		}

		public FastPrinter print(final int i) {
			ensureBufferSpace(MAX_INT_DIGITS);
			fillBuffer(i);
			if (autoFlush) flush();
			return this;
		}

		public FastPrinter print(final long l) {
			ensureBufferSpace(MAX_LONG_DIGITS);
			fillBuffer(l);
			if (autoFlush) flush();
			return this;
		}

		public FastPrinter print(final double d) {
			return print(Double.toString(d));
		}

		public FastPrinter print(final char c) {
			ensureBufferSpace(1);
			buffer[pos++] = (byte) c;
			if (autoFlush) flush();
			return this;
		}

		public FastPrinter print(final boolean b) {
			ensureBufferSpace(3);
			fillBuffer(b);
			if (autoFlush) flush();
			return this;
		}

		public FastPrinter print(final String s) {
			final byte[] src = s.getBytes();
			fillBuffer(src, s.length());
			if (autoFlush) flush();
			return this;
		}

		public FastPrinter print(final StringBuilder s) {
			final byte[] src = s.toString().getBytes();
			final int len = s.length();
			fillBuffer(src, len);
			if (autoFlush) flush();
			return this;
		}

		public FastPrinter print(final BigInteger bi) {
			return print(bi.toString());
		}

		public FastPrinter print(final BigDecimal bd) {
			return print(bd.toString());
		}

		public FastPrinter printf(final String format, final Object... args) {
			return print(String.format(format, args));
		}

		public FastPrinter printf(final Locale locale, final String format, final Object... args) {
			return print(String.format(locale, format, args));
		}

		private void ensureBufferSpace(final int size) {
			if (pos + size > buffer.length) {
				try {
					out.write(buffer, 0, pos);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				pos = 0;
			}
		}

		private void fillBuffer(final byte[] src, final int len) {
			for (int i = 0; i < len; ) {
				ensureBufferSpace(1);
				int limit = min(BUFFER_SIZE - pos, len - i);
				System.arraycopy(src, i, buffer, pos, limit);
				pos += limit;
				i += limit;
			}
		}

		private void fillBuffer(final boolean b) {
			if (b) {
				System.arraycopy(TRUE_BYTES, 0, buffer, pos, 3);
				pos += 3;
			} else {
				System.arraycopy(FALSE_BYTES, 0, buffer, pos, 2);
				pos += 2;
			}
		}

		private void fillBuffer(int i) {
			if (i >= 0) i = -i;
			else buffer[pos++] = '-';
			int quotient, remainder;
			final int numOfDigits = countDigits(i);
			int writePos = pos + numOfDigits;
			while (i <= -100) {
				quotient = i / 100;
				remainder = (quotient << 6) + (quotient << 5) + (quotient << 2) - i;
				buffer[--writePos] = DigitOnes[remainder];
				buffer[--writePos] = DigitTens[remainder];
				i = quotient;
			}
			quotient = i / 10;
			remainder = (quotient << 3) + (quotient << 1) - i;
			buffer[--writePos] = (byte) ('0' + remainder);
			if (quotient < 0) buffer[--writePos] = (byte) ('0' - quotient);
			pos += numOfDigits;
		}

		private void fillBuffer(long l) {
			if (l >= 0) l = -l;
			else buffer[pos++] = '-';
			long quotient;
			int remainder;
			final int numOfDigits = countDigits(l);
			int writePos = pos + numOfDigits;
			while (l <= -100) {
				quotient = l / 100;
				remainder = (int) ((quotient << 6) + (quotient << 5) + (quotient << 2) - l);
				buffer[--writePos] = DigitOnes[remainder];
				buffer[--writePos] = DigitTens[remainder];
				l = quotient;
			}
			quotient = l / 10;
			remainder = (int) ((quotient << 3) + (quotient << 1) - l);
			buffer[--writePos] = (byte) ('0' + remainder);
			if (quotient < 0) buffer[--writePos] = (byte) ('0' - quotient);
			pos += numOfDigits;
		}

		private int countDigits(int i) {
			if (i > -100000) {
				if (i > -100) {
					return i > -10 ? 1 : 2;
				} else {
					if (i > -10000) return i > -1000 ? 3 : 4;
					else return 5;
				}
			} else {
				if (i > -10000000) {
					return i > -1000000 ? 6 : 7;
				} else {
					if (i > -1000000000) return i > -100000000 ? 8 : 9;
					else return 10;
				}
			}
		}

		private int countDigits(long l) {
			if (l > -1000000000) {
				if (l > -10000) {
					if (l > -100) {
						return l > -10 ? 1 : 2;
					} else {
						return l > -1000 ? 3 : 4;
					}
				} else {
					if (l > -1000000) {
						return l > -100000 ? 5 : 6;
					} else {
						if (l > -100000000) return l > -10000000 ? 7 : 8;
						else return 9;
					}
				}
			} else {
				if (l > -10000000000000L) {
					if (l > -100000000000L) {
						return l > -10000000000L ? 10 : 11;
					} else {
						return l > -1000000000000L ? 12 : 13;
					}
				} else {
					if (l > -10000000000000000L) {
						if (l > -1000000000000000L) return l > -100000000000000L ? 14 : 15;
						else return 16;
					} else {
						if (l > -1000000000000000000L) return l > -100000000000000000L ? 17 : 18;
						else return 19;
					}
				}
			}
		}
	}

}
