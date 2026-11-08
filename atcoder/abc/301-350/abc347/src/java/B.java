import java.util.*;

public class B {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.next();
		int len = S.length();
		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len - i + 1; j++) {
				if (j == 0) {
					String s = S.substring(0, i);
					set.add(s);
				} else if (S.substring(j, j + i) != null) {
					String s = S.substring(j, j + i);
					set.add(s);
				}
			}

		}
		System.out.println(set.size());
	}
}
