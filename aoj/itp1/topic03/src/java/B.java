import java.util.*;

public class B {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		for (int x = sc.nextInt(), i = 1; x != 0; x = sc.nextInt(), i++) {
			if (i > 1) sb.append("\n");
			sb.append("Case ").append(i).append(": ").append(x);
		}
		System.out.println(sb);
	}
}
