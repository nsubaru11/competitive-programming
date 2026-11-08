import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.next();
		boolean fOutput = true;

		for (int i = 0; i < S.length(); i++) {

			if (S.charAt(i) == '|') {
				fOutput = !fOutput;

			} else if (fOutput == true) {
				System.out.print(S.charAt(i));
			}
		}
	}
}
