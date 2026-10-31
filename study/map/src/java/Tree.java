import java.util.TreeMap;

public class Tree {

	public static void main(String[] args) {
		boolean inclusive = true;
		int num = 0;

		TreeMap<Integer, Integer> tm = new TreeMap<>();

		tm.descendingKeySet();// 全てのkeyを逆順で返す -> Set
		tm.descendingMap();// 全てのMapを逆順で返す -> Map

		tm.firstEntry();// 最初 -> Entry
		tm.firstKey();// 最初 -> Integer
		tm.pollFirstEntry();// 最初のEntryの取得&削除 -> Entry

		tm.headMap(num);// 先頭 -> Map
		tm.headMap(num, inclusive);// 先頭 -> Map

		tm.lowerEntry(num);// 未満 -> Entry
		tm.lowerKey(num);// 未満 -> Integer
		tm.floorEntry(num);// 未満 -> Entry
		tm.floorKey(num);// 未満 -> Integer

		tm.subMap(num, num + 5);// 中間 -> Map
		tm.subMap(num, inclusive, num + 10, inclusive);// 中間 -> Map

		tm.ceilingEntry(num);// 以上 -> Entry
		tm.ceilingKey(num);// 以上 -> Set
		tm.higherEntry(num);// 超過 -> Entry
		tm.higherKey(num);// 超過 -> Set

		tm.tailMap(num);// 後尾 -> Map
		tm.tailMap(num, inclusive);// 後尾 -> Map

		tm.lastEntry();// 最後 -> Entry
		tm.lastKey();// 最後 -> Set
		tm.pollLastEntry();// のEntryの取得&削除 -> Entry

	}
}
