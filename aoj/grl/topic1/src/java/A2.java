import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import static java.util.Arrays.*;

public class A2 {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out, false);
	private static final long INF = Long.MAX_VALUE;

	public static void main(String[] args) {
		int v = sc.nextInt(), e = sc.nextInt(), r = sc.nextInt();
		DirectedGraph g = new DirectedGraph(v, e);
		for (int i = 0; i < e; i++) {
			g.add(sc.nextInt(), sc.nextInt(), sc.nextInt());
		}
		long[] dist = dist(g, r);
		for (int i = 0; i < v; i++) {
			long temp = dist[i];
			out.println(temp == Long.MAX_VALUE ? "INF" : temp);
		}
		out.flush();
	}

	/**
	 * 始点 {@code s} から全頂点への最短距離を返します。
	 * 到達不能な頂点の値は {@link Long#MAX_VALUE} です。
	 *
	 * @param graph 探索対象のグラフ
	 * @param s     始点（0-indexed）
	 * @return 最短距離の配列
	 */
	public static long[] dist(final Graph graph, final int s) {
		final PathResult result = solve(graph, s);
		return result.dist;
	}

	/**
	 * 始点 {@code s} から全頂点への最短距離と経路復元情報を計算します。
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
		final LongIndexedPriorityQueue dist = new LongIndexedPriorityQueue(n);
		for (final int si : s) {
			parent[si] = si;
			dist.add(si, 0);
		}

		while (!dist.isEmpty()) {
			final int u = dist.peekIndex();
			final long du = dist.poll();
			for (int e = first[u]; e != -1; e = next[e]) {
				final int v = dest[e];
				final long c = cost[e];
				if (dist.relax(v, du + c)) parent[v] = u;
			}
		}
		final long[] res = new long[graph.n];
		setAll(res, i -> dist.getCostOrDefault(i, INF));
		return new PathResult(s, res, parent);
	}

	/**
	 * long値を保持するコレクションの読み取り用インターフェースである。
	 * 走査とそれを使う一括処理の実行中は、要素数や走査順を変更しないこと。
	 */
	private interface LongCollection extends Iterable<Long> {

		int size();

		/**
		 * プリミティブ値を走査する iterator を返す。走査順は実装に依存する。
		 */
		PrimitiveIterator.OfLong iterator();

		default boolean isEmpty() {
			return size() == 0;
		}

		/**
		 * 指定値を含むかを線形探索で判定する。
		 */
		default boolean contains(final long v) {
			for (final PrimitiveIterator.OfLong it = iterator(); it.hasNext(); ) {
				if (it.nextLong() == v) return true;
			}
			return false;
		}

		/**
		 * iterator の走査順で各要素に action を適用する。
		 */
		default void forEachLong(final LongConsumer action) {
			iterator().forEachRemaining(action);
		}

		/**
		 * SIZED・SUBSIZED の spliterator を返す。既定実装は ORDERED を指定しない。
		 */
		default Spliterator.OfLong spliterator() {
			return Spliterators.spliterator(iterator(), size(), 0);
		}

		/**
		 * 逐次実行のプリミティブ Stream を返す。
		 */
		default LongStream longStream() {
			return StreamSupport.longStream(spliterator(), false);
		}

		/**
		 * iterator の走査順にボックス化した、変更不可の List を返す。
		 */
		default List<Long> toList() {
			return longStream().boxed().collect(Collectors.toList());
		}

		/**
		 * iterator の走査順で独立したプリミティブ配列へコピーする。
		 */
		default long[] toArray() {
			final int len = size();
			final long[] a = new long[len];
			final PrimitiveIterator.OfLong it = iterator();
			for (int i = 0; i < len; i++) a[i] = it.nextLong();
			return a;
		}
	}

	/**
	 * 2つのlong値を比較する関数です。
	 */
	@FunctionalInterface
	private interface LongComparator {

		/**
		 * 昇順のComparatorを返します。
		 *
		 * @return 昇順のComparator
		 */
		static LongComparator naturalOrder() {
			return Long::compare;
		}

		/**
		 * 降順のComparatorを返します。
		 *
		 * @return 降順のComparator
		 */
		static LongComparator reverseOrder() {
			return (a, b) -> Long.compare(b, a);
		}

		/**
		 * 2つの値を比較します。
		 *
		 * @param a 1つ目の値
		 * @param b 2つ目の値
		 * @return aが先なら負、同順位なら0、bが先なら正
		 */
		int compare(long a, long b);

		/**
		 * 比較順序を反転したComparatorを返します。
		 *
		 * @return 反転したComparator
		 */
		default LongComparator reversed() {
			return (a, b) -> compare(b, a);
		}
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
	 * 前方スター形式の隣接リストで有向グラフを保持するクラス。
	 * <p>
	 * 辺は追加順に0始まりの辺IDが割り当てられます。
	 * {@link #adjEdgeIds(int)} で取得した辺IDは {@link #to(int)} と {@link #cost(int)} にそのまま渡せます。
	 */
	private static final class DirectedGraph extends Graph {
		final int[] inDegree, outDegree;

		/**
		 * {@code n} 頂点、{@code m} 辺分の領域を確保します。
		 * {@code m} は {@code setAll} が読み込む辺数でもあります。
		 *
		 * @param n 頂点数
		 * @param m 入力辺数
		 */
		public DirectedGraph(final int n, final int m) {
			super(n, m, m);
			inDegree = new int[n];
			outDegree = new int[n];
		}

		public void add(final int u, final int v, final long c) {
			addEdge(u, v, c);
			outDegree[u]++;
			inDegree[v]++;
		}

		/**
		 * 指定した辺の終点を返します。
		 *
		 * @param e 辺ID
		 * @return 辺の終点
		 */
		public int to(final int e) {
			return dest[e];
		}

		public int edgeCount() {
			return edgePtr;
		}

		public long cost(final int e) {
			return cost[e];
		}

		/**
		 * 指定した頂点の入次数と出次数の合計を返します。
		 * 自己ループは入次数と出次数にそれぞれ1本ずつ数えます。
		 *
		 * @param i 頂点
		 * @return 入次数と出次数の合計
		 */
		public int degree(final int i) {
			return inDegree[i] + outDegree[i];
		}

		/**
		 * 指定した頂点の入次数を返します。
		 *
		 * @param i 頂点
		 * @return 入次数
		 */
		public int inDegree(final int i) {
			return inDegree[i];
		}

		/**
		 * 指定した頂点の出次数を返します。
		 *
		 * @param i 頂点
		 * @return 出次数
		 */
		public int outDegree(final int i) {
			return outDegree[i];
		}

		public int[] adj(final int u) {
			final int[] adj = new int[outDegree[u]];
			for (int e = first[u], i = 0; e != -1; e = next[e], i++) {
				adj[i] = dest[e];
			}
			return adj;
		}

		public int[] adjEdgeIds(final int u) {
			final int[] ids = new int[outDegree[u]];
			for (int e = first[u], i = 0; e != -1; e = next[e], i++) {
				ids[i] = e;
			}
			return ids;
		}

		public void clear() {
			if (edgePtr == 0) return;
			fill(inDegree, 0);
			fill(outDegree, 0);
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

	/**
	 * 添字 {@code 0 <= i < n} に long のコストを対応付ける優先度キュー。Dijkstra 法などで、添字ごとのコストを更新しながら優先されるものから取り出す用途に使う。
	 * n は構築時に決め、添字の範囲は検査しない。比較順は昇順・降順・LongComparator から選ぶ。同順位のコストを持つ添字の取り出し順は規定しない。
	 * 各添字は「未追加」「キュー内」「取り出し済み」のいずれかの状態を持つ。poll 系と remove で取り出した添字は取り出し済みになり、clear で全添字が未追加に戻る。
	 * 空の peek・poll 系は NoSuchElementException を投げ、OrDefault 系は指定した既定値を返す。走査と toString はキュー内のコストを内部配列の順に返す。
	 */
	private static final class LongIndexedPriorityQueue implements LongCollection {
		public final int indexCount;
		private final boolean isDescendingOrder;
		private final LongComparator comparator;
		private final int[] heap, position;
		private final long[] cost;
		private int size, unsortedCount, stamp;

		public LongIndexedPriorityQueue(final int n) {
			this(n, false, null);
		}

		public LongIndexedPriorityQueue(final int n, final boolean isDescendingOrder) {
			this(n, isDescendingOrder, null);
		}

		public LongIndexedPriorityQueue(final int n, final LongComparator comparator) {
			this(n, false, Objects.requireNonNull(comparator));
		}

		/**
		 * costs[i] を添字 i のコストとして、全添字をキューに入れる。
		 */
		public LongIndexedPriorityQueue(final long[] costs) {
			this(costs, false);
		}

		public LongIndexedPriorityQueue(final long[] costs, final boolean isDescendingOrder) {
			this(costs.length, isDescendingOrder, null);
			System.arraycopy(costs, 0, cost, 0, costs.length);
			build();
		}

		public LongIndexedPriorityQueue(final long[] costs, final LongComparator comparator) {
			this(costs.length, false, Objects.requireNonNull(comparator));
			System.arraycopy(costs, 0, cost, 0, costs.length);
			build();
		}

		/**
		 * iterator の j 番目の値を添字 j のコストとして、全添字をキューに入れる。
		 */
		public LongIndexedPriorityQueue(final LongCollection costs) {
			this(costs, false);
		}

		public LongIndexedPriorityQueue(final LongCollection costs, final boolean isDescendingOrder) {
			this(costs.size(), isDescendingOrder, null);
			copyCosts(costs);
		}

		public LongIndexedPriorityQueue(final LongCollection costs, final LongComparator comparator) {
			this(costs.size(), false, Objects.requireNonNull(comparator));
			copyCosts(costs);
		}

		public LongIndexedPriorityQueue(final LongIndexedPriorityQueue other) {
			isDescendingOrder = other.isDescendingOrder;
			comparator = other.comparator;
			indexCount = other.indexCount;
			heap = other.heap.clone();
			position = other.position.clone();
			cost = other.cost.clone();
			size = other.size;
			unsortedCount = other.unsortedCount;
			stamp = other.stamp;
		}

		private LongIndexedPriorityQueue(final int n, final boolean isDescendingOrder, final LongComparator comparator) {
			this.isDescendingOrder = isDescendingOrder;
			this.comparator = comparator;
			indexCount = n;
			heap = new int[n];
			position = new int[n];
			cost = new long[n];
			stamp = -1;
		}

		/**
		 * キュー内にある添字の数を返す。
		 */
		public int size() {
			return size;
		}

		/**
		 * 使用できる添字の数 n を返す。
		 */
		public int indexCount() {
			return indexCount;
		}

		/**
		 * 添字 i がキュー内にあるか判定する。コストを探す contains とは異なる。
		 */
		public boolean containsIndex(final int i) {
			return isActive(i);
		}

		/**
		 * 添字 i がキュー内にあるか、取り出し済みか判定する。true なら getCost でコストを取得できる。
		 */
		public boolean hasCost(final int i) {
			return !isUnseen(i);
		}

		/**
		 * キュー内にある添字 i のコストを返す。キュー内になければ NoSuchElementException を投げる。
		 */
		public long get(final int i) {
			if (!isActive(i)) throw new NoSuchElementException();
			return cost[i];
		}

		public long getOrDefault(final int i, final long defaultValue) {
			return isActive(i) ? cost[i] : defaultValue;
		}

		/**
		 * 添字 i が最後に持っていたコストを返す。取り出し済みの添字にも使え、Dijkstra 法の確定距離の取得に使える。
		 * 未追加の添字では NoSuchElementException を投げる。
		 */
		public long getCost(final int i) {
			if (isUnseen(i)) throw new NoSuchElementException();
			return cost[i];
		}

		public long getCostOrDefault(final int i, final long defaultValue) {
			return isUnseen(i) ? defaultValue : cost[i];
		}

		public long peek() {
			if (isEmpty()) throw new NoSuchElementException();
			if (unsortedCount > 0) ensureHeapProperty();
			return cost[heap[0]];
		}

		public long peekOrDefault(final long defaultValue) {
			return isEmpty() ? defaultValue : peek();
		}

		public int peekIndex() {
			if (isEmpty()) throw new NoSuchElementException();
			if (unsortedCount > 0) ensureHeapProperty();
			return heap[0];
		}

		public int peekIndexOrDefault(final int defaultIndex) {
			return isEmpty() ? defaultIndex : peekIndex();
		}

		/**
		 * 2番目に優先されるコストを返す。要素数は2以上とする。
		 */
		public long peekSecond() {
			if (size < 2) throw new NoSuchElementException();
			if (unsortedCount > 0) ensureHeapProperty();
			if (size == 2) return cost[heap[1]];
			return compare(cost[heap[1]], cost[heap[2]]) <= 0 ? cost[heap[1]] : cost[heap[2]];
		}

		/**
		 * 添字 i のコストを c にする。キュー内になければ c でキューに入れる。キューに入れた場合に true を返す。
		 */
		public boolean set(final int i, final long c) {
			if (!isActive(i)) {
				append(i, c);
				return true;
			}
			final int order = compare(c, cost[i]);
			cost[i] = c;
			if (order == 0) return false;
			final int p = position[i];
			final int sortedSize = size - unsortedCount;
			if (p >= sortedSize) return false;
			if (order < 0) siftUp(i, p);
			else siftDown(i, p, sortedSize);
			return false;
		}

		/**
		 * 添字 i がキュー内になければ c でキューに入れて true を返す。キュー内なら何もせず false を返す。取り出し済みの添字も入れ直す。
		 */
		public boolean add(final int i, final long c) {
			if (isActive(i)) return false;
			append(i, c);
			return true;
		}

		/**
		 * c が添字 i の現在のコストより優先される場合だけ更新し、未追加の添字はキューに入れる。更新した場合に true を返す。
		 * 取り出し済みの添字は入れ直さずに false を返す。
		 */
		public boolean relax(final int i, final long c) {
			if (isRemoved(i)) return false;
			if (!isActive(i)) {
				append(i, c);
				return true;
			}
			if (compare(c, cost[i]) >= 0) return false;
			final int p = position[i];
			cost[i] = c;
			if (p < size - unsortedCount) siftUp(i, p);
			return true;
		}

		public long poll() {
			if (isEmpty()) throw new NoSuchElementException();
			if (unsortedCount > 0) ensureHeapProperty();
			final int i = heap[0];
			final long c = cost[i];
			position[i] = stamp;
			if (--size > 0) siftDown(heap[size], 0, size);
			return c;
		}

		public long pollOrDefault(final long defaultValue) {
			return isEmpty() ? defaultValue : poll();
		}

		public int pollIndex() {
			if (isEmpty()) throw new NoSuchElementException();
			if (unsortedCount > 0) ensureHeapProperty();
			final int i = heap[0];
			position[i] = stamp;
			if (--size > 0) siftDown(heap[size], 0, size);
			return i;
		}

		public int pollIndexOrDefault(final int defaultIndex) {
			return isEmpty() ? defaultIndex : pollIndex();
		}

		/**
		 * 添字 i がキュー内にあれば取り出し済みにして true、なければ false を返す。
		 */
		public boolean remove(final int i) {
			if (!isActive(i)) return false;
			if (position[i] >= size - unsortedCount) {
				removeUnsorted(i);
				return true;
			}
			if (unsortedCount > 0) ensureHeapProperty();
			final int p = position[i];
			position[i] = stamp;
			if (--size != p) {
				final int j = heap[size];
				if (compare(cost[j], cost[i]) < 0) siftUp(j, p);
				else siftDown(j, p, size);
			}
			return true;
		}

		/**
		 * 全添字の状態を消去し、{@code init(i)} を添字 i のコストとして全添字をキューに入れる。
		 */
		public LongIndexedPriorityQueue setAll(final IntToLongFunction init) {
			clear();
			for (int i = 0; i < indexCount; i++) cost[i] = init.applyAsLong(i);
			build();
			return this;
		}

		public void clear() {
			size = unsortedCount = 0;
			stamp--;
		}

		public PrimitiveIterator.OfLong iterator() {
			return new PrimitiveIterator.OfLong() {
				private int i;

				public boolean hasNext() {
					return i < size;
				}

				public long nextLong() {
					if (!hasNext()) throw new NoSuchElementException();
					return cost[heap[i++]];
				}
			};
		}

		public String toString() {
			if (isEmpty()) return "";
			final StringBuilder sb = new StringBuilder(21 * size - 1);
			sb.append(cost[heap[0]]);
			for (int i = 1; i < size; i++) sb.append(' ').append(cost[heap[i]]);
			return sb.toString();
		}

		private void build() {
			size = unsortedCount = indexCount;
			for (int i = 0; i < size; i++) heap[i] = position[i] = i;
		}

		private void copyCosts(final LongCollection costs) {
			final PrimitiveIterator.OfLong it = costs.iterator();
			for (int i = 0; i < indexCount; i++) cost[i] = it.nextLong();
			build();
		}

		private int compare(final long a, final long b) {
			if (comparator != null) return comparator.compare(a, b);
			return isDescendingOrder ? Long.compare(b, a) : Long.compare(a, b);
		}

		private void append(final int i, final long c) {
			cost[i] = c;
			heap[size] = i;
			position[i] = size++;
			unsortedCount++;
		}

		private void removeUnsorted(final int i) {
			final int p = position[i];
			position[i] = stamp;
			unsortedCount--;
			if (--size != p) {
				final int j = heap[size];
				heap[p] = j;
				position[j] = p;
			}
		}

		private boolean isActive(final int i) {
			final int p = position[i];
			return p >= 0 && p < size && heap[p] == i;
		}

		private boolean isRemoved(final int i) {
			return position[i] == stamp;
		}

		private boolean isUnseen(final int i) {
			return !isActive(i) && !isRemoved(i);
		}

		private void ensureHeapProperty() {
			final int log2N = 31 - Integer.numberOfLeadingZeros(size);
			final int heapifyCost = size * 2 - 2 * log2N;
			final int incrementalCost = unsortedCount <= 100 ? getIncrementalCostStrict() : getIncrementalCostApprox();
			if (heapifyCost < incrementalCost) heapify();
			else for (int p = size - unsortedCount; p < size; p++) siftUp(heap[p], p);
			unsortedCount = 0;
		}

		private int getIncrementalCostStrict() {
			int c = 0;
			final int sortedSize = size - unsortedCount;
			for (int i = 1; i <= unsortedCount; i++) c += 31 - Integer.numberOfLeadingZeros(sortedSize + i);
			return c;
		}

		private int getIncrementalCostApprox() {
			final int averageSize = size - unsortedCount + (unsortedCount >> 1);
			if (averageSize == 0) return 0;
			return unsortedCount * (31 - Integer.numberOfLeadingZeros(averageSize));
		}

		private void heapify() {
			for (int p = (size >> 1) - 1; p >= 0; p--) siftDown(heap[p], p, size);
		}

		private void siftUp(final int i, int p) {
			final long c = cost[i];
			while (p > 0) {
				final int q = (p - 1) >> 1;
				final int j = heap[q];
				if (compare(c, cost[j]) >= 0) break;
				heap[p] = j;
				position[j] = p;
				p = q;
			}
			heap[p] = i;
			position[i] = p;
		}

		private void siftDown(final int i, int p, final int n) {
			final long c = cost[i];
			final int half = n >> 1;
			while (p < half) {
				int q = (p << 1) + 1;
				if (q + 1 < n && compare(cost[heap[q]], cost[heap[q + 1]]) > 0) q++;
				final int j = heap[q];
				if (compare(c, cost[j]) <= 0) break;
				heap[p] = j;
				position[j] = p;
				p = q;
			}
			heap[p] = i;
			position[i] = p;
		}
	}
}
