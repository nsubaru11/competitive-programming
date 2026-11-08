import java.util.*;

public class A {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		for (char c : sc.nextLine().toCharArray()) {
			if (Character.isUpperCase(c)) {
				sb.append(Character.toLowerCase(c));
			} else if (Character.isLowerCase(c)) {
				sb.append(Character.toUpperCase(c));
			} else {
				sb.append(c);
			}
		}
		System.out.println(sb);
	}
}
