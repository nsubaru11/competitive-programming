import java.util.*;


public class C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.next();
		String T = sc.next();
		T = T.toLowerCase();
		if (T.endsWith("x") && S.contains("" + T.charAt(0)) && S.contains("" + T.charAt(1))) {

			for (int i = 0; i < S.length(); i++) {
				if (i == S.length() - 1) {
					System.out.println("No");
					return;
				}
				if (S.charAt(i) == T.charAt(0)) {
					for (int j = i + 1; j < S.length(); j++) {
						if (S.charAt(j) == T.charAt(1)) {
							System.out.println("Yes");
							return;
						}
						if (j == S.length() - 1) {
							System.out.println("No");
							return;
						}
					}
				}
			}

		} else if (S.contains("" + T.charAt(0)) && S.contains("" + T.charAt(1)) && S.contains("" + T.charAt(2))) {

			for (int i = 0; i < S.length(); i++) {
				if (i == S.length() - 2) {
					System.out.println("No");
					return;
				}
				if (S.charAt(i) == T.charAt(0)) {
					for (int j = i + 1; j < S.length(); j++) {
						if (j == S.length() - 1) {
							System.out.println("No");
							return;
						}
						if (S.charAt(j) == T.charAt(1)) {
							for (int k = j + 1; k < S.length(); k++) {
								if (S.charAt(k) == T.charAt(2)) {
									System.out.println("Yes");
									return;
								}
								if (k == S.length() - 1) {
									System.out.println("No");
									return;
								}
							}
						}
					}
				}
			}

		} else System.out.println("No");
	}
}
