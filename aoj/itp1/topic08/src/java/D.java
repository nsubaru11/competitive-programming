import java.util.*;

public class D {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		StringBuilder s = new StringBuilder(sc.next());
		String t = sc.next();
		s.append(s);
		System.out.println((s.toString().contains(t)) ? "Yes" : "No");
	}
}
