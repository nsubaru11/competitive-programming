import java.util.*;

import lib.io.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int L = sc.nextInt(), R = sc.nextInt();
		StringJoiner sj = new StringJoiner(" ");
		for (int i = 1; i <= n; i++) {
			if (i == L) {
				for (i = R; i >= L; i--) {
					sj.add(Integer.toString(i));
				}
				i = R;
			} else {
				sj.add(Integer.toString(i));
			}
		}
		System.out.println(sj);
	}
}
