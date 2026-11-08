import java.io.*;
import java.util.*;

public class C {

	private static final PrintWriter out = new PrintWriter(System.out, false);
	private static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int n = sc.nextInt();
		int taro = 0, hana = 0;
		while (n-- > 0) {
			String s = sc.next(), t = sc.next();
			int k = s.compareTo(t);
			if (k > 0) {
				taro += 3;
			} else if (k < 0) {
				hana += 3;
			} else {
				taro++;
				hana++;
			}
		}
		out.print(taro + " " + hana);
		out.flush();
	}
}
