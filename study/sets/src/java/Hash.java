import java.util.HashSet;
import java.util.Set;

public class Hash {

	public static void main(String[] args) {
		int idx = 0;

		Set<Integer> s = new HashSet<>();
		HashSet<Integer> hs = new HashSet<>();

//		挿入順を保持するHashSet
//		LinkedHashSet<Integer> lhs = new LinkedHashSet<>();

		// Setに共通するメソッド
		s.add(idx);// 追加 -> boolean
		s.remove(idx);// 削除 -> boolean
		s.clear();// 一括削除 -> void

		s.contains(idx);// is含有 -> boolean
		s.isEmpty();// is空 -> boolean
		s.equals(hs);// is同等 -> boolean
		s.size();// 要素数 -> int

		s.addAll(hs);// 和集合 -> boolean
		s.removeAll(hs);// 差集合 -> boolean
		s.retainAll(hs);// 積集合 -> boolean

		s.removeIf(x -> x < idx);// 条件削除 -> boolean
		s.containsAll(hs);// is部分集合 -> boolean

		s.forEach(x -> hs.add(x));// action -> void
		s.toArray(new Integer[s.size()]);// 配列化 -> T[]
		s.toArray();// Object[]

	}
}
