import java.io.PrintWriter;
import java.util.Scanner;

import static java.util.Arrays.setAll;

public class B {

	private static void solve(Scanner sc, PrintWriter out, int n) {
		int[] a = new int[n];
		int[] b = new int[n];
		setAll(a, i -> sc.nextInt() + (i > 0 ? a[i - 1] : 0));
		setAll(b, i -> sc.nextInt() + (i > 0 ? b[i - 1] : 0));
		int i = 0;
		int cnt = 0;
		while (i < n && a[i] == b[i]) i++;
		if (i < n) {
			boolean flag = a[i] > b[i];
			for (; i < n; i++) {
				if (flag && a[i] < b[i]) {
					cnt++;
					flag = false;
				} else if (!flag && a[i] > b[i]) {
					cnt++;
					flag = true;
				}
			}
		}
		out.println(cnt);
	}

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
			 PrintWriter out = new PrintWriter(System.out)) {
			int n;
			while ((n = sc.nextInt()) != 0) {
				solve(sc, out, n);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
