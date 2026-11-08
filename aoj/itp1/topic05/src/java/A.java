import java.io.*;
import java.util.*;

public class A {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out, false);

	public static void main(String[] args) {
		while (true) {
			int h = sc.nextInt(), w = sc.nextInt();
			if (h == w && w == 0) break;
			StringBuilder sb = new StringBuilder("#"), sb2 = new StringBuilder();
			for (; w > 0; w >>= 1, sb.append(sb)) {
				if ((w & 1) == 1) sb2.append(sb);
			}
			sb2.append("\n");
			sb = new StringBuilder();
			for (; h > 0; h >>= 1, sb2.append(sb2)) {
				if ((h & 1) == 1) sb.append(sb2);
			}
			out.println(sb);
		}
		out.flush();
	}
}
