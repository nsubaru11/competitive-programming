import java.io.PrintWriter;
import java.util.*;

public class A {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while (true) {
			int n = Integer.parseInt(sc.next());
			if (n == 0) {
				sc.close();
				out.flush();
				return;
			}
			int m = Integer.parseInt(sc.next());
			int[] student = new int[n];
			int max = 0;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					student[j] += Integer.parseInt(sc.next());
					max = Math.max(max, student[j]);
				}
			}
			out.println(max);
		}
	}
}
