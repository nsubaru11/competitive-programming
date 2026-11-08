import java.util.*;

import lib.io.*;

public class C_stack {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		Stack<Integer> st = new Stack<>();
		int len = 0;
		while (n-- > 0) {
			int a = sc.nextInt();
			while (len != 0 && st.peek() == a) {
				st.pop();
				len--;
				a++;
			}
			len++;
			st.push(a);
		}
		System.out.println(len);
	}
}
