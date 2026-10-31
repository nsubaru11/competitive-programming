import java.util.*;

import lib.io.*;

public class D {

	// region < Constants & Globals >
	private static final boolean DEBUG = true;
	private static final int MOD = 998244353;
	// private static final int MOD = 1_000_000_007;
	private static final char[] op = new char[]{'L', 'U', 'R', 'D'};
	private static final int[] di = new int[]{0, -1, 0, 1, -1, -1, 1, 1};
	private static final int[] dj = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter(64);
	// endregion

	private static void solve() {
		ArrayDeque<Character> stack = new ArrayDeque<>();
		for (char c : sc.nextChars()) {
			if (!stack.isEmpty()) {
				if (c == ')' || c == ']' || c == '>') {
					switch (c) {
						case ')':
							if (stack.peek() == '(') {
								stack.pop();
								break;
							} else {
								out.println("No");
								out.flush();
								return;
							}
						case ']':
							if (stack.peek() == '[') {
								stack.pop();
								break;
							} else {
								out.println("No");
								out.flush();
								return;
							}
						case '>':
							if (stack.peek() == '<') {
								stack.pop();
								break;
							} else {
								out.println("No");
								out.flush();
								return;
							}
					}
				} else {
					stack.push(c);
				}
			} else {
				if (c == ')' || c == ']' || c == '>') {
					out.println("No");
					out.flush();
					return;
				}
				stack.push(c);
			}
		}
		out.println(stack.isEmpty());
		out.flush();
	}

	public static void main(final String[] args) {
		try {
			solve();
		} finally {
			out.close();
		}
	}
}
