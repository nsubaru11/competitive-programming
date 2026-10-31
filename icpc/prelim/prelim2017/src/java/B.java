import java.io.PrintWriter;
import java.util.Scanner;

public class B {

	private static boolean solve(final Scanner sc, final PrintWriter out) {
		String s = sc.nextLine();
		if (s.equals(".")) return false;
		String t = sc.nextLine();
		s = s.replaceAll("\"", " \" ");
		t = t.replaceAll("\"", " \" ");
		String[] s2 = s.split("\"");
		String[] t2 = t.split("\"");
		int k = 0;
		for (int i = 0; i < s2.length && i < t2.length; i++) {
			if (!s2[i].equals(t2[i])) {
				if ((i & 1) == 1) {
					k++;
				} else {
					k += 2;
				}
			}
		}
		if (k == 0) {
			if (s2.length == t2.length) {
				out.println("IDENTICAL");
			} else if (Math.abs(s2.length - t2.length) == 1) {
				out.println("CLOSE");
			} else {
				out.println("DIFFERENT");
			}
		} else if (k == 1) {
			if (s2.length == t2.length) {
				out.println("CLOSE");
			} else {
				out.println("DIFFERENT");
			}
		} else {
			out.println("DIFFERENT");
		}
		return true;
	}

	public static void main(String[] args) {
		try (final Scanner sc = new Scanner(System.in);
			 final PrintWriter out = new PrintWriter(System.out)) {
			while (solve(sc, out)) ;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
