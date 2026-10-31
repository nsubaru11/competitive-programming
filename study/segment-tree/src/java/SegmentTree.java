import java.util.Arrays;

public class SegmentTree {
	private SegmentTree child;
	private int[] node;
	private int n, h, size;

	public SegmentTree(int n) {
		this.n = 1;
		this.h = 0;
		this.size = Integer.SIZE - Integer.numberOfLeadingZeros(n);
		this.node = new int[this.n];
		child = new SegmentTree(n, h + 1, size);
	}

	private SegmentTree(int n, int h, int size) {
		this.size = size;
		this.h = h;
		if (1 << h < n) {
			child = new SegmentTree(n, h + 1, size);
			this.n = (child.n + 1) >> 1;
		} else {
			this.n = n;
		}
		node = new int[this.n];
	}

	public int update(int i, int e) {
		if (child == null) {
			return node[i] += e;
		} else {
			int h2 = size - h;
			int j = i / (1 << h2);
			return node[j] = Math.max(node[j], child.update(i, e));
		}
	}

	@Override
	public String toString() {
//		if (child == null) return h + ", " + n + ": " + Arrays.toString(node);
//		return h + ", " + n + ": " + Arrays.toString(node) + "\n" + child.toString();
		if (child == null) return Arrays.toString(node);
		return Arrays.toString(node) + "\n" + child.toString();
	}
}
