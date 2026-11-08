import lib.io.*;

public class C {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		char[] s = sc.nextChars();
		int n = s.length;
		long ans = (long) n * (n - 1) / 2;
		boolean f = false;
		int[] map = new int[26];
		for (char c : s) {
			c -= 'a';
			if (map[c] >= 1) {
				f = true;
			}
			ans -= map[c];
			map[c]++;
		}
		System.out.println(f ? ans + 1 : ans);
	}
}
