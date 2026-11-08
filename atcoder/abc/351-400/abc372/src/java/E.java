import java.util.*;

import lib.io.*;

public class E {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int q = sc.nextInt();
		HashMap<Integer, TreeSet<Integer>> graph = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			graph.computeIfAbsent(i, k -> new TreeSet<>((o1, o2) -> o2 - o1)).add(i);
		}
		while (q-- > 0) {
			int t = sc.nextInt();
			if (t == 1) {
				int u = sc.nextInt();
				int v = sc.nextInt();
				graph.get(u).add(v);
				graph.get(v).add(u);
			} else {
				int v = sc.nextInt();
				int k = sc.nextInt();
				if (graph.getOrDefault(v, new TreeSet<>()).size() < k) {
					out.println(-1);
				} else {
					out.println(graph.get(v).stream().skip(k - 1).findFirst().get());
				}
			}
		}
		out.flush();
	}

	@SuppressWarnings("unused")
	private static class Deque {
		final int size;
		final int[] list;
		int head, tail, len;

		public Deque(int n) {
			size = n;
			list = new int[size];
			len = head = tail = 0;
		}

		public Deque addLast(int k) {
			if (isEmpty()) {
				head = tail = 0;
			} else if (len == size) {
				throw new IndexOutOfBoundsException("Index " + len + " out of bounds for length " + len);
			} else {
				tail = (tail + 1) % size;
			}
			list[tail] = k;
			len++;
			return this;
		}

		public Deque addFirst(int k) {
			if (isEmpty()) {
				head = tail = 0;
			} else if (len == size) {
				throw new IndexOutOfBoundsException("Index " + -1 + " out of bounds for length " + len);
			} else {
				head = head - 1 < 0 ? size - 1 : head - 1;
			}
			list[head] = k;
			len++;
			return this;
		}

		public int pollLast() {
			int res = list[tail];
			list[tail] = 0;
			if (!isEmpty()) {
				tail = (tail - 1) % size;
				len--;
			} else {
				throw new IndexOutOfBoundsException("Index " + 0 + " out of bounds for length " + len);
			}
			return res;
		}

		public int pollFirst() {
			int res = list[head];
			list[head] = 0;
			if (!isEmpty()) {
				head = (head + 1) % size;
				len--;
			} else {
				throw new IndexOutOfBoundsException("Index " + 0 + " out of bounds for length " + len);
			}
			return res;
		}

		public int peekLast() {
			if (isEmpty()) {
				throw new IndexOutOfBoundsException("Index " + 0 + " out of bounds for length " + len);
			}
			return list[tail];
		}

		public int peekFirst() {
			if (isEmpty()) {
				throw new IndexOutOfBoundsException("Index " + 0 + " out of bounds for length " + len);
			}
			return list[head];
		}

		public Deque set(int k, int idx) {
			if (idx >= len || idx < 0) {
				throw new IndexOutOfBoundsException("Index " + idx + " out of bounds for length " + len);
			}
			list[(head + idx) % size] = k;
			return this;
		}

		public Deque setLength(int len) {
			this.len = len;
			if (len == 0) {
				head = tail = 0;
			} else {
				tail = (head + len - 1) % size;
			}
			return this;
		}

		public Deque setLength(int strt, int end) {
			if (strt >= len || strt < 0) {
				throw new IndexOutOfBoundsException("Index " + strt + " out of bounds for length " + len);
			}
			if (end >= len || end < 0) {
				throw new IndexOutOfBoundsException("Index " + end + " out of bounds for length " + len);
			}
			len = end - strt + 1;
			int temp = head;
			head = (head + strt) % size;
			tail = (temp + end) % size;
			return this;
		}

		public boolean contains(int k) {
			int i = head;
			while (true) {
				if (list[i] == k) {
					return true;
				}
				if (i == tail) {
					return false;
				}
				i = (i + 1) % size;
			}
		}

		public Deque insert(int k, int idx) {
			int i = tail;
			while (true) {
				list[(i + 1) % size] = list[i];
				if ((head + idx) % size == i) {
					list[i] = k;
					break;
				}
				if (i == 0) {
					i = size - 1;
				} else {
					i = (i - 1) % size;
				}
			}
			len++;
			tail++;
			return this;
		}

		public boolean isEmpty() {
			return len == 0;
		}

		public int length() {
			return len;
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = head; i != tail; i = (i + 1) % size) {
				sb.append(list[i]).append(" ");
			}
			sb.append(list[tail]);
			return sb.toString();
		}

	}

	/**
	 * 整数と長整数に対して通常の二分探索、上限探索(Upper Bound)、下限探索(Lower Bound)を行うための抽象クラスです。
	 * 探索に失敗した際の戻り値は-(挿入位置(境界値) - 1)となっています。
	 */
	@SuppressWarnings("unused")
	private static abstract class AbstractBinarySearch {

		/**
		 * 整数範囲での通常の二分探索を行います。comparatorが0を返した時点で探索を終了します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含む)
		 * @return int
		 */
		public final int normalSearch(int l, int r) {
			return binarySearch(l, r, SearchType.NORMAL);
		}

		/**
		 * 長整数範囲での通常の二分探索を行います。comparatorが0を返した時点で探索を終了します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含む)
		 * @return long
		 */
		public final long normalSearch(long l, long r) {
			return binarySearch(l, r, SearchType.NORMAL);
		}

		/**
		 * 整数範囲での上限探索(Upper Bound)を行います。 目的の条件にちょうど当てはまる際にcomparatorが0を返すことが好ましい。
		 * comparatorが0を返した際、その値を記憶します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含む)
		 * @return 条件にちょうど当てはまる整数。もしくは上限値+1(挿入位置)。
		 */
		public final int upperBoundSearch(int l, int r) {
			return binarySearch(l, r, SearchType.UPPER_BOUND);
		}

		/**
		 * 長整数範囲での上限探索(Upper Bound)を行います。 目的の条件にちょうど当てはまる際にcomparatorが0を返すことが好ましい。
		 * comparatorが0を返した際、その値を記憶します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含む)
		 * @return 条件にちょうど当てはまる長整数。もしくは上限値+1(挿入位置)。
		 */
		public final long upperBoundSearch(long l, long r) {
			return binarySearch(l, r, SearchType.UPPER_BOUND);
		}

		/**
		 * 整数範囲での下限探索(Lower Bound)を行います。 目的の条件にちょうど当てはまる際にcomparatorが0を返すことが好ましい。
		 * comparatorが0を返した際、その値を記憶します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含む)
		 * @return 条件にちょうど当てはまる整数。もしくは下限値-1(挿入位置)。
		 */
		public final int lowerBoundSearch(int l, int r) {
			return binarySearch(l, r, SearchType.LOWER_BOUND);
		}

		/**
		 * 長整数範囲での下限探索(Lower Bound)を行います。 目的の条件にちょうど当てはまる際にcomparatorが0を返すことが好ましい。
		 * comparatorが0を返した際、その値を記憶します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含む)
		 * @return 条件にちょうど当てはまる長整数。もしくは下限値-1(挿入位置)。
		 */
		public final long lowerBoundSearch(long l, long r) {
			return binarySearch(l, r, SearchType.LOWER_BOUND);
		}

		/**
		 * 整数範囲での汎用二分探索メソッド
		 */
		private final int binarySearch(int l, int r, SearchType type) {
			Integer k = null;
			while (l <= r) {
				int m = (l + r) >> 1;
				switch (comparator(m)) {
					case 1:
						r = m - 1;
						break;
					case 0:
						switch (type) {
							case UPPER_BOUND:
								l = m + 1;
								break;
							case LOWER_BOUND:
								r = m - 1;
								break;
							case NORMAL:
								return m;
						}
						k = m;
						break;
					case -1:
						l = m + 1;
						break;
				}
			}
			return k != null ? k : ~l;
		}

		/**
		 * 長整数範囲での汎用二分探索メソッド
		 */
		private final long binarySearch(long l, long r, SearchType type) {
			Long k = null;
			while (l <= r) {
				long m = (l + r) >> 1L;
				switch (comparator(m)) {
					case 1:
						r = m - 1;
						break;
					case 0:
						switch (type) {
							case UPPER_BOUND:
								l = m + 1;
								break;
							case LOWER_BOUND:
								r = m - 1;
								break;
							case NORMAL:
								return m;
						}
						k = m;
						break;
					case -1:
						l = m + 1;
						break;
				}
			}
			return k != null ? k : ~l;
		}

		/**
		 * 問題に応じた実装を必要とします。条件を超過する際は1, ちょうど合致する際は0、そうでない場合は-1を返すことが望ましい。
		 */
		abstract protected int comparator(long n);

		/**
		 * 内部的に利用される探索種別を示す列挙型
		 */
		private enum SearchType {
			NORMAL, UPPER_BOUND, LOWER_BOUND
		}
	}
}
