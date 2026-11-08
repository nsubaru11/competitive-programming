import lib.io.*;

public class C {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();

		int n = Integer.parseInt(sc.next());
		String S = sc.next();
		int q = Integer.parseInt(sc.next());
		String abc = "abcdefghijklmnopqrstuvwxyz";
		String abc2 = "abcdefghijklmnopqrstuvwxyz";
		for (int i = 0; i < q; i++) {
			String c = sc.next();
			String d = sc.next();
			abc2 = abc2.replace(c.charAt(0), d.charAt(0));
		}
		String S2 = "";
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < abc.length(); j++) {
				if (S.charAt(i) == abc.charAt(j)) {
					S2 += abc2.charAt(j);
					break;
				}
			}
		}
		System.out.println(S2);

	}
}
