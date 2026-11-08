import java.io.*;
import java.util.*;

public class B {

	private static String[] words;
	private static int j, n;

	private static void solve(final Scanner sc, final PrintWriter out) {
		words = new String[n];
		Arrays.setAll(words, i -> sc.next());
		int ans = 0;
		for (int i = 0; i < n; i++) {
			j = i;
			if (!isExistLength(5)) continue;
			if (!isExistLength(7)) continue;
			if (!isExistLength(5)) continue;
			if (!isExistLength(7)) continue;
			if (isExistLength(7)) {
				ans = i + 1;
				break;
			}
		}
		out.println(ans);
	}

	private static boolean isExistLength(int l) {
		int len = 0;
		while (j < n && len < l) {
			len += words[j++].length();
		}
		return len == l;
	}

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
			 PrintWriter out = new PrintWriter(System.out)) {
			while ((n = sc.nextInt()) != 0) {
				solve(sc, out);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
