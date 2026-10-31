import java.util.BitSet;
import java.util.function.BiFunction;

@SuppressWarnings("unused")
public class SegmentTreeArray {
	private final int n, size;
	private final int[] tree;
	private final BiFunction<Integer, Integer, Integer> bf;
	private final BitSet isUpdate;

	public SegmentTreeArray(int n, BiFunction<Integer, Integer, Integer> bf) {
		this.n = n;
		size = n == 1 ? 0 : Integer.highestOneBit((n - 1) << 1) - 1;
		tree = new int[size + n];
		this.bf = bf;
		isUpdate = new BitSet(size + n);
	}

	public void set(int i, int e) {
		int idx = size + i;
		tree[idx] = e;
		isUpdate.set(idx);
	}

	public int query(int l, int r) {
		int h = size + 1;
		l += size;
		r += size;
		while (h > 0) {
			if ((l & 1) == 0) {
				tree[(l - 1) >> 1] = tree[l];
			}
			if ((r & 1) == 1) {
				tree[(r - 1) >> 1] = tree[r];
			}
			for (int i = l ^ 1; i < (r ^ 0); i = isUpdate.nextSetBit(i)) {

			}
			h >>= 1;
		}
		return tree[0];
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 1; i < size; i <<= 1) {
			s += "[" + tree[i - 1];
			for (int j = 1; j < i; j++) {
				s += ", " + tree[i - 1 + j];
			}
			s += "]\n";
		}
		s += "[" + tree[size];
		for (int i = size + 1; i < size + n; i++) {
			s += ", " + tree[i - 1];
		}
		s += "]\n";
		return s;
	}

}
