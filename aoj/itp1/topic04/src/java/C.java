import java.io.*;
import java.util.*;

public class C {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out, false);

	public static void main(String[] args) {
		while (true) {
			int a = sc.nextInt();
			char op = sc.next().charAt(0);
			int b = sc.nextInt();
			if (op == '?') break;
			out.println((op == '+') ? a + b : (op == '-') ? a - b : (op == '*') ? a * b : a / b);
		}
		out.flush();
	}
}
