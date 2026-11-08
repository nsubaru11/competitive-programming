import java.util.*;

import lib.io.*;

import static java.lang.Math.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		Scanner sc2 = new Scanner(System.in);
		int[] a = new int[10];
		for (int i = 0; i < 10; i++) {
			a[i] = sc.nextInt();
		}

		int[] b = sc.nextInt(10);
		ArrayList<Integer> al = sc.nextIntAL(10);
		al = new ArrayList<Integer>();
		int n = 10;
		while (--n > 0) {
			al.add(sc.nextInt());
		}
		char[] s = sc.nextChars();
		char[] t = sc.nextChars();
		int sLen = s.length;
		int tLen = t.length;
		for (int i = 0; i < min(sLen, tLen); i++) {
			if (s[i] != t[i]) {
				System.out.println(i + 1);
				return;
			}
		}
		System.out.println(sLen != tLen ? min(sLen, tLen) + 1 : 0);
	}
}
