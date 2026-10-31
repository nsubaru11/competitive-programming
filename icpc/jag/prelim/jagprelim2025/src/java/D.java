import java.io.PrintWriter;
import java.util.*;

import static java.lang.System.out;
import static java.util.Arrays.*;
import static java.lang.Math.*;

public class D {

	private static final Scanner sc = new Scanner(System.in);
	private static int n;
	private static StringBuilder parentheses;

	private static void solve() {
		dfs(1, 0);
		while (parentheses.length() < n + 2) {
			parentheses.append(')');
		}
		out.println(parentheses);
	}

	private static void dfs(int i, int cnt) {
		if (i >= n) return;
		out.println("? " + i + " " + (i + 1));
		int a = sc.nextInt();
		if (a == 0) {
			if (n / 2 >= cnt) {
				parentheses.append("(");
				dfs(i + 1, cnt + 2);
			} else {
				parentheses.append(")");
				dfs(i + 1, cnt - 2);
			}
		} else {
			parentheses.append("()");
			dfs(i + 2, cnt);
		}
	}

	public static void main(String[] args) {
		try {
			// 最初の入力
			n = sc.nextInt();
			parentheses = new StringBuilder(n + 2);
			parentheses.append("! ");
			solve();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}

}
