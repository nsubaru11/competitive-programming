import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static java.lang.Math.max;
import static java.util.Arrays.setAll;

public class C2 {

	private static void solve(final FastScanner sc, final FastPrinter out, int n) {
		int[][] a = sc.nextIntMat(n, n);
		int[][] ans = new int[n][n];
		int h = (n - 1) / 2;
		for (int i = 0; i < n; i++) {
			int y = i % 2 == 0 ? h - i / 2 : n - (i + 1) / 2;
			for (int j = 0; j < n; j++) {
				int x = j % 2 == 0 ? h - j / 2 : n - (j + 1) / 2;
				ans[x][y] = a[j][i];
			}
		}
		out.println(ans);
	}

	public static void main(String[] args) {
		try (final FastScanner sc = new FastScanner();
			 final FastPrinter out = new FastPrinter(System.out)) {
			int n = sc.nextInt();
			while (n != 0) {
				solve(sc, out, n);
				n = sc.nextInt();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static final class FastScanner implements AutoCloseable {
		private static final int DEFAULT_BUFFER_SIZE = 65536;
		private final InputStream in;
		private final byte[] buffer;
		private int pos = 0, bufferLength = 0;

		public FastScanner() {
			this(System.in, DEFAULT_BUFFER_SIZE);
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

		public byte read() {
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

		public int nextInt() {
			int b = read();
			while (isWhitespace(b)) b = read();
			boolean negative = b == '-';
			if (negative) b = read();
			int result = 0;
			while ('0' <= b && b <= '9') {
				result = result * 10 + b - '0';
				b = read();
			}
			return negative ? -result : result;
		}

		public int[][] nextIntMat(final int h, final int w) {
			final int[][] a = new int[h][w];
			for (int i = 0; i < h; i++)
				setAll(a[i], j -> nextInt());
			return a;
		}
	}

	private static final class FastPrinter implements AutoCloseable {
		private static final int MAX_INT_DIGITS = 11;
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
		private final byte[] buffer;
		private final OutputStream out;
		private int pos = 0;

		public FastPrinter(final OutputStream out) {
			this(out, DEFAULT_BUFFER_SIZE);
		}

		public FastPrinter(final OutputStream out, final int bufferSize) {
			this.out = out;
			this.buffer = new byte[max(bufferSize, 64)];
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

		public void println() {
			ensureBufferSpace(1);
			buffer[pos++] = '\n';
		}

		public void print(final int i) {
			ensureBufferSpace(MAX_INT_DIGITS);
			fillBuffer(i);
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

		private void fillBuffer(long l) {
			if (l < 0) {
				buffer[pos++] = '-';
			} else {
				l = -l;
			}
			long quotient;
			int remainder;
			final int numOfDigits = countDigits(l);
			int writePos = pos + numOfDigits;
			while (l <= -100) {
				quotient = l / 100;
				remainder = (int) (quotient * 100 - l);
				buffer[--writePos] = DigitOnes[remainder];
				buffer[--writePos] = DigitTens[remainder];
				l = quotient;
			}
			quotient = l / 10;
			remainder = (int) (quotient * 10 - l);
			buffer[--writePos] = (byte) ('0' + remainder);
			if (quotient < 0) {
				buffer[--writePos] = (byte) ('0' - quotient);
			}
			pos += numOfDigits;
		}

		private int countDigits(final long l) {
			if (l > -10) return 1;
			if (l > -100) return 2;
			if (l > -1000) return 3;
			if (l > -10000) return 4;
			if (l > -100000) return 5;
			if (l > -1000000) return 6;
			if (l > -10000000) return 7;
			if (l > -100000000) return 8;
			if (l > -1000000000) return 9;
			if (l > -10000000000L) return 10;
			if (l > -100000000000L) return 11;
			if (l > -1000000000000L) return 12;
			if (l > -10000000000000L) return 13;
			if (l > -100000000000000L) return 14;
			if (l > -1000000000000000L) return 15;
			if (l > -10000000000000000L) return 16;
			if (l > -100000000000000000L) return 17;
			if (l > -1000000000000000000L) return 18;
			return 19;
		}

		public void println(final int[] arr, final char delimiter) {
			if (arr == null) return;
			final int len = arr.length;
			if (len == 0) return;
			print(arr[0]);
			for (int i = 1; i < len; i++) {
				ensureBufferSpace(MAX_INT_DIGITS + 1);
				buffer[pos++] = (byte) delimiter;
				fillBuffer(arr[i]);
			}
			println();
		}

		public void println(final int[][] arr) {
			println(arr, ' ');
		}

		public void println(final int[][] arr2d, final char delimiter) {
			if (arr2d == null) return;
			for (final int[] arr : arr2d)
				println(arr, delimiter);
		}
	}
}
