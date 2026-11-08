import java.util.*;

public class B {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		while (true) {
			String s = sc.next();
			if (s.equals("0")) break;
			int sum = 0;
			for (char c : s.toCharArray()) {
				sum += c - '0';
			}
			sb.append(sum).append("\n");
		}
		System.out.print(sb);
	}
}
