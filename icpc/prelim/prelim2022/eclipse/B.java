import java.util.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int n = Integer.parseInt(sc.next());
			TreeMap<Integer, TreeSet<Integer>> player = new TreeMap<>(); // 人番号 -> カード番号
			if (n == 0) {
				sc.close();
				return;
			}
			for (int i = 0; i < n; i++) {
				int a = Integer.parseInt(sc.next());
				int b = Integer.parseInt(sc.next());
				if (a != b) {
					TreeSet<Integer> card = new TreeSet<>();
					card.add(a);
					card.add(b);
					player.put(i, card);
				}
			}

			// solve
			int count = 0;
			while (player.size() > 0) {

				// 自分の次の人に自分の一番小さいカードを引いて貰う。
				for (int p: player.keySet()) {
					if (player.higherKey(p) != null) {

						// 自分のカードの集合と次の人のカードの集合を取り出す。
						TreeSet<Integer> c1 = player.get(p);
						TreeSet<Integer> c2 = player.get(player.higherKey(p));

						/* 次の人のカードに自分の一番低いカードが含まれているかの判定。
						 * 含まれているならば両方からそのカードを取り除く。
						 * 含まれていなければ自分から取り除き次の人の集合に加える。
						 */
						if (c2.contains(c1.first())) {
							c2.remove(c1.first());
							c1.pollFirst();
						} else {
							c2.add(c1.pollFirst());
						}
						count++;
					}
				}

				// カードがなくなった人をplayerから取り除く。
				int key = player.firstKey();
				int size = player.size();
				for (int i = 0; i < size - 1; i++) {
					if (player.get(key).isEmpty()) {
						player.remove(key);
					}
					key = player.higherKey(key);
				}

				//　次に最後の人のカードを、今いる最初の人に移す作業をする。
				TreeSet<Integer> last = player.get(player.lastKey());
				TreeSet<Integer> first = player.get(player.firstKey());
				if (first.contains(last.first())) {
					first.remove(last.first());
					last.pollFirst();
				} else {
					first.add(last.pollFirst());
				}
				count++;

				// 最後の人と最初の人がカードを持ってい無ければ、playerから取り除く。
				if (last.isEmpty()) {
					player.remove(player.lastKey());
				}
				if (first.isEmpty()) {
					player.remove(player.firstKey());
				}
			}
			System.out.println(count);
		}
	}

}
