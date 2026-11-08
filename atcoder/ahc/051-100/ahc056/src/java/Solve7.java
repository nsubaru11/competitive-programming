import java.util.*;

import lib.io.*;

import static java.util.Arrays.*;

public final class Solve7 {
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
	private static final long startTime, timeLimit;

	static {
		// init
		startTime = System.currentTimeMillis();
		timeLimit = 1900;
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
	}

	// ------------------------ メインロジック ------------------------
	private static void solve() {
		Solution sol1 = solveMethod1();
		Solution sol2 = solveMethod2Greedy();
		Solution sol3 = solveMethod3PenaltyDijkstra();

		Solution best = sol1;
		if (sol2.score < best.score) best = sol2;
		if (sol3.score < best.score) best = sol3;

		best.output();
	}

	// 解法1: 色1個 + ステップ数を状態に
	private static Solution solveMethod1() {
		ArrayList<Integer> path = new ArrayList<>(t);
		path.add(point[0]);

		int[] dest = new int[n * n];
		for (int k2 = 0; k2 < k - 1; k2++) {
			ArrayDeque<Integer> dq = new ArrayDeque<>();
			int from = point[k2], to = point[k2 + 1];
			Arrays.fill(dest, -1);
			dest[from] = from;
			dq.add(from);

			while (!dq.isEmpty()) {
				from = dq.poll();
				if (from == to) break;

				for (int d = 0; d < 4; d++) {
					if ((wall[from] >> d & 1) == 1) continue;
					int next = from + dij[d][0] * n + dij[d][1];
					if (dest[next] >= 0) continue;
					dest[next] = from;
					dq.add(next);
				}
			}

			ArrayList<Integer> segment = new ArrayList<>();
			int p = to;
			while (dest[p] != p) {
				segment.add(p);
				p = dest[p];
			}
			for (int i = segment.size() - 1; i >= 0; i--) {
				path.add(segment.get(i));
			}
		}

		return new Solution(1, path.size(), path, null);
	}

	// 解法2: 貪欲に最短経路を選択
	private static Solution solveMethod2Greedy() {
		ArrayList<Integer> path = new ArrayList<>(t);
		HashMap<Integer, Integer> color = new HashMap<>();
		path.add(point[0]);
		color.put(point[0], 0);

		int[] dest = new int[n * n];
		for (int k2 = 0; k2 < k - 1; k2++) {
			ArrayDeque<Integer> dq = new ArrayDeque<>();
			int from = point[k2], to = point[k2 + 1];
			Arrays.fill(dest, -1);
			dest[from] = from;
			dq.add(from);

			while (!dq.isEmpty()) {
				from = dq.poll();
				if (from == to) break;

				for (int d = 0; d < 4; d++) {
					if ((wall[from] >> d & 1) == 1) continue;
					int next = from + dij[d][0] * n + dij[d][1];
					if (dest[next] >= 0) continue;
					dest[next] = from;
					dq.add(next);
				}
			}

			ArrayList<Integer> segment = new ArrayList<>();
			int p = to;
			while (dest[p] != p) {
				segment.add(p);
				p = dest[p];
			}
			for (int i = segment.size() - 1; i >= 0; i--) {
				int pos = segment.get(i);
				path.add(pos);
				color.putIfAbsent(pos, color.size());
			}
		}

		return new Solution(color.size(), k, path, color);
	}

	// 解法3: ペナルティ付きダイクストラ
	private static Solution solveMethod3PenaltyDijkstra() {
		Dijkstra dijkstra = new Dijkstra(n * n, n * n * 4);
		for (int ij = 0; ij < n * n; ij++) {
			for (int d = 0; d < 4; d++) {
				if ((wall[ij] >> d & 1) == 1) continue;
				dijkstra.addEdge(ij, ij + dij[d][0] * n + dij[d][1]);
			}
		}

		// スタート地点を中心からの距離でソート
		int centerI = n / 2, centerJ = n / 2;
		Integer[] sortedStarts = new Integer[k];
		for (int i = 0; i < k; i++) sortedStarts[i] = i;
		Arrays.sort(sortedStarts, (a, b) -> {
			int distA = Math.abs(point[a] / n - centerI) + Math.abs(point[a] % n - centerJ);
			int distB = Math.abs(point[b] / n - centerI) + Math.abs(point[b] % n - centerJ);
			return Integer.compare(distA, distB);
		});

		ArrayDeque<Integer> bestRoot = null;
		HashMap<Integer, Integer> bestColor = null;
		int bestScore = Integer.MAX_VALUE;

		// フェーズ1: 粗い探索（0-30%）
		long phase1End = startTime + (long) (timeLimit * 0.3);
		int[] coarsePenalties = {n / 2, n, 2 * n, 3 * n, 5 * n, 10 * n, 20 * n};
		int bestPenalty = n;

		for (int penalty : coarsePenalties) {
			if (System.currentTimeMillis() > phase1End) break;

			dijkstra.setPenalty(penalty);

			for (int idx = 0; idx < Math.min(3, k); idx++) {
				ArrayDeque<Integer> rootI = buildPath(dijkstra, sortedStarts[idx]);
				if (rootI == null || rootI.size() - 1 > t) continue;

				HashMap<Integer, Integer> colorI = new HashMap<>();
				for (int cell : rootI) {
					colorI.putIfAbsent(cell, colorI.size());
				}

				int score = colorI.size() + k;
				if (score < bestScore) {
					bestScore = score;
					bestRoot = new ArrayDeque<>(rootI);
					bestColor = new HashMap<>(colorI);
					bestPenalty = penalty;
				}
			}
		}

		// フェーズ2: 細かい探索（30-80%）
		long phase2End = startTime + (long) (timeLimit * 0.8);
		int searchRadius = Math.max(bestPenalty / 2, n);
		int minP = Math.max(1, bestPenalty - searchRadius);
		int maxP = bestPenalty + searchRadius;
		int step = Math.max(1, searchRadius / 20);

		for (int p = minP; p <= maxP; p += step) {
			if (System.currentTimeMillis() > phase2End) break;

			dijkstra.setPenalty(p);

			int numStarts = (System.currentTimeMillis() - startTime < timeLimit * 0.5) ? k : Math.max(k / 2, 1);

			for (int idx = 0; idx < numStarts; idx++) {
				if (System.currentTimeMillis() > phase2End) break;

				ArrayDeque<Integer> rootI = buildPath(dijkstra, sortedStarts[idx]);
				if (rootI == null || rootI.size() - 1 > t) continue;

				HashMap<Integer, Integer> colorI = new HashMap<>();
				for (int cell : rootI) {
					colorI.putIfAbsent(cell, colorI.size());
				}

				int score = colorI.size() + k;
				if (score < bestScore) {
					bestScore = score;
					bestRoot = new ArrayDeque<>(rootI);
					bestColor = new HashMap<>(colorI);
				}
			}
		}

		// フェーズ3: ファインチューニング（80-100%）
		Random rand = new Random(System.currentTimeMillis());
		int fineRadius = Math.max(step, n / 4);

		while (System.currentTimeMillis() - startTime < timeLimit) {
			int p;
			if (rand.nextDouble() < 0.8) {
				p = bestPenalty + rand.nextInt(2 * fineRadius + 1) - fineRadius;
				p = Math.max(1, p);
			} else {
				p = rand.nextInt(20 * n) + 1;
			}

			dijkstra.setPenalty(p);
			int startIdx = rand.nextInt(k);

			ArrayDeque<Integer> rootI = buildPath(dijkstra, sortedStarts[startIdx]);
			if (rootI == null || rootI.size() - 1 > t) continue;

			HashMap<Integer, Integer> colorI = new HashMap<>();
			for (int cell : rootI) {
				colorI.putIfAbsent(cell, colorI.size());
			}

			int score = colorI.size() + k;
			if (score < bestScore) {
				bestScore = score;
				bestRoot = new ArrayDeque<>(rootI);
				bestColor = new HashMap<>(colorI);
			}
		}

		if (bestRoot == null) return solveMethod2Greedy();

		return new Solution(bestColor.size(), k, new ArrayList<>(bestRoot), bestColor);
	}

	// 指定したスタート地点から経路を構築
	private static ArrayDeque<Integer> buildPath(Dijkstra dijkstra, int start) {
		ArrayDeque<Integer> rootI = new ArrayDeque<>();
		rootI.add(point[start]);
		boolean[] visited = new boolean[n * n];
		visited[point[start]] = true;

		// Forward
		for (int j = start; j < k - 1; j++) {
			int[] path = dijkstra.solve(point[j], point[j + 1], visited);
			if (path[point[j + 1]] == -1) return null;

			ArrayList<Integer> reversePath = new ArrayList<>();
			int p = point[j + 1];
			while (path[p] != p) {
				reversePath.add(p);
				p = path[p];
			}
			for (int r = reversePath.size() - 1; r >= 0; r--) {
				int pos = reversePath.get(r);
				rootI.add(pos);
				visited[pos] = true;
			}
		}

		// Backward
		for (int j = start - 1; j >= 0; j--) {
			int[] path = dijkstra.solve(point[j], point[j + 1], visited);
			if (path[point[j + 1]] == -1) return null;

			int p = point[j + 1];
			while (path[p] != p) {
				p = path[p];
				rootI.addFirst(p);
				visited[p] = true;
			}
		}

		return rootI;
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

	// Solutionクラス
	private static class Solution {
		int colorCount;
		int stateCount;
		ArrayList<Integer> path;
		HashMap<Integer, Integer> color;
		int score;

		Solution(int colorCount, int stateCount, ArrayList<Integer> path, HashMap<Integer, Integer> color) {
			this.colorCount = colorCount;
			this.stateCount = stateCount;
			this.path = path;
			this.color = color;
			this.score = colorCount + stateCount;
		}

		void output() {
			if (color == null) {
				// 解法1
				out.print(1, path.size(), path.size() - 1).println();
				for (int i = 0; i < n; i++) {
					out.print('0').printRepeat(" 0", n - 1).println();
				}
				for (int p = 0; p < path.size() - 1; p++) {
					int cur = path.get(p);
					int nij = path.get(p + 1);
					char d = getDirection(cur, nij);
					out.print(0, p, 0, p + 1, d).println();
				}
			} else {
				// 解法2, 3
				out.print(colorCount, stateCount, path.size() - 1).println();
				for (int i = 0, ij = 0; i < n; i++) {
					out.print(color.getOrDefault(ij++, 0));
					for (int j = 1; j < n; j++) {
						out.print("", color.getOrDefault(ij++, 0));
					}
					out.println();
				}
				for (int p = 0, state = 0; p < path.size() - 1; p++) {
					int cur = path.get(p);
					int nij = path.get(p + 1);
					int curColor = color.get(cur);
					char d = getDirection(cur, nij);
					int nextState = (state + 1 < k && nij == point[state + 1]) ? state + 1 : state;
					out.print(curColor, state, curColor, nextState, d).println();
					state = nextState;
				}
			}
		}

		char getDirection(int cur, int next) {
			if (cur - 1 == next) return 'L';
			if (cur - n == next) return 'U';
			if (cur + 1 == next) return 'R';
			return 'D';
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
