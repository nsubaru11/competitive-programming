import java.util.*;

public class Q {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int n = sc.nextInt();
			TreeMap<Integer, Integer> map = new TreeMap<>();
			if (n == 0) {
				sc.close();
				return;
			}

			for (int i = 1; i <= n; i++) {
				map.put(sc.nextInt(), i);
			}

			int ceiling = Integer.MAX_VALUE;
			if (map.ceilingKey(2023) != null) {
				ceiling = map.ceilingKey(2023);
			}
			int floor = Integer.MIN_VALUE;
			if (map.floorKey(2023) != null) {
				floor = map.floorKey(2023);
			}

			if (Math.abs(2023 - ceiling) > Math.abs(2023 - floor)) {
				System.out.println(map.get(floor));
			} else {
				System.out.println(map.get(ceiling));
			}
		}
	}

}
