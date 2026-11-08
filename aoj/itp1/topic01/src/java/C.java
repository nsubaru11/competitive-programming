import java.io.*;
import java.util.*;

public class C {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out, false);

	public static void main(String[] args) {
		int a = sc.nextInt(), b = sc.nextInt();
		out.print(a * b + " " + 2 * (a + b));
		out.flush();
	}
}
