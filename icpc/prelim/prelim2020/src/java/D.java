import java.io.*;
import java.util.*;
//import static java.lang.System.out;

public class D {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out);
	private static int i;

	private static void solve(int n) {
		char[] a = sc.next().toCharArray();
		char[] s = sc.next().toCharArray();
		char[] t = sc.next().toCharArray();
		Map<Character, List<Character>> graph = new HashMap<>(n);
		for (int i = 0; i < n; i++) {
			graph.put(a[i], new ArrayList<>());
		}
		i = 0;
		dfs(s, graph);
		i = 0;
		dfs(t, graph);
		for (int i = 0; i < n; i++) {
			out.println(a[i] + ": " + graph.get(a[i]));
		}
	}

	private static char dfs(char[] s, Map<Character, List<Character>> graph) {
		Character left, right, op;
		left = right = op = null;
		while (i < s.length) {
			Character c = read(s);
			if (c == '(') {
				if (left == null) {
					left = dfs(s, graph);
				} else {
					right = dfs(s, graph);
					if (left != right) {
						if (op == '<') {
							graph.get(left).add(right);
							left = right;
						} else {
							graph.get(right).add(left);
						}
					}
				}
			} else {
				if (left == null) {
					left = c;
				} else {
					right = c;
					if (left != right) {
						if (op == '<') {
							graph.get(left).add(right);
							left = right;
						} else {
							graph.get(right).add(left);
						}
					}
				}
			}
			c = read(s);
			if (c == null) {
				break;
			} else if (c == '<' || c == '>') {
				op = c;
			} else if (c == ')') {
				break;
			}
		}
		return left;
	}

	private static Character read(char[] s) {
		if (i < s.length) {
			return s[i++];
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		try {
			// 最初の入力
			int n;
			while ((n = sc.nextInt()) != 0) {
				solve(n); // メインロジック
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
			out.close();
		}
	}

}
