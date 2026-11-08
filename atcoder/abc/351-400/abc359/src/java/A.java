import lib.io.*;

public class A {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int cnt = 0;
		while (n-- > 0) {
			if (sc.next().equals("Takahashi")) {
				cnt++;
			}
		}
		System.out.println(cnt);

	}
}
