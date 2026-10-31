
class RankedQuickUnion {
	int[] root;
	int[] rank;

	// O(n)
	public RankedQuickUnion(int size) {
		root = new int[size];
		rank = new int[size];
		for (int i = 0; i < size; i++) {
			root[i] = i;
		}
	}

	// O(n)
	public int find(int x) {
		while (x != root[x]) {
			x = root[x];
		}
		return x;
	}

	// O(n)
	// ランク = たどるノードの数
	public void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		if (rootX != rootY) {
			if (rank[rootX] > rank[rootY]) {
				root[rootY] = rootX;
			} else if (rank[rootX] < rank[rootY]) {
				root[rootX] = rootY;
			} else {
				root[rootY] = rootX;
				rank[rootX] += 1;
			}
		}
	}

	// O(n)
	public boolean isConnected(int x, int  y) {
		return find(x) == find(y);
	}
}
