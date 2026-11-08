import java.util.*;

public class C {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		for (int x = sc.nextInt(), y = sc.nextInt(); x != 0 || y != 0; x = sc.nextInt(), y = sc.nextInt()) {
			if (x <= y) {
				sb.append(x).append(" ").append(y);
			} else {
				sb.append(y).append(" ").append(x);
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
