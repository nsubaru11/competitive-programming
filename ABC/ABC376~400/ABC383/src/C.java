import static java.util.Arrays.*;

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
	private static final FastPrinter out = new FastPrinter();
	// endregion

	private static void solve() {
		int h = sc.nextInt();
		int w = sc.nextInt();
		int d = sc.nextInt();
		char[][] s = new char[h][w];
		boolean[][] f = new boolean[h][w];
		Deque dq = new Deque(h * w);
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				s[i][j] = sc.nextChar();
				if (s[i][j] == 'H') {
					dq.addLast(new Tuple(i, j));
				}
			}
		}
		int ans = 0;
		int[][] dxy = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		while (!dq.isEmpty()) {
			Tuple tuple = dq.pollFirst();
			int i = tuple.i;
			int j = tuple.j;
			int cnt = tuple.cnt;
			if (!f[i][j]) {
				ans++;
			}
			f[i][j] = true;
			if (cnt < d) {
				for (int k = 0; k < 4; k++) {
					int di = i + dxy[k][0];
					int dj = j + dxy[k][1];
					if (di < h && dj < w && di >= 0 && dj >= 0) {
						if (!f[di][dj] && s[di][dj] == '.') {
							Tuple t = new Tuple(di, dj);
							t.cnt = cnt + 1;
							dq.addLast(t);
						}
					}
				}
			}
		}
		System.out.println(ans);
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	@SuppressWarnings("unused")
	private static class Deque {
		final int size;
		final Tuple[] list;
		int head, tail, len;

		public Deque(int n) {
			size = n;
			list = new Tuple[size];
			setAll(list, i -> new Tuple(0, 0));
			len = head = tail = 0;
		}

		public Deque addLast(Tuple p) {
			if (isEmpty()) {
				head = tail = 0;
			} else {
				tail = (tail + 1) % size;
			}
			list[tail] = p;
			len++;
			return this;
		}

		public Deque addFirst(Tuple p) {
			if (isEmpty()) {
				head = tail = 0;
			} else {
				head = head - 1 < 0 ? size - 1 : head - 1;
			}
			list[head] = p;
			len++;
			return this;
		}

		public Tuple pollLast() {
			Tuple res = list[tail];
			if (!isEmpty()) {
				tail = (tail - 1) % size;
				len--;
			}
			return res;
		}

		public Tuple pollFirst() {
			Tuple res = list[head];
			if (!isEmpty()) {
				head = (head + 1) % size;
				len--;
			}
			return res;
		}

		public Tuple peekLast() {
			return list[tail];
		}

		public Tuple peekFirst() {
			return list[head];
		}

		public Deque set(Tuple p, int idx) {
			list[(head + idx) % size] = p;
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
			this.len = end - strt + 1;
			int temp = head;
			head = (head + strt) % size;
			tail = (temp + end) % size;
			return this;
		}

		public boolean contains(Tuple p) {
			int i = head;
			while (true) {
				if (list[i].equals(p)) {
					return true;
				}
				if (i == tail) {
					return false;
				}
				i = (i + 1) % size;
			}
		}

		public Deque insert(Tuple p, int idx) {
			int i = tail;
			while (true) {
				list[(i + 1) % size] = list[i];
				if ((head + idx) % size == i) {
					list[i] = p;
					break;
				}
				i = (i - 1) % size;
			}
			len++;
			tail++;
			return this;
		}

		public boolean isEmpty() {
			return len == 0;
		}
	}

	private static class Tuple {
		int i, j, cnt = 0;

		public Tuple(int i, int j) {
			this.i = i;
			this.j = j;
		}

		public boolean equals(Object o) {
			if (o instanceof Tuple) {
				return i == ((Tuple) o).i && j == ((Tuple) o).j;
			}
			return false;
		}
	}
}
