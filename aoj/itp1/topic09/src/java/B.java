import java.util.*;

public class B {

	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		StringBuilder ans = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		while (!(sb = new StringBuilder(sc.next())).toString().equals("-")) {
			int m = sc.nextInt();
			while (m-- > 0) {
				int h = sc.nextInt();
				sb.append(sb.substring(0, h));
				sb.delete(0, h);
			}
			ans.append(sb).append("\n");
		}
		System.out.print(ans);
	}
}
