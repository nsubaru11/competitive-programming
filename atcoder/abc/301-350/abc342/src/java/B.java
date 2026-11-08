import java.util.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = Integer.parseInt(sc.next());
		HashMap<Integer, Integer> p = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int pi = Integer.parseInt(sc.next());
			p.put(pi, i);
		}
		int q = Integer.parseInt(sc.next());
		for (int i = 0; i < q; i++) {
			int a = Integer.parseInt(sc.next());
			int b = Integer.parseInt(sc.next());
			if (p.get(a) > p.get(b)) {
				System.out.println(b);
			} else {
				System.out.println(a);
			}
		}
	}

}
