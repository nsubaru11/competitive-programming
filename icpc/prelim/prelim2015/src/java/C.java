import java.io.*;
import java.util.*;
import java.util.function.*;

public class C {

	private static String[] str;
	private static int i, n;

	private static void solve(final Scanner sc, final PrintWriter out) {
		str = new String[n];
		Arrays.setAll(str, i -> sc.next());
		i = 0;
		out.println(dfs(0));
	}

	private static int dfs(int d) {
		char c = str[i++].charAt(d);
		int ans = c == '+' ? 0 : c == '*' ? 1 : c - '0';
		IntBinaryOperator op = read(c);
		while (i < n) {
			int len = str[i].length();
			if (len != d + 2) break;
			c = str[i].charAt(d + 1);
			if ('0' <= c && c <= '9') {
				ans = op.applyAsInt(ans, c - '0');
				i++;
			} else {
				ans = op.applyAsInt(ans, dfs(d + 1));
			}
		}
		return ans;
	}

	private static IntBinaryOperator read(char c) {
		return c == '+' ? Integer::sum : (x, y) -> x * y;
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
