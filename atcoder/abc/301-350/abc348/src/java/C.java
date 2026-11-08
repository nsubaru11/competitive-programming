import java.util.*;


public class C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		HashMap<Integer, Integer> map = new HashMap<>();
		Integer col = null;
		Integer value = null;

		for (int i = 0; i < N; i++) {
			value = sc.nextInt();
			col = sc.nextInt();
			if (map.get(col) == null) {
				map.put(col, value);
			} else if (value <= map.get(col)) {
				map.put(col, value);
			}
		}

		TreeSet<Integer> set = new TreeSet<>();
		set.addAll(map.values());
		System.out.println(set.last());

		//ArrayList<Integer> list = new ArrayList<>(set);
		//int n = list.size();
		//System.out.println(list.get(n - 1));

	}
}
