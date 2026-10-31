import java.util.BitSet;

public class BitSets {

	public static void main(String[] args) {
		boolean value = true;
		int idx, from, to;
		idx = from = to = 0;

		BitSet bs = new BitSet(100);
		BitSet bs2 = new BitSet(100);

		bs.set(idx);// Sets to true. -> void
		bs.set(idx, value);// Sets to the specified value. -> void
		bs.set(from, to);// Sets to true.
		bs.set(from, to, value);// Sets to the specified value. -> void

		bs.length();// last index of true bit + 1 -> int
		bs.size();// the size of this BitSet -> int
		bs.cardinality();// the num of true bits -> int

		bs.clear();// Sets to false. -> void
		bs.clear(idx);// Sets to false. -> void
		bs.clear(from, to);// Sets to false. -> void

		bs.flip(idx);// flip the value -> void
		bs.flip(from, to);// flip the value -> void

		bs.get(idx);// get the value -> boolean
		bs.get(from, to);// get new BitSet -> BitSet

		bs.isEmpty();// true if all bits are false -> boolean
		bs.equals(bs2);// -> boolean
		bs.intersects(bs2);// whether this BitSet intersects the specified BitSet -> boolean
		bs.clone();// -> BitSet

		bs.nextSetBit(idx);// next true bit idx -> int
		bs.nextClearBit(idx);// next false bit idx -> int
		bs.previousSetBit(idx);// previous true bit idx -> int
		bs.previousClearBit(idx);// previous false bit idx -> int

		bs.and(bs2);// and
		bs.andNot(bs2);// andNot
		bs.or(bs2);// or
		bs.xor(bs2);// xor

	}
}
