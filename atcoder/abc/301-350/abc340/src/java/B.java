import java.util.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<>();
		int q = Integer.parseInt(sc.next());
		for (int i = 0; i < q; i++) {
			int n = Integer.parseInt(sc.next());
			int k = Integer.parseInt(sc.next());
			if (n == 1) {
				list.add(k);
			} else {
				System.out.println(list.get(list.size() - k));
			}
		}

	}

}
