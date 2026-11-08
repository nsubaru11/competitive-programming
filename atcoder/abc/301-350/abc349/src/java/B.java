import java.util.*;


public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.next();

		if (S.length() == 1) {
			System.out.println("No");
			return;
		}

		HashMap<Character, Integer> counts = new HashMap<>();
		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			if (counts.get(c) == null) {
				counts.put(c, 1);
			} else {
				counts.put(c, counts.get(c) + 1);
			}
		}

		int[] n = new int[101];
		for (char c : counts.keySet()) {
			int count = counts.get(c);
			n[count] += 1;
			if (n[count] > 2) {
				System.out.println("No");
				return;
			}
		}

		for (char c : counts.keySet()) {
			int count = counts.get(c);
			if (n[count] == 1) {
				System.out.println("No");
				return;
			}
		}
		System.out.println("Yes");
	}
}
