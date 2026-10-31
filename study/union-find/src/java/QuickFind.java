
class QuickFind {
	int[] root;

	// O(n)
	public QuickFind(int size) {
		root = new int[size];
		for (int i = 0; i < size; i++) {
			root[i] = i;
		}
	}

	// O(1)
	public int find(int x) {
		return root[x];
	}

	// O(n)
	public void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);

		if (rootX != rootY) {
			for (int i = 0; i < root.length; i++) {
				if (root[i] == rootY) {
					root[i] = rootX;
				}
			}
		}
	}

	// O(1)
	public boolean isConnected(int x, int  y) {
		return root[x] == root[y];
	}
}
