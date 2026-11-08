import java.util.*;


public class B2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> list = new ArrayList<>();
		for (; ; ) {
			int x = sc.nextInt();
			list.add(x);
			if (x == 0) {
				break;
			}
		}
		for (int i = list.size() - 1; i >= 0; i--) {
			System.out.println(list.get(i));
		}
	}
}
