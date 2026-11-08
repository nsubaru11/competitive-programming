import java.util.*;

import lib.io.*;

public class C_list {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		ArrayList<Integer> al = new ArrayList<>(n);
		int len = 0;
		while (n-- > 0) {
			int a = sc.nextInt();
			while (len != 0 && al.get(len - 1) == a) {
				al.remove(--len);
				a++;
			}
			al.add(a);
			len++;
		}
		System.out.println(len);
	}
}
