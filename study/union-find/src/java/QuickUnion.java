
class QuickUnion {
	int[] root;

	// O(n)
	public QuickUnion(int size) {
		root = new int[size];
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
	public void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		if (rootX != rootY) {
			root[rootY] = rootX;
		}
	}

	// O(n)
	public boolean isConnected(int x, int  y) {
		return find(x) == find(y);
	}
}
