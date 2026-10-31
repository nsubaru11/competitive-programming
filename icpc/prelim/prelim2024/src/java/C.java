import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class C {

	private static void solve(Scanner sc, PrintWriter out) {
		int x = sc.nextInt();
		int y = sc.nextInt();
		int a = abs(y) + abs(x);
		int b = abs(y + x) + abs(x);
		int c = abs(y + x) + abs(y);
		out.println(min(a, min(b, c)));
	}

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
			 PrintWriter out = new PrintWriter(System.out)) {
			int n = sc.nextInt();
			while (n-- > 0) {
				solve(sc, out);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
