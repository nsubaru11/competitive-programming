import java.io.*;
import java.util.*;

public class C {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out, false);

	public static void main(String[] args) {
		while (true) {
			int h = sc.nextInt();
			int w = sc.nextInt();
			if (h == 0 && w == 0) break;
			StringBuilder sb = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			for (int j = 0; j < w; j++) {
				sb.append((j % 2 == 0) ? "#" : ".");
				sb2.append((j % 2 == 0) ? "." : "#");
			}
			sb.append("\n");
			sb2.append("\n");
			StringBuilder ans = new StringBuilder();
			for (int i = 0; i < h; i++) {
				ans.append((i % 2 == 0) ? sb : sb2);
			}
			out.println(ans);
		}
		out.flush();
	}
}
