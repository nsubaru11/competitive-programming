import static java.lang.Long.*;
import static java.lang.Math.min;
import static java.util.Arrays.*;

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
		int n = sc.nextInt(); // town
		int m = sc.nextInt(); // load
		Warshallfroyd atcoder = new Warshallfroyd(n);
		while (m-- > 0) {
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			int c = sc.nextInt();
			atcoder.addEdge(u, v, c);
			atcoder.addEdge(v, u, c);
		}
		int k = sc.nextInt(); // airport num
		atcoder.setT(sc.nextInt());
		while (k-- > 0) {
			atcoder.addPort(sc.nextInt() - 1);
		}

		int q = sc.nextInt();
		while (q-- > 0) {
			int type = sc.nextInt();
			switch (type) {
				case 1:
					int u = sc.nextInt() - 1;
					int v = sc.nextInt() - 1;
					int c = sc.nextInt();
					atcoder.addEdge(u, v, c);
					atcoder.addEdge(v, u, c);
					break;
				case 2:
					atcoder.addPort(sc.nextInt() - 1);
					break;
				case 3:
					out.println(atcoder.getAllShortestPathWeightSum());
					//					out.println(atcoder.getAllShortestPathWeight());
					break;
			}
		}
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}

	/**
	 * Warshall-Floyd アルゴリズムを実装するクラス。
	 * 全点対間の最短経路を計算する。負閉路が存在する場合は、
	 * getShortestPathWeight() は -INF を返し、getAllShortestPathWeight() は null を返す。
	 * <p>
	 * ※ 隣接行列を用いるため、頂点数が大きい場合はメモリや計算量の観点で注意が必要。
	 */
	@SuppressWarnings("unused")
	private static final class Warshallfroyd {
		private static final long INF = MAX_VALUE;
		private final int v;
		private final long[][] dist;
		private final boolean[] airports;
		private boolean update = false;
		private boolean isNegative = false;
		private int t;

		/**
		 * コンストラクタ
		 *
		 * @param v 頂点数
		 */
		public Warshallfroyd(int v) {
			this.v = v;
			dist = new long[v][v];
			for (int i = 0; i < v; i++) {
				fill(dist[i], INF);
				dist[i][i] = 0;
			}
			airports = new boolean[v];
		}

		/**
		 * 有向辺を追加する。
		 * 既に辺が存在する場合は、より小さい重みを採用する。
		 *
		 * @param from 開始点（0-indexed）
		 * @param to   到着点（0-indexed）
		 * @param cost 辺の重み
		 */
		public void addEdge(int from, int to, long cost) {
			if (dist[from][to] > cost) {
				dist[from][to] = cost;
				update = true;
				isNegative = false;
			}
		}

		public void setT(int t) {
			this.t = t;
		}

		public void addPort(int i) {
			airports[i] = true;
			for (int j = 0; j < v; j++) {
				if (i != j && airports[j] && dist[i][j] > t) {
					dist[i][j] = t;
					dist[j][i] = t;
					update = true;
					isNegative = false;
				}
			}
		}

		/**
		 * 始点 from から終点 to への最短経路の重みを返す。
		 * 負閉路が存在する場合は -INF を返す。
		 *
		 * @param from 始点（0-indexed）
		 * @param to   終点（0-indexed）
		 * @return 始点から終点への最短経路の重み（到達不可能なら INF）
		 */
		public long getShortestPathWeight(int from, int to) {
			if (update) dist();
			return isNegative ? -INF : dist[from][to];
		}

		/**
		 * 全点対間の最短経路の重みを含む隣接行列を返す。
		 * 負閉路が存在する場合は null を返す。
		 *
		 * @return 全点対間の最短経路行列（負閉路がある場合は null）
		 */
		public long[][] getAllShortestPathWeight() {
			if (update) dist();
			return isNegative ? null : dist;
		}

		public long getAllShortestPathWeightSum() {
			if (update) dist();
			long sum = 0;
			for (int i = 0; i < v; i++) {
				for (int j = 0; j < v; j++) {
					sum += dist[i][j] == INF ? 0 : dist[i][j];
				}
			}
			return sum;
		}

		/**
		 * ワーシャルフロイド法により、全点対間の最短経路を計算する。
		 * 辺の緩和を全頂点対で行い、負閉路が検出された場合は isNegative を true にする。
		 */
		private void dist() {
			update = false;
			for (int via = 0; via < v; via++) {
				for (int from = 0; from < v; from++) {
					if (airports[from] && airports[via]) {
						dist[from][via] = min(dist[from][via], t);
					}
					if (dist[from][via] == INF) continue;
					for (int to = 0; to < v; to++) {
						if (airports[via] && airports[to]) {
							dist[via][to] = min(dist[via][to], t);
						}
						if (dist[via][to] == INF) continue;
						if (airports[from] && airports[to]) {
							dist[from][to] = min(dist[from][to], t);
						}
						dist[from][to] = min(dist[from][to], dist[from][via] + dist[via][to]);
						if (from == to && dist[from][to] < 0) {
							isNegative = true;
							return;
						}
					}
				}
			}
		}
	}
}
