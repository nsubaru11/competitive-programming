import java.io.*;
import java.util.*;

public class A {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out, false);

	public static void main(String[] args) {
		while (true) {
			int m = sc.nextInt();
			int f = sc.nextInt();
			int r = sc.nextInt();
			if (m == -1 && f == -1 && r == -1) {
				out.flush();
				break;
			}
			if (m == -1 || f == -1) {
				out.println("F");
			} else if (m + f >= 80) {
				out.println("A");
			} else if (m + f >= 65) {
				out.println("B");
			} else if (m + f >= 50) {
				out.println("C");
			} else if (m + f >= 30) {
				if (r >= 50) {
					out.println("C");
				} else {
					out.println("D");
				}
			} else {
				out.println("F");
			}
		}
	}
}
