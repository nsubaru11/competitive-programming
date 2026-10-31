import java.io.PrintWriter;
import java.util.*;

import static java.util.Arrays.*;
import static java.lang.Math.*;

public class E {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out);
	private static int pos = 0;
	private static String e;

	private static void solve() {
		HashMap<StringBuilder, Integer> map = new HashMap<>();
		HashMap<Integer, Integer> map2 = new HashMap<>();
		int len = e.length();
		int cnt = 0;
		Tree tree = new Tree();
		while (pos < len) {
			Tree temp = tree;
			if (e.charAt(pos) == 'i') {
				pos += 3;
			}
			StringBuilder sb = new StringBuilder();
			while (pos < len && e.charAt(pos) != '_') {
				sb.append(e.charAt(pos++));
			}
			map.put(sb, cnt);
			temp.setLabel(sb.toString());
			pos += 6;
			cnt++;
			if ('0' <= e.charAt(pos) && e.charAt(pos) <= '9') {
				temp.IF = new Tree();
				temp.IF.setLabel(e.charAt(pos++) + "");
				if (pos < len && e.charAt(pos) == 'e') {
					temp.ELSE = new Tree();
					temp = temp.ELSE;
				}
			} else {
				temp = temp.IF;
				cnt++;
			}
		}
	}

	private static class Tree {
		String label;
		Tree IF, ELSE;

		public Tree() {

		}

		public void setLabel(String label) {
			this.label = label;
		}
	}

	private record Token(String var, int num, State state) {}

	enum State {
		IF, ELSE, VAR, THEN, NUM
	}

	public static void main(String[] args) {
		try {
			// 最初の入力
			e = sc.next();
			solve(); // メインロジック
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
			out.close();
		}
	}

}
