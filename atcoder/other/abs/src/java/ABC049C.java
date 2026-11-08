import lib.io.*;

public class ABC049C {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		FastPrinter out = new FastPrinter();

		StringBuilder S = new StringBuilder(sc.next());
		S.reverse();
		for (int i = 0; i < S.length(); i++) {
			if (S.charAt(i) == 'r') {
				if (S.length() > i + 2) {
					if (S.charAt(i + 2) == 's') {
						if (S.length() >= i + 6) {
							String s = S.substring(i, i + 6);
							if (s.equals("resare")) {
								i += 5;
							} else {
								out.println("NO");
								out.flush();
								return;
							}
						} else {
							out.println("NO");
							out.flush();
							return;
						}
					} else if (S.charAt(i + 2) == 'm') {
						if (S.length() >= i + 7) {
							String s = S.substring(i, i + 7);
							if (s.equals("remaerd")) {
								i += 6;
							} else {
								out.println("NO");
								out.flush();
								return;
							}
						} else {
							out.println("NO");
							out.flush();
							return;
						}
					} else {
						out.println("NO");
						out.flush();
						return;
					}
				} else {
					out.println("NO");
					out.flush();
					return;
				}
			} else if (S.charAt(i) == 'e') {
				if (S.length() >= i + 5) {
					String s = S.substring(i, i + 5);
					if (s.equals("esare")) {
						i += 4;
					} else {
						out.println("NO");
						out.flush();
						return;
					}
				} else {
					out.println("NO");
					out.flush();
					return;
				}
			} else if (S.charAt(i) == 'm') {
				if (S.length() >= i + 5) {
					String s = S.substring(i, i + 5);
					if (s.equals("maerd")) {
						i += 4;
					} else {
						out.println("NO");
						out.flush();
						return;
					}
				} else {
					out.println("NO");
					out.flush();
					return;
				}
			} else {
				out.println("NO");
				out.flush();
				return;
			}
		}
		out.println("YES");
		out.flush();
	}
}
