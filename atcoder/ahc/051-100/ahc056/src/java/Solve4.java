import java.util.*;

import lib.io.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public final class Solve4 {

	// ------------------------ 定数 ------------------------
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final int[][] dij = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}}; // 下、左、右、上
	// private static final int[][] dij = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
	private static final FastScanner sc;
	private static final FastPrinter out;

	private static final int L = 1, U = 3, R = 2, D = 0;

	private static final int n, k, t;
	private static final int[] wall, point;
	private static final Dijkstra dijkstra;
	private static final long startTime, timeLimit;
	private static ArrayDeque<Integer> root;
	private static HashMap<Integer, Integer> color;

	static {
		// init
		startTime = System.currentTimeMillis();
		timeLimit = 1800;
		sc = new FastScanner();
		out = new FastPrinter();
		n = sc.nextInt();
		k = sc.nextInt();
		t = sc.nextInt();
		wall = new int[n * n];
		point = new int[k];
		for (int i = 0; i < n; i++) {
			wall[i] |= 1 << U;
			wall[n * (n - 1) + i] |= 1 << D;
			wall[i * n] |= 1 << L;
			wall[(i + 1) * n - 1] |= 1 << R;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1; j++) {
				char v = sc.nextChar();
				if (v == '0') continue;
				wall[i * n + j] |= 1 << R;
				wall[i * n + j + 1] |= 1 << L;
			}
		}
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n; j++) {
				char h = sc.nextChar();
				if (h == '0') continue;
				wall[i * n + j] |= 1 << D;
				wall[(i + 1) * n + j] |= 1 << U;
			}
		}
		for (int i = 0; i < k; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			point[i] = x * n + y;
		}
		dijkstra = new Dijkstra(n * n, n * n * 4);
		for (int ij = 0; ij < n * n; ij++) {
			for (int d = 0; d < 4; d++) {
				if ((wall[ij] >> d & 1) == 1) continue;
				dijkstra.addEdge(ij, ij + dij[d][0] * n + dij[d][1]);
			}
		}
	}

	// ------------------------ メインロジック ------------------------
	private static void solve() {
		// bfs
		ArrayList<Integer> path12 = new ArrayList<>(t);
		HashMap<Integer, Integer> color12 = new HashMap<>(n * n);
		solve12(path12, color12);

		// 出力準備
		int size = path12.size();
		int score1 = 1 + size;
		int score2 = color12.size() + k;
		int score3 = solve3();
		if (score3 <= score1 && score3 <= score2) {
			out2(new ArrayList<>(root), color, root.size());
		} else if (score1 <= score2) {
			out1(path12, size);
		} else {
			out2(path12, color12, size);
		}
	}

	private static int solve3() {
		for (int i = 0, prevPenalty = -1; ; i++) {
			long currentTime = System.currentTimeMillis();
			if (currentTime - startTime > timeLimit) break;
			int penalty = i < n * n ? i : n * n + (int) pow(1.1, i - (n * n));
			if (penalty == prevPenalty) continue;
			prevPenalty = penalty;
			dijkstra.setPenalty(penalty);
			for (int start = 0; start < k - 1; start++) {
				// Forward
				ArrayDeque<Integer> rootI = new ArrayDeque<>(t);
				HashMap<Integer, Integer> colorI = new HashMap<>(n * n);
				rootI.add(point[start]);
				colorI.put(point[start], 0);
				boolean[] visited = new boolean[n * n];
				for (int j = start; j < k - 1; j++) {
					int[] path = dijkstra.solve(point[j], point[j + 1], visited);
					ArrayList<Integer> reversePath = new ArrayList<>();
					int p = point[j + 1];
					while (path[p] != p) {
						reversePath.add(p);
						p = path[p];
					}
					for (int r = reversePath.size() - 1; r >= 0; r--) {
						int pos = reversePath.get(r);
						rootI.add(pos);
						colorI.putIfAbsent(pos, colorI.size());
						visited[pos] = true;
					}
				}
				for (int j = start - 1; j >= 0; j--) {
					int[] path = dijkstra.solve(point[j], point[j + 1], visited);
					int p = point[j + 1];
					while (path[p] != p) {
						p = path[p];
						rootI.addFirst(p);
						colorI.putIfAbsent(p, colorI.size());
						visited[p] = true;
					}
				}
				if (rootI.size() - 1 <= t && (root == null || color.size() > colorI.size())) {
					color = colorI;
					root = rootI;
				}
			}
		}
		return root == null ? Integer.MAX_VALUE : color.size() + k;
	}

	private static void solve12(final ArrayList<Integer> path, final HashMap<Integer, Integer> color) {
		color.put(point[0], 0);
		path.add(point[0]);
		int[] dest = new int[n * n];
		for (int k2 = 0; k2 < k - 1; k2++) {
			ArrayDeque<Integer> dq = new ArrayDeque<>();
			int from = point[k2], to = point[k2 + 1];
			fill(dest, -1);
			dest[from] = from;
			dq.add(from);
			outer:
			while (!dq.isEmpty()) {
				from = dq.poll();
				for (int d = 0; d < 4; d++) {
					if ((wall[from] >> d & 1) == 1) continue; // 方向`d`が壁の時
					int next = from + dij[d][0] * n + dij[d][1]; // 移動先
					if (dest[next] >= 0) continue; // 既に探索済みの時
					dest[next] = from;
					if (next == to) break outer; // 目的地の場合
					dq.add(next);
				}
			}
			ArrayList<Integer> root = new ArrayList<>();
			int p = to;
			while (dest[p] != p) {
				root.add(p);
				p = dest[p];
			}
			for (int i = root.size() - 1; i >= 0; i--) {
				int pos = root.get(i);
				path.add(pos);
				color.putIfAbsent(pos, color.size());
			}
		}
	}

	private static void out1(ArrayList<Integer> path, int size) {
		out.print(1, size, size - 1).println();
		for (int i = 0; i < n; i++) out.print('0').printRepeat(" 0", n - 1).println();
		for (int p = 0; p < size - 1; p++) {
			int cur = path.get(p);
			int nij = path.get(p + 1);
			int s = p + 1;
			char d;
			if (cur - 1 == nij) {
				d = 'L';
			} else if (cur - n == nij) {
				d = 'U';
			} else if (cur + 1 == nij) {
				d = 'R';
			} else {
				d = 'D';
			}
			out.print(0, p, 0, s, d).println();
		}
	}

	private static void out2(ArrayList<Integer> path, HashMap<Integer, Integer> color, int size) {
		out.print(color.size() + 1, k, size - 1).println();
		for (int i = 0, ij = 0; i < n; i++) {
			out.print(color.getOrDefault(ij++, 0));
			for (int j = 1; j < n; j++) {
				out.print("", color.getOrDefault(ij++, 0));
			}
			out.println();
		}
		for (int p = 0, i = 0; p < size - 1; p++) {
			int cur = path.get(p);
			int nij = path.get(p + 1);
			int curColor = color.get(cur);
			char d;
			if (cur - 1 == nij) {
				d = 'L';
			} else if (cur - n == nij) {
				d = 'U';
			} else if (cur + 1 == nij) {
				d = 'R';
			} else {
				d = 'D';
			}
			out.print(curColor, i, curColor, nij == point[i + 1] ? ++i : i, d).println();
		}
	}

	// ------------------------ main() 関数 ------------------------
	public static void main(final String[] args) {
		try {
			solve();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	// ------------------------ デバッグ用 ------------------------
	@SuppressWarnings("unused")
	private static void debug(final Object... args) {
		if (DEBUG) {
			out.flush();
			System.err.println(deepToString(args));
		}
	}

	@SuppressWarnings("unused")
	private static final class Dijkstra {
		// -------------- フィールド --------------
		private static final long INF = Long.MAX_VALUE;
		private final IndexedPriorityQueue ans;
		private final int[] dest, next, first;
		private final int v;
		private int e;
		private int penalty = 100;

		// -------------- コンストラクタ --------------

		/**
		 * コンストラクタ
		 *
		 * @param v 頂点数
		 * @param e 辺数の最大値
		 */
		public Dijkstra(final int v, final int e) {
			this(v, e, false);
		}

		/**
		 * コンストラクタ
		 *
		 * @param v     頂点数
		 * @param e     辺数の最大値
		 * @param isMax true の場合は最長経路を求める（最大値優先）、false の場合は最短経路を求める（最小値優先）
		 */
		public Dijkstra(final int v, final int e, final boolean isMax) {
			dest = new int[e];
			next = new int[e];
			first = new int[v];
			fill(first, -1);
			this.e = 0;
			this.v = v;
			ans = new IndexedPriorityQueue(v, isMax);
		}

		// -------------- グラフ構築 --------------

		/**
		 * 有向辺を追加する
		 *
		 * @param i 辺の始点（0-indexed）
		 * @param j 辺の終点（0-indexed）
		 */
		public void addEdge(final int i, final int j) {
			dest[e] = j;
			next[e] = first[i];
			first[i] = e;
			e++;
		}

		public void setPenalty(final int penalty) {
			this.penalty = penalty;
		}

		// -------------- 最短経路探索 --------------

		/**
		 * 始点 i から終点 j への最短経路の重みを返す
		 *
		 * @param i 始点
		 * @param j 終点
		 * @return 始点から終点への最短経路の重み（経路が存在しない場合は INF）
		 */
		public int[] solve(final int i, final int j, final boolean[] visited) {
			int[] path = new int[v];
			fill(path, -1);
			path[i] = i;
			ans.clear();
			ans.push(i, 0);
			while (!ans.isEmpty()) {
				long c = ans.peek();
				int from = ans.pollNode();
				for (int e = first[from]; e != -1; e = next[e]) {
					int to = dest[e];
					long cost = 1;
					if (!visited[to]) cost += penalty;
					if (ans.relax(to, c + cost)) {
						path[to] = from;
					}
				}
			}
			return path;
		}

		// -------------- 内部クラス：IndexedPriorityQueue --------------

		/**
		 * Dijkstra法特化インデックス付き優先度キュー
		 * <p>
		 * 遅延ヒープ構築により効率的な探索を実現する内部クラス。
		 */
		private static final class IndexedPriorityQueue {
			// -------------- フィールド --------------
			private final boolean isDescendingOrder;
			private final long[] cost;
			private final int[] heap, position;
			private int size, unsortedCount;

			// -------------- コンストラクタ --------------

			/**
			 * コンストラクタ
			 *
			 * @param n                 頂点数
			 * @param isDescendingOrder true の場合は最大値優先（降順）、false の場合は最小値優先（昇順）
			 */
			public IndexedPriorityQueue(final int n, final boolean isDescendingOrder) {
				this.isDescendingOrder = isDescendingOrder;
				cost = new long[n];
				heap = new int[n];
				position = new int[n];
				fill(position, -2);
				size = 0;
				unsortedCount = 0;
			}

			// -------------- 公開メソッド --------------

			/**
			 * 要素を追加する
			 *
			 * @param node 追加するノード
			 * @param c    追加するノードのコスト
			 */
			public void push(final int node, long c) {
				if (position[node] != -2) throw new IllegalArgumentException();
				if (isDescendingOrder) c = -c;
				cost[node] = c;
				heap[size] = node;
				position[node] = size;
				size++;
				unsortedCount++;
			}

			/**
			 * relax操作（Dijkstra法などで使用）
			 *
			 * @param node ノード
			 * @param cost 新しいコスト
			 * @return 更新が行われた場合はtrue
			 */
			public boolean relax(final int node, long cost) {
				if (position[node] == -1) return false;
				if (position[node] == -2) {
					push(node, cost);
					return true;
				}
				if (isDescendingOrder) cost = -cost;
				if (this.cost[node] > cost) {
					this.cost[node] = cost;
					siftUp(node, position[node]);
					return true;
				}
				return false;
			}

			/**
			 * ヒープの先頭要素のコストを取得する
			 *
			 * @return ヒープの先頭要素のコスト（昇順時は最小、降順時は最大）
			 */
			public long peek() {
				if (isEmpty()) throw new NoSuchElementException();
				if (unsortedCount > 0) ensureHeapProperty();
				return isDescendingOrder ? -cost[heap[0]] : cost[heap[0]];
			}

			/**
			 * ヒープの先頭ノードを削除し、そのノードを返す
			 *
			 * @return 削除されたノード（昇順時は最小コスト、降順時は最大コストのノード）
			 */
			public int pollNode() {
				if (isEmpty()) throw new NoSuchElementException();
				if (unsortedCount > 0) ensureHeapProperty();
				int node = heap[0];
				position[node] = -1;
				size--;
				if (size > 0) {
					int lastNode = heap[size];
					siftDown(lastNode, 0);
				}
				return node;
			}

			/**
			 * 指定したノードのコストを取得する（存在しない場合はデフォルト値を返す）
			 *
			 * @param node         対象ノード
			 * @param defaultValue デフォルト値
			 * @return コストまたはデフォルト値
			 */
			public long getCostOrDefault(final int node, final long defaultValue) {
				return position[node] == -2 ? defaultValue : isDescendingOrder ? -cost[node] : cost[node];
			}

			/**
			 * ヒープをクリアする
			 */
			public void clear() {
				size = 0;
				unsortedCount = 0;
				fill(position, -2);
			}

			/**
			 * ヒープが空かどうかを判定する
			 *
			 * @return 空の場合はtrue
			 */
			public boolean isEmpty() {
				return size == 0;
			}

			// -------------- ヒープ構築（遅延評価） --------------

			/**
			 * 遅延評価された未ソート要素をヒープ化し、ヒーププロパティを復元する。
			 * <p>
			 * このメソッドは、未ソート要素が存在する場合に最適なアルゴリズムを自動選択して実行します。
			 * <p><b>分岐点の決定：</b>
			 * 両アルゴリズムの最大比較回数を計算し、コストが小さい方を実行する。
			 * (heapifyCost < incrementalCost なら heapify を選択)
			 */
			private void ensureHeapProperty() {
				int log2N = 31 - Integer.numberOfLeadingZeros(size);
				int heapifyCost = size * 2 - 2 * log2N;
				int incrementalCost = unsortedCount <= 100 ? getIncrementalCostStrict() : getIncrementalCostApprox();
				if (heapifyCost < incrementalCost) {
					heapify();
				} else {
					for (int i = size - unsortedCount; i < size; i++) siftUp(heap[i], i);
				}
				unsortedCount = 0;
			}

			/**
			 * インクリメンタル構築の最大比較回数を厳密に計算する。
			 *
			 * @return 最大比較回数の合計
			 */
			private int getIncrementalCostStrict() {
				int totalCost = 0;
				int sortedSize = size - unsortedCount;
				for (int i = 1; i <= unsortedCount; i++) {
					int currentHeapSize = sortedSize + i;
					int depth = 31 - Integer.numberOfLeadingZeros(currentHeapSize);
					totalCost += depth;
				}
				return totalCost;
			}

			/**
			 * インクリメンタル構築の最大比較回数を高速に近似計算する。
			 * <p>コスト ≈ k * floor(log₂(平均ヒープサイズ))
			 *
			 * @return 最大比較回数の近似値
			 */
			private int getIncrementalCostApprox() {
				int sortedSize = size - unsortedCount;
				int avgHeapSize = sortedSize + (unsortedCount >> 1);
				if (avgHeapSize == 0) return 0;
				int depthOfAvgSize = 31 - Integer.numberOfLeadingZeros(avgHeapSize);
				return unsortedCount * depthOfAvgSize;
			}

			/**
			 * Bottom-up heapify (Floyd's algorithm)
			 */
			private void heapify() {
				for (int i = (size >> 1) - 1; i >= 0; i--) siftDown(heap[i], i);
			}

			// -------------- ヒープ操作（基本） --------------

			/**
			 * siftUp操作
			 *
			 * @param node 移動させるノード
			 * @param i    ノードの現在位置
			 */
			private void siftUp(final int node, int i) {
				long c = cost[node];
				while (i > 0) {
					int j = (i - 1) >> 1;
					int parent = heap[j];
					if (c >= cost[parent]) break;
					heap[i] = parent;
					position[parent] = i;
					i = j;
				}
				heap[i] = node;
				position[node] = i;
			}

			/**
			 * siftDown操作
			 *
			 * @param node 移動させるノード
			 * @param i    ノードの現在位置
			 */
			private void siftDown(final int node, int i) {
				long c = cost[node];
				int half = size >> 1;
				while (i < half) {
					int child = (i << 1) + 1;
					child += child + 1 < size && cost[heap[child]] > cost[heap[child + 1]] ? 1 : 0;
					int childNode = heap[child];
					if (c <= cost[childNode]) break;
					heap[i] = childNode;
					position[childNode] = i;
					i = child;
				}
				heap[i] = node;
				position[node] = i;
			}
		}
	}
}
