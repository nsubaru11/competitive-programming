import java.util.*;

public class A {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		String s, t = sc.next();
		int cnt = 0;
		while (!(s = sc.next()).equals("END_OF_TEXT")) {
			if (s.equalsIgnoreCase(t)) cnt++;
		}
		System.out.println(cnt);
	}
}
