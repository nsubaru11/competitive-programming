import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Hash {

	public static void main(String[] args) {
		int key = 0;
		int value = 0;

		Map<Integer, Integer> hm = new HashMap<>();
		HashMap<Integer, Integer> hm2 = new HashMap<>();

//		挿入順を保持するHashMap
//		LinkedHashMap<Integer, Integer> lhm = new LinkedHashMap<>();

		// Mapに共通するメソッド
		hm.put(key, value);// keyとvalueをセット -> Integer
		hm.putAll(hm2);// 全てのマッピングをコピー -> void
		hm.putIfAbsent(key, value);// keyが未マッピングなら新たにマッピング -> Integer

		hm.clear();// 全て削除 -> void
		hm.remove(key);// 指定のkeyを削除 -> Integer
		hm.remove(key, value);// 指定のエントリーを削除 -> true

		hm.get(key);// keyにマッピングされているvalueを返す -> Integer
		hm.getOrDefault(key, 0);// マッピングされていなければデフォルト値を返す -> Integer

		hm.replace(key, value);// 置換 -> Integer
		hm.replace(key, value, value + 1);// 置換 -> Integer

		hm.containsKey(key);// is含有key -> boolean
		hm.containsValue(value);// is含有value -> boolean
		hm.equals(hm2);// is同等 -> boolean
		hm.isEmpty();// is空 -> boolean
		hm.size();// 要素数 -> int

		hm.entrySet();// すべてのエントリーを返す -> Entry
		for (Entry<Integer, Integer> e : hm.entrySet()) {
			e.getKey();
			e.getValue();
		}

		hm.keySet();// すべてのキーを返す -> Set
		for (int k : hm.keySet()) {
			hm.get(k);
		}

		hm.values();// すべてのバリューを返す -> List
		for (int v : hm.values()) {
			hm2.put(v, key);
		}

		hm.forEach((k, v) -> hm2.put(k * k, v * v));// action -> void

		// if (未マッピング) 第二引数をマッピング else 第三引数の関数の戻り値をマッピング -> Integer
		hm.merge(key, value, (ov, v) -> {
			return ov * v;
		});

		hm.compute(key, (k, v) -> k * v);// keyについて置換 -> Inetger
		hm.computeIfAbsent(key + 1, (k) -> k * k);// 未マッピングならcompute -> Integer
		hm.computeIfPresent(key, (k, v) -> k * v);// マッピング済ならcompute -> Integer

	}
}
