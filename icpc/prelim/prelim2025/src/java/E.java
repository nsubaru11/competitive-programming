import java.io.*;
import java.util.*;

import lib.ds.priorityqueue.*;

public final class E {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out);
	// endregion

	private static void solve() {
		outer:
		while (true) {
			int n = sc.nextInt();
			if (n == 0) return;
			boolean[] visited = new boolean[n];
			int[] from = new int[n];
			LongIndexedPriorityQueue pq = new LongIndexedPriorityQueue(n);
			for (int i = 1; i < n; i++) {
				int p = sc.nextInt() - 1;
				int e = sc.nextInt();
				from[i] = p;
				pq.add(i, e);
			}
			int time = 0, cnt = 0;
			visited[0] = true;
			int[] ans = new int[n - 1];
			int[] temp = new int[n];
			while (!pq.isEmpty()) {
				int i = pq.peekIndex();
				int e = (int) pq.peek();
				int k = 0;
				while (!visited[i]) {
					pq.remove(i);
					temp[k++] = i;
					visited[i] = true;
					i = from[i];
				}
				if (time + k - 1 > e) {
					out.println("no");
					continue outer;
				}
				time += k;
				while (k-- > 0) ans[cnt++] = temp[k] + 1;
			}
			out.println("yes");
			out.print(ans[0]);
			for (int i = 1; i < cnt; i++) {
				out.print(' ');
				out.print(ans[i]);
			}
			out.println();
		}
	}

	// region < I/O & Debug >
	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			sc.close();
			out.close();
		}
	}
	// endregion
}
