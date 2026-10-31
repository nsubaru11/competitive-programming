import java.util.Iterator;
import java.util.LinkedList;

public class Linked {

	public static void main(String[] args) {
		Integer num = 0;

		LinkedList<Integer> ll = new LinkedList<>();
		LinkedList<Integer> ll2 = new LinkedList<>();

		ll.addFirst(num);// 先頭に追加 -> void
		ll.addLast(num);// 後尾に追加 -> void

		ll.element();// 先頭を取得 -> Integer
		ll.getFirst();// 先頭を取得 -> Integer
		ll.getLast();// 後尾を取得 -> Integer

		ll.offer(num);// 後尾に追加 -> boolean
		ll.offerFirst(num);// 先頭に追加 -> boolean
		ll.offerLast(num);// 後尾に追加 -> boolean

		ll.peek();// 先頭を取得 -> Integer
		ll.peekFirst();// 先頭を取得 -> Integer
		ll.peekLast();// 後尾を取得 -> Integer

		ll.poll();// 先頭を取得&削除 -> Integer
		ll.pollFirst();// 先頭を取得&削除 -> Integer
		ll.pollLast();// 後尾を取得&削除 -> Integer

		ll.pop();// 先頭を取得 -> Integer
		ll.push(num);// 先頭に追加 -> void

		ll.remove();// 先頭を取得&削除 -> Integer
		ll.removeFirst();// 先頭を取得&削除 -> Integer
		ll.removeFirstOccurrence(num);// 最初に現れる指定の値を削除 -> boolean
		ll.removeLast();// 先頭を取得&削除 -> Integer
		ll.removeLastOccurrence(num);// 最後に現れる指定の値を削除 -> boolean

		// 逆順Iterator -> Iterator
		Iterator<Integer> i = ll.descendingIterator();
		while (i.hasNext()) {
			ll2.add(i.next() * 2);
			i.remove();
		}

	}
}
