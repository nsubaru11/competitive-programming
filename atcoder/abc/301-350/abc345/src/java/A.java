import lib.io.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		char[] s = sc.nextChars();
		int n = s.length;
		if (s[0] != '<' || s[n - 1] != '>') {
			System.out.println("No");
			return;
		}
		for (int i = 1; i < n - 1; i++) {
			if (s[i] != '=') {
				System.out.println("No");
				return;
			}
		}
		System.out.println("Yes");
	}
}
