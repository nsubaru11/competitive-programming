import lib.io.*;

public class C2 {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();

		long n = Long.parseLong(sc.next());
		String S = Long.toString(n - 1, 5);
		long ans = 0;
		long tenPow = 1L;
		for (int i = S.length() - 1; i >= 0; i--) {
			ans += Integer.parseInt(S.substring(i, i + 1)) * 2 * tenPow;
			tenPow *= 10L;
		}
		System.out.println(ans);

	}
}
