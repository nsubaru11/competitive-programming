import java.util.*;
import java.util.function.*;

import static java.util.Arrays.*;

public class B2 {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int v = sc.nextInt(), e = sc.nextInt(), r = sc.nextInt();

		UndirectedGraph g = new UndirectedGraph(v, e);
		for (int i = 0; i < e; i++) {
			g.add(sc.nextInt(), sc.nextInt(), sc.nextInt());
		}

		PathResult result = solve(g, r);
		if (result.hasNegCycle) {
			System.out.println("NEGATIVE CYCLE");
			return;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < v; i++) {
			if (i > 0) sb.append("\n");
			long temp = result.dist[i];
			if (temp == Long.MAX_VALUE) {
				sb.append("INF");
			} else {
				sb.append(temp);
			}
		}
		System.out.println(sb);
	}

	private static final long INF = Long.MAX_VALUE;
	private static final long NINF = Long.MIN_VALUE;

	/**
	 * 始点 {@code s} から全頂点への最短距離を計算します。
	 *
	 * @param graph 探索対象のグラフ
	 * @param s     始点（0-indexed）
	 * @return 計算結果
	 */
	public static PathResult solve(final Graph graph, final int s) {
		return solveInternal(graph, new int[]{s});
	}

	private static PathResult solveInternal(final Graph graph, final int[] s) {
		final int n = graph.n;
		final int[] dest = graph.dest, next = graph.next, first = graph.first;
		final long[] cost = graph.cost;
		final int[] parent = new int[n];
		fill(parent, -1);
		final long[] dist = new long[n];
		fill(dist, INF);
		for (final int si : s) {
			parent[si] = si;
			dist[si] = 0;
		}

		for (int k = 1; k < n; k++) {
			boolean updated = false;
			for (int u = 0; u < n; u++) {
				final long du = dist[u];
				if (du == INF) continue;
				for (int e = first[u]; e != -1; e = next[e]) {
					final int v = dest[e];
					final long c = cost[e];
					if (dist[v] > du + c) {
						dist[v] = du + c;
						parent[v] = u;
						updated = true;
					}
				}
			}
			if (!updated) break;
		}

		final boolean[] affected = new boolean[n];
		final int[] q = new int[n];
		int tail = 0;
		for (int u = 0; u < n; u++) {
			final long du = dist[u];
			if (du == INF) continue;
			for (int e = first[u]; e != -1; e = next[e]) {
				final int v = dest[e];
				if (dist[v] > du + cost[e] && !affected[v]) {
					affected[v] = true;
					q[tail++] = v;
				}
			}
		}

		for (int head = 0; head < tail; head++) {
			final int u = q[head];
			for (int e = first[u]; e != -1; e = next[e]) {
				final int v = dest[e];
				if (affected[v]) continue;
				affected[v] = true;
				q[tail++] = v;
			}
		}

		for (int i = 0; i < tail; i++) {
			final int v = q[i];
			dist[v] = NINF;
			parent[v] = -1;
		}
		return new PathResult(s, tail > 0, dist, parent);
	}

	/**
	 * 前方スター形式の隣接リストを保持するグラフの基底クラス。
	 * <p>
	 * 辺の終点・重み・次の辺番号をプリミティブ配列に保持します。
	 * 無向グラフでは、1本の無向辺を向きの異なる2本の内部辺として保持します。
	 * 配列の容量は構築時に固定され、辺追加時の自動拡張は行いません。
	 * <p>
	 * {@link #m} は現在の辺数ではなく、辺数引数を持たない {@link #setAll(IntSupplier, IntSupplier)}
	 * および {@link #setAll(IntSupplier, IntSupplier, LongSupplier)} が読み込む入力辺数です。
	 * 現在の論理辺数は {@link #edgeCount()} で取得します。
	 */
	private static abstract class Graph {
		public final int n, m;
		protected final int[] dest, next, first;
		protected final long[] cost;
		protected int edgePtr = 0;

		/**
		 * グラフの内部配列を確保します。
		 *
		 * @param n            頂点数
		 * @param m            {@code setAll} が読み込む入力辺数
		 * @param edgeCapacity 内部辺の最大数。有向グラフでは論理辺数、無向グラフではその2倍が必要
		 */
		protected Graph(final int n, final int m, final int edgeCapacity) {
			this.n = n;
			this.m = m;
			dest = new int[edgeCapacity];
			next = new int[edgeCapacity];
			first = new int[n];
			fill(first, -1);
			cost = new long[edgeCapacity];
		}

		/**
		 * 現在の辺をすべて削除し、重み1の辺を {@link #m} 本読み込みます。
		 *
		 * @param u 辺の始点を返すサプライヤ
		 * @param v 辺の終点を返すサプライヤ
		 */
		public void setAll(final IntSupplier u, final IntSupplier v) {
			clear();
			addAll(m, u, v);
		}

		/**
		 * 現在の辺をすべて削除し、重み付き辺を {@link #m} 本読み込みます。
		 *
		 * @param u 辺の始点を返すサプライヤ
		 * @param v 辺の終点を返すサプライヤ
		 * @param c 辺の重みを返すサプライヤ
		 */
		public void setAll(final IntSupplier u, final IntSupplier v, final LongSupplier c) {
			clear();
			addAll(m, u, v, c);
		}

		/**
		 * 重み1の辺を1本追加します。
		 *
		 * @param u 辺の始点
		 * @param v 辺の終点
		 */
		public void add(final int u, final int v) {
			add(u, v, 1);
		}

		/**
		 * 重み付き辺を1本追加します。
		 *
		 * @param u 辺の始点
		 * @param v 辺の終点
		 * @param c 辺の重み
		 */
		public abstract void add(final int u, final int v, final long c);

		/**
		 * 前方スター形式の内部辺を1本追加します。
		 *
		 * @param u 内部辺の始点
		 * @param v 内部辺の終点
		 * @param c 内部辺の重み
		 */
		protected final void addEdge(final int u, final int v, final long c) {
			dest[edgePtr] = v;
			next[edgePtr] = first[u];
			cost[edgePtr] = c;
			first[u] = edgePtr++;
		}

		/**
		 * 現在の辺に続けて、重み1の辺を {@code count} 本追加します。
		 *
		 * @param count 追加する辺数
		 * @param u     辺の始点を返すサプライヤ
		 * @param v     辺の終点を返すサプライヤ
		 */
		public void addAll(int count, final IntSupplier u, final IntSupplier v) {
			while (count-- > 0) add(u.getAsInt(), v.getAsInt(), 1);
		}

		/**
		 * 現在の辺に続けて、重み付き辺を {@code count} 本追加します。
		 *
		 * @param count 追加する辺数
		 * @param u     辺の始点を返すサプライヤ
		 * @param v     辺の終点を返すサプライヤ
		 * @param c     辺の重みを返すサプライヤ
		 */
		public void addAll(int count, final IntSupplier u, final IntSupplier v, final LongSupplier c) {
			while (count-- > 0) add(u.getAsInt(), v.getAsInt(), c.getAsLong());
		}

		/**
		 * 現在保持している辺をすべて削除します。
		 */
		public void clear() {
			if (edgePtr == 0) return;
			fill(first, -1);
			edgePtr = 0;
		}

		/**
		 * 現在保持している論理辺数を返します。
		 *
		 * @return 論理辺数
		 */
		public abstract int edgeCount();

		/**
		 * 指定した論理辺の重みを返します。
		 *
		 * @param e 辺ID
		 * @return 辺の重み
		 */
		public abstract long cost(final int e);

		/**
		 * 指定した頂点に接続する辺数を返します。
		 * 有向グラフでは入次数と出次数の合計です。
		 *
		 * @param v 頂点
		 * @return 次数
		 */
		public abstract int degree(final int v);

		/**
		 * 指定した頂点から隣接する頂点を返します。
		 * 有向グラフでは出辺の終点のみを返します。
		 *
		 * @param u 頂点
		 * @return 隣接頂点の配列
		 */
		public abstract int[] adj(final int u);

		/**
		 * 指定した頂点に接続する辺IDを返します。
		 * 有向グラフでは出辺のIDのみを返します。
		 *
		 * @param u 頂点
		 * @return 辺IDの配列
		 */
		public abstract int[] adjEdgeIds(final int u);
	}

	/**
	 * 前方スター形式の隣接リストで、自己ループを含まない無向グラフを保持するクラス。
	 * <p>
	 * 無向辺は追加順に0始まりの辺IDが割り当てられます。
	 * 内部表現では1本の無向辺を2本の内部辺として保持しますが、
	 * 外部には無向辺IDとして公開します。
	 * <p>
	 * {@link #adjEdgeIds(int)} で取得した辺IDは {@link #to(int, int)} と
	 * {@link #cost(int)} にそのまま渡せます。
	 * {@link #to(int, int)} は「頂点 {@code u} から見た接続先頂点」を返します。
	 */
	private static class UndirectedGraph extends Graph {
		private final int[] degree;

		/**
		 * {@code n} 頂点、{@code m} 辺分の領域を確保します。
		 * 内部では各無向辺を2本の内部辺として保持します。
		 *
		 * @param n 頂点数
		 * @param m 入力辺数
		 */
		public UndirectedGraph(final int n, final int m) {
			super(n, m, m << 1);
			degree = new int[n];
		}

		public void add(final int u, final int v, final long c) {
			addEdge(u, v, c);
			degree[u]++;
			addEdge(v, u, c);
			degree[v]++;
		}

		/**
		 * 無向辺 {@code e} について、頂点 {@code u} と反対側の端点を返します。
		 *
		 * @param u 辺 {@code e} の一方の端点
		 * @param e 無向辺ID
		 * @return 反対側の端点
		 */
		public int to(final int u, final int e) {
			final int v1 = dest[e << 1];
			final int v2 = dest[e << 1 | 1];
			return u != v1 ? v1 : v2;
		}

		public int edgeCount() {
			return edgePtr >> 1;
		}

		public long cost(final int e) {
			return cost[e << 1];
		}

		public int degree(final int i) {
			return degree[i];
		}

		public int[] adj(final int u) {
			final int[] adj = new int[degree[u]];
			for (int e = first[u], i = 0; e != -1; e = next[e], i++) {
				adj[i] = dest[e];
			}
			return adj;
		}

		public int[] adjEdgeIds(final int u) {
			final int[] ids = new int[degree[u]];
			for (int e = first[u], i = 0; e != -1; e = next[e], i++) {
				ids[i] = e >> 1;
			}
			return ids;
		}

		public void clear() {
			if (edgePtr == 0) return;
			fill(degree, 0);
			super.clear();
		}
	}

	/**
	 * 単一始点または複数始点の経路計算結果。
	 * <p>
	 * 負閉路を扱わないアルゴリズム（BFS・Dijkstra・0-1BFS等）では {@link #hasNegCycle} は常に
	 * {@code false} で、負閉路の影響を受ける頂点は現れません。
	 * Bellman-Ford法など負閉路を検出するアルゴリズムでは、到達不能な頂点の距離は
	 * {@link Long#MAX_VALUE}、到達可能な負閉路の影響を受ける頂点は {@link Long#MIN_VALUE} です。
	 */
	private static final class PathResult {
		private static final long INF = Long.MAX_VALUE, NINF = Long.MIN_VALUE;

		public final int[] s;
		public final boolean hasNegCycle;
		public final long[] dist;
		public final int[] parent;

		PathResult(final int s, final long[] dist, final int[] parent) {
			this(new int[]{s}, false, dist, parent);
		}

		PathResult(final int[] s, final long[] dist, final int[] parent) {
			this(s, false, dist, parent);
		}

		PathResult(final int s, final boolean hasNegCycle, final long[] dist, final int[] parent) {
			this(new int[]{s}, hasNegCycle, dist, parent);
		}

		PathResult(final int[] s, final boolean hasNegCycle, final long[] dist, final int[] parent) {
			this.s = s;
			this.hasNegCycle = hasNegCycle;
			this.dist = dist;
			this.parent = parent;
		}

		/**
		 * 指定した頂点への距離を返します。
		 *
		 * @param v 終点
		 * @return 距離。到達不能は {@link Long#MAX_VALUE}、負閉路の影響下は {@link Long#MIN_VALUE}
		 */
		public long distTo(final int v) {
			return dist[v];
		}

		/**
		 * 指定した頂点へ到達可能かを返します。
		 * 負閉路の影響下でも到達可能なら {@code true} です。
		 *
		 * @param v 終点
		 * @return 到達可能なら {@code true}
		 */
		public boolean reachable(final int v) {
			return dist[v] != INF;
		}

		/**
		 * 経路復元用の親を返します。
		 *
		 * @param v 頂点
		 * @return 親。始点自身は始点、到達不能または負閉路の影響下では {@code -1}
		 */
		public int parent(final int v) {
			return parent[v];
		}

		/**
		 * 始点から指定した頂点までの経路を頂点列として返します。
		 *
		 * @param v 終点
		 * @return 始点と終点を含む頂点列。到達不能または負閉路の影響下では {@code null}
		 */
		public int[] pathTo(final int v) {
			final long d = distTo(v);
			if (d == INF || d == NINF) return null;
			final int[] temp = new int[dist.length];
			int len = 0, p = v;
			for (; p != parent[p]; p = parent[p]) temp[len++] = p;
			temp[len++] = p;
			final int[] res = new int[len];
			for (int i = 0; i < len; i++) res[i] = temp[len - i - 1];
			return res;
		}

		/**
		 * 始点、負閉路の有無、距離配列、親配列を複数行の文字列として返します。
		 *
		 * @return 計算結果の文字列表現
		 */
		@Override
		public String toString() {
			return "s: " + Arrays.toString(s) + '\n' + "hasNegCycle: " + hasNegCycle + '\n' + "dist: " + Arrays.toString(dist) + '\n' + "parent: " + Arrays.toString(parent);
		}
	}
}
