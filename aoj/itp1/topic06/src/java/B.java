import java.util.*;

public class B {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int n = sc.nextInt();
		boolean s[] = new boolean[14];
		boolean h[] = new boolean[14];
		boolean d[] = new boolean[14];
		boolean c[] = new boolean[14];
		for (int i = 0; i < n; i++) {
			String a = sc.next();
			int x = sc.nextInt();
			if (a.equals("S")) {
				s[x] = true;
			} else if (a.equals("H")) {
				h[x] = true;
			} else if (a.equals("C")) {
				c[x] = true;
			} else {
				d[x] = true;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < 14; i++) {
			if (!s[i]) sb.append("S ").append(i).append("\n");
		}
		for (int i = 1; i < 14; i++) {
			if (!h[i]) sb.append("H ").append(i).append("\n");
		}
		for (int i = 1; i < 14; i++) {
			if (!c[i]) sb.append("C ").append(i).append("\n");
		}
		for (int i = 1; i < 14; i++) {
			if (!d[i]) sb.append("D ").append(i).append("\n");
		}
		System.out.print(sb);
	}
}
