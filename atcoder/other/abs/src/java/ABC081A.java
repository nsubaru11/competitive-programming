import lib.io.*;

public class ABC081A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int cnt = 0;
		for (char c : sc.nextChars()) {
			if (c == '1') {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
