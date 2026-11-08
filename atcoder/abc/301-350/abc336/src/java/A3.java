import lib.io.*;

public class A3 {

	public static void main(String[] args) {

		FastScanner sc = new FastScanner();

		int n = Integer.parseInt(sc.next());
		StringBuilder sb = new StringBuilder();
		sb.append("L");
		for (int i = 0; i < n; i++) {
			sb.append("o");
		}
		sb.append("ng");
		System.out.println(sb);

	}
}
