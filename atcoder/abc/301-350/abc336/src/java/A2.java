import java.io.*;

import lib.io.*;

public class A2 {

	public static void main(String[] args) {

		FastScanner sc = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);

		int n = Integer.parseInt(sc.next());
		out.print("L");
		for (int i = 0; i < n; i++) {
			out.print("o");
		}
		out.println("ng");
		out.flush();
	}
}
