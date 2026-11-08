import lib.io.*;

public class A {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();

		String S = sc.next();
		String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; //あらかじめ大文字列を用意
		if (upper.contains(S.substring(0, 1))) {    //始まりが大文字かどうか
			for (int i = 1; i < S.length(); i++) {
				if (upper.contains(S.substring(i, i + 1))) {    //i番目の文字が大文字かどうか
					System.out.println("No");
					return;
				}
			}
		} else {
			System.out.println("No");
			return;
		}
		System.out.println("Yes");
	}
}
