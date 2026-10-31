import java.util.*;
import java.util.function.*;

import lib.io.*;

public class C {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter((int) 1e6);
	// endregion

	private static void solve() {
		int q = sc.nextInt();
		Deque dq = new Deque(q);
		long pos = 0;
		while (q-- > 0) {
			int i = sc.nextInt();
			switch (i) {
				case 1:
					int l = sc.nextInt();
					if (dq.isEmpty()) pos = 0;
					dq.addLast(dq.isEmpty() ? l : dq.peekLast() + l);
					break;
				case 2:
					pos = dq.pollFirst();
					if (dq.isEmpty()) pos = 0;
					break;
				case 3:
					int k = sc.nextInt();
					if (k == 1) {
						out.println(0);
					} else {
						out.println(dq.get(k - 2) - pos);
					}
			}
		}
		out.flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static class Deque {
		private final int size;
		private final long[] list;
		private int head, tail, len;

		public Deque(int n) {
			size = n;
			list = new long[size];
			len = head = tail = 0;
		}

		public Deque addLast(long e) {
			if (len == size) throw new IndexOutOfBoundsException("Deque is full");
			list[tail] = e;
			tail = mod(tail + 1, size);
			len++;
			return this;
		}

		public Deque addFirst(long e) {
			if (len == size) throw new IndexOutOfBoundsException("Deque is full");
			head = mod(head - 1, size);
			list[head] = e;
			len++;
			return this;
		}

		public long get(int index) {
			if (index >= len || index < -len)
				throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + len);
			if (index < 0) index += len;
			index = mod(index + head, size);
			return list[index];
		}

		public long pollLast() {
			if (isEmpty()) throw new NoSuchElementException("Deque is empty");
			len--;
			tail = mod(tail - 1, size);
			return get(-1);
		}

		public long pollFirst() {
			if (isEmpty()) throw new NoSuchElementException("Deque is empty");
			long res = list[head];
			head = mod(head + 1, size);
			len--;
			return res;
		}

		public long peekLast() {
			if (isEmpty()) throw new NoSuchElementException("Deque is empty");
			return get(-1);
		}

		public long peekFirst() {
			if (isEmpty()) throw new NoSuchElementException("Deque is empty");
			return get(0);
		}

		public Deque set(int index, long e) {
			if (index > len || index < -len)
				throw new IndexOutOfBoundsException("Index " + index + " out of bounds for length " + len);
			if (index == len) {
				addLast(e);
			} else {
				if (index < 0) index += len;
				index = mod(index + head, size);
				list[index] = e;
			}
			return this;
		}

		public Deque setLength(int len) {
			if (len > size) throw new IndexOutOfBoundsException("Index " + len + " out of bounds for length " + size);
			if (this.len < len) {
				while (this.len < len) {
					this.addLast(0);
				}
			} else if (len <= 0) {
				head = tail = this.len = 0;
			} else {
				this.len = len;
				tail = mod(head + len, size);
			}
			return this;
		}

		public Deque setLength(int strt, int end) {
			if (strt < 0 || size <= strt)
				throw new IndexOutOfBoundsException("Index " + strt + " out of bounds for length " + size);
			if (end < 0 || size < end)
				throw new IndexOutOfBoundsException("Index " + end + " out of bounds for length " + size);
			if (mod(head + strt, size) >= tail) {
				end -= strt;
				setLength(end);
			} else if (mod(head + end, size) >= tail) {
				while (mod(head + end, size) > tail) {
					this.addLast(0);
				}
				len = end - strt;
				head = mod(head + strt, size);
			} else {
				len = end - strt;
				head = mod(head + strt, size);
				tail = mod(head + len, size);
			}
			return this;
		}

		public boolean contains(long e) {
			for (int i = 0; i < len; i++) {
				if (get(i) == e) return true;
			}
			return false;
		}

		public Deque insert(int index, long e) {
			for (int i = len; i > index; i--) {
				set(i, get(i - 1));
			}
			set(index, e);
			return this;
		}

		public boolean isEmpty() {
			return len == 0;
		}

		public int length() {
			return len;
		}

		public Deque setAll(LongFunction<Long> generator) {
			head = tail = len = 0;
			for (int i = 0; i < size; i++) {
				addLast(generator.apply(i));
			}
			return this;
		}

		public Deque fill(long e) {
			setAll(i -> e);
			return this;
		}

		public boolean equals(Object o) {
			if (o instanceof Deque) {
				Deque dq = (Deque) o;
				if (dq.length() == len) {
					for (int i = 0; i < len; i++) {
						if (dq.get(i) != get(i)) return false;
					}
					return true;
				}
			}
			return false;
		}

		public String toString() {
			if (len == 0) return "";
			StringBuilder sb = new StringBuilder();
			sb.append(get(0));
			for (int i = 1; i < len; i++) {
				sb.append(" ").append(get(i));
			}
			return sb.toString();
		}

		private int mod(int a, int b) {
			if (a < 0) return a + b;
			if (a < b) return a;
			return a - b;
		}
	}
}
