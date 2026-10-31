import static java.lang.Math.*;
import static java.util.Arrays.*;

import java.util.*;

import lib.io.*;

public class E {

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
		int t = sc.nextInt();
		int max = 200000;
		Pair[] a = new Pair[max];
		setAll(a, i -> new Pair(i, 0));
		int[] b = new int[max];
		Queue<Integer> pq = new PriorityQueue<>(max, (o1, o2) -> Integer.compare(o2, o1));
		while (t-- > 0) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			for (int i = 0; i < n; i++) {
				a[i].index = i;
				a[i].value = sc.nextInt();
			}
			sort(a, 0, n);
			for (int i = 0; i < n; i++)
				b[i] = sc.nextInt();
			long sum = 0;
			pq.clear();
			for (int i = 0; i < k; i++) {
				pq.add(b[a[i].index]);
				sum += b[a[i].index];
			}
			long ans = a[k - 1].value * sum;
			for (int i = k; i < n; i++) {
				sum -= pq.poll();
				pq.add(b[a[i].index]);
				sum += b[a[i].index];
				ans = min(ans, a[i].value * sum);
			}
			out.println(ans);
		}
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	private static final class Pair implements Comparable<Pair> {
		int index, value;

		public Pair(int i, int j) {
			this.index = i;
			this.value = j;
		}

		@Override
		public int compareTo(Pair o) {
			return Integer.compare(value, o.value);
		}

		public boolean equals(Object o) {
			if (this == o) return true;
			if (!(o instanceof Pair other)) return false;
			return index == other.index && value == other.value;
		}

		public String toString() {
			return index + " " + value;
		}
	}
}
