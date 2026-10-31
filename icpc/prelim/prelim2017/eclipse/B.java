import java.util.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			String S = sc.next();
			if (S.equals(".")) {
				sc.close();
				return;
			}
			String T = sc.next();
			// solve & output
			System.out.println((S.equals(T)) ? "IDENTICAL": (compare(S, T, 0, 0, "even", true)) ? "CLOSE": "DIFFERENT");
		}
	}

	private static boolean compare (String s, String t, int i, int j, String judge, boolean close) {
		boolean f = true;

		if (s.indexOf("\"", i) == -1 && t.indexOf("\"", j) == -1) {

			if (!s.contains("\"") || !s.substring(i).equals(t.substring(j))) {
				return false;
			} else {
				return true;
			}
		} else if ((s.indexOf("\"", i) == -1 || t.indexOf("\"", j) == -1)) {
			return false;
		}

		if (s.substring(i, s.indexOf("\"", i)).equals(t.substring(j, t.indexOf("\"", j))) && judge.equals("even")) {
			i = s.indexOf("\"", i) + 1;
			j = t.indexOf("\"", j) + 1;
			f = compare(s, t, i, j, "odd", close);

		} else if (!s.substring(i, s.indexOf("\"", i)).equals(t.substring(j, t.indexOf("\"", j))) && judge.equals("even")) {
			return false;

		} else if (s.substring(i, s.indexOf("\"", i)).equals(t.substring(j, t.indexOf("\"", j))) && judge.equals("odd")) {
			i = s.indexOf("\"", i) + 1;
			j = t.indexOf("\"", j) + 1;
			f = compare(s, t, i, j, "even", close);

		} else if (!s.substring(i, s.indexOf("\"", i)).equals(t.substring(j, t.indexOf("\"", j))) && judge.equals("odd")) {
			if (!close) {
				return false;
			} else {
				i = s.indexOf("\"", i) + 1;
				j = t.indexOf("\"", j) + 1;
				close = false;
				f = compare(s, t, i, j, "even", close);
			}
		}
		return f;
	}

}