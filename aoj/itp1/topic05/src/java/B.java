import java.io.*;
import java.util.*;

public class B {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out, false);

	public static void main(String[] args) {
		while (true) {
			int h = sc.nextInt(), w = sc.nextInt();
			if (h == w && w == 0) break;
			StringBuilder temp = new StringBuilder("#"), frame = new StringBuilder();
			StringBuilder temp2 = new StringBuilder("."), mid = new StringBuilder();
			for (int k = w; k > 0; k >>= 1, temp.append(temp), temp2.append(temp2)) {
				if ((k & 1) == 1) {
					frame.append(temp);
					mid.append(temp2);
				}
			}
			frame.append("\n");
			mid.replace(0, 1, "#").replace(w - 1, w, "#").append("\n");
			StringBuilder ans = new StringBuilder(frame);
			for (h -= 2; h > 0; h >>= 1, mid.append(mid)) {
				if ((h & 1) == 1) ans.append(mid);
			}
			ans.append(frame);
			out.println(ans);
		}
		out.flush();
	}
}
