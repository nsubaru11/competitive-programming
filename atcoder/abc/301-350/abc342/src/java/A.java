import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String s = sc.next();
		if (s.charAt(0) == s.charAt(1)) {
			for (int i = 2; i < s.length(); i++) {
				if (s.charAt(i) != s.charAt(0)) {
					System.out.println(i + 1);
					return;
				}
			}
		} else {
			if (s.charAt(0) == s.charAt(2)) {
				System.out.println(2);
				return;
			} else if (s.charAt(1) == s.charAt(2)) {
				System.out.println(1);
			}
		}
	}
}
