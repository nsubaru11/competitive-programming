import java.io.*;
import java.util.*;

public class D {

	private static final PrintWriter out = new PrintWriter(System.out, false);
	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder(sc.next());
		int q = sc.nextInt();
		while (q-- > 0) {
			String t = sc.next();
			switch (t) {
				case "replace":
					int i = sc.nextInt(), j = sc.nextInt() + 1;
					String s = sc.next();
					sb.replace(i, j, s);
					break;
				case "reverse":
					i = sc.nextInt();
					j = sc.nextInt() + 1;
					StringBuilder temp = new StringBuilder(sb.substring(i, j));
					sb.replace(i, j, temp.reverse().toString());
					break;
				case "print":
					i = sc.nextInt();
					j = sc.nextInt() + 1;
					out.println(sb.substring(i, j));
					break;
			}
		}
		out.flush();
	}
}
