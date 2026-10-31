import java.util.TreeSet;

public class Tree {

	public static void main(String[] args) {
		int idx, from, to;
		boolean inclusive = true;
		idx = from = to = 0;

		TreeSet<Integer> ts = new TreeSet<>();

		ts.descendingSet();// 降順 -> Set		

		ts.first();// 最初の要素の取得 -> Integer
		ts.pollFirst();// 最初の要素の取得&削除 -> Integer

		ts.headSet(to);// 先頭の部分集合 -> Set
		ts.headSet(to, inclusive);// 先頭の部分集合 -> Set

		ts.lower(idx);// 未満 -> Set
		ts.floor(idx);// 以下 -> Set

		ts.subSet(from, to);// 中間の部分集合 -> Set
		ts.subSet(from, inclusive, to, inclusive);// 中間の部分集合 -> Set

		ts.ceiling(idx);// 以上 -> Set
		ts.higher(idx);// 超過 -> Set

		ts.tailSet(from);// 後尾の部分集合 -> Set
		ts.tailSet(from, inclusive);// 後尾の部分集合 -> Set

		ts.last();// 最後の要素の取得 -> Integer
		ts.pollLast();// 最後の要素の取得&削除 -> Integer

	}
}
