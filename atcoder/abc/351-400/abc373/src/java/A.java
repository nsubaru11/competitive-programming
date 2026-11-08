import lib.io.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int cnt = 0;
		for (int i = 0; i < 12; i++) {
			if (sc.next().length() == i + 1) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
