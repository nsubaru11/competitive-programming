import lib.io.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		String S = sc.next();
		for (int i = 0; i < 3; i++) {
			if (S.charAt(i) == 'R') {
				System.out.println("Yes");
				return;
			}
			if (S.charAt(i) == 'M') {
				System.out.println("No");
				return;
			}
		}
	}
}
