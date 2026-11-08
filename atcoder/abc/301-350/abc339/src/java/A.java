import lib.io.*;

public class A {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();

		String s = sc.next();
		String[] ans = s.split(".");
		System.out.println(ans[ans.length - 1]);

	}
}
