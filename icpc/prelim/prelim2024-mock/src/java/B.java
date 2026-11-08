import java.util.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int n = Integer.parseInt(sc.next());
			if (n == 0) {
				sc.close();
				return;
			}
			StringBuilder str = new StringBuilder("");
			int idx = 0;
			for (int i = 0; i < n; i++) {
				String s = sc.next();
				if (s.equals("INSERT")) {
					str.insert(idx, sc.next());
					idx++;
				} else if (s.equals("LEFT")) {
					idx = (idx > 0) ? idx - 1 : idx;
					sc.nextLine();
				} else if (s.equals("RIGHT")) {
					idx = (idx < str.length()) ? idx + 1 : idx;
					sc.nextLine();
				}
			}
			System.out.println(str);
		}
	}

}
