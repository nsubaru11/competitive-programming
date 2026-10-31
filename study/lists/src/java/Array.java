import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class Array {

	public static void main(String[] args) {
		int idx = 0;
		Integer num = 0;

		ArrayList<Integer> al = new ArrayList<>();
		ArrayList<Integer> al2 = new ArrayList<>();

//		// LinkedListと共通メソッド
		al.add(num);// 追加 -> boolean
		al.add(num, idx);// 場所指定、追加 -> void
		al.addAll(al2);// 全て追加 -> boolean
		al.addAll(idx, al2);// 場所指定、全て追加 -> boolean
		al.set(idx, num);// 場所指定、置換 -> Integer

		al.clear();// すべて削除 -> void
		al.remove(idx);// 場所指定、削除 -> Integer
		al.remove(num);// 最も最初の指定要素削除 -> boolean
		al.removeAll(al2);// 共通要素削除 -> boolean
		al.removeIf(x -> x < 10);// 条件一致要素削除 -> boolean

		al.ensureCapacity(num);// サイズの確保 -> void
		al.trimToSize();// サイズを現在の要素数で切り詰め -> void

		al.contains(num);// is含有 -> boolean
		al.containsAll(al2);// is部分集合 -> boolean
		al.isEmpty();// is空 -> boolean
		al.equals(al2);// is同等 -> boolean

		al.replaceAll(x -> x * x);// 全て置換 -> void
		al.forEach(x -> al2.add(x));// action -> void

		al.get(idx);// 値取得 -> Integer
		al.indexOf(num);// index取得 -> int
		al.lastIndexOf(num);// lastIndex取得 -> int
		al.size();// 要素数 -> int

		al.retainAll(al2);// 共通の要素を保持 -> boolean
		al.subList(idx, idx + 5);// 部分順序列 -> List
		al.toArray(new Integer[10]);// 配列化 -> T[]

		// sort -> void
		al.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				if (i1 % 10 > i2 % 10)
					return 1;
				if (i1 % 10 < i2 % 10)
					return -1;
				return (i1 < i2) ? -1 : (i1 > i1) ? 1 : 0;
			}
		});

		// iterator -> Iterator
		Iterator<Integer> i = al.iterator();
		while (i.hasNext()) {
			al2.add(i.next() * 2);
			i.remove();
		}

	}
}
