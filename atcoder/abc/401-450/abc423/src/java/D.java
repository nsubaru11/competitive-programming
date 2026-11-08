import java.util.*;

import lib.io.*;

import static java.lang.Math.*;

public class D {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter(650000);
	// endregion

	private static void solve() {
		int n = sc.nextInt();
		int k = sc.nextInt();
		long t = 0;
		int cnt = 0;
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		while (n-- > 0) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			t = max(t, a);
			while (cnt + c > k) {
				Pair p = pq.poll();
				t = max(t, p.i);
				cnt -= p.j;
			}
			out.println(t);
			pq.add(new Pair(t + b, c));
			cnt += c;
		}
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	//	/**
	//	 * 優先度キュークラス
	//	 */
	//	@SuppressWarnings({"unchecked", "unused"})
	//	private static final class PriorityQueue<T extends Comparable<T>> implements Iterable<T> {
	//		private final Comparator<? super T> comparator;
	//		private T[] buf;
	//		private int size, capacity;
	//
	//		public PriorityQueue() {
	//			this(65536, Comparator.naturalOrder(), false);
	//		}
	//
	//		public PriorityQueue(int capacity) {
	//			this(capacity, Comparator.naturalOrder(), false);
	//		}
	//
	//		public PriorityQueue(Comparator<T> comparator) {
	//			this(65536, comparator, false);
	//		}
	//
	//		public PriorityQueue(boolean isDescendingOrder) {
	//			this(65536, Comparator.naturalOrder(), isDescendingOrder);
	//		}
	//
	//		public PriorityQueue(int capacity, Comparator<T> comparator) {
	//			this(capacity, comparator, false);
	//		}
	//
	//		public PriorityQueue(Comparator<T> comparator, boolean isDescendingOrder) {
	//			this(65536, comparator, isDescendingOrder);
	//		}
	//
	//		public PriorityQueue(int capacity, boolean isDescendingOrder) {
	//			this(capacity, Comparator.naturalOrder(), isDescendingOrder);
	//		}
	//
	//		public PriorityQueue(int capacity, Comparator<T> comparator, boolean isDescendingOrder) {
	//			this.capacity = capacity;
	//			this.comparator = isDescendingOrder ? comparator.reversed() : comparator;
	//			buf = (T[]) new Comparable<?>[capacity];
	//			size = 0;
	//		}
	//
	//		public void push(T v) {
	//			if (isFull()) buf = Arrays.copyOf(buf, capacity *= 2);
	//			int i = size++;
	//			while (i > 0) {
	//				int j = (i - 1) >> 1;
	//				if (comparator.compare(v, buf[j]) >= 0) break;
	//				buf[i] = buf[j];
	//				i = j;
	//			}
	//			buf[i] = v;
	//		}
	//
	//		public T poll() {
	//			if (isEmpty()) throw new NoSuchElementException();
	//			T res = buf[0];
	//			T v = buf[--size];
	//			int half = size >> 1;
	//			int i = 0;
	//			while (i < half) {
	//				int child = (i << 1) + 1, r = child + 1;
	//				if (r < size && comparator.compare(buf[child], buf[r]) > 0) {
	//					child = r;
	//				}
	//				if (comparator.compare(v, buf[child]) <= 0) break;
	//				buf[i] = buf[child];
	//				i = child;
	//			}
	//			buf[i] = v;
	//			return res;
	//		}
	//
	//		public T peek() {
	//			if (isEmpty()) throw new NoSuchElementException();
	//			return buf[0];
	//		}
	//
	//		public int size() {
	//			return size;
	//		}
	//
	//		public void clear() {
	//			size = 0;
	//		}
	//
	//		public boolean isEmpty() {
	//			return size == 0;
	//		}
	//
	//		private boolean isFull() {
	//			return size == capacity;
	//		}
	//
	//		@Override
	//		public Iterator<T> iterator() {
	//			return new Iterator<>() {
	//				int i = 0;
	//
	//				@Override
	//				public boolean hasNext() {
	//					return i < size;
	//				}
	//
	//				@Override
	//				public T next() {
	//					if (!hasNext()) throw new NoSuchElementException();
	//					return buf[i++];
	//				}
	//			};
	//		}
	//	}

	private static final class Pair implements Comparable<Pair> {
		long i;
		int j;

		public Pair(long i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public int compareTo(Pair o) {
			return Long.compare(i, o.i);
		}

		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Pair other)) return false;
			return i == other.i && j == other.j;
		}

		public String toString() {
			return i + " " + j;
		}
	}
}
