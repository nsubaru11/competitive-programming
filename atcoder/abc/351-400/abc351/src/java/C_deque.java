import java.util.*;

import lib.io.*;

public class C_deque {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		Deque<Integer> dq = new LinkedList<>();
		int len = 0;
		while (n-- > 0) {
			int a = sc.nextInt();
			while (len != 0 && dq.peekLast() == a) {
				dq.removeLast();
				len--;
				a++;
			}
			len++;
			dq.add(a);
		}
		System.out.println(dq.size());
	}
}
