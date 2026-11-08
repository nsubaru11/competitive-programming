import lib.io.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		String S = sc.next();
		int upp = 0, low = 0;
		for (char c : S.toCharArray()) {
			if (Character.isUpperCase(c)) upp++;
			else low++;
		}
		System.out.println(upp > low ? S.toUpperCase() : S.toLowerCase());
	}
}
