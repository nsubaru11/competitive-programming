import java.io.PrintWriter;
import java.util.Scanner;

import static java.util.Arrays.setAll;

public class A {

	private static void solve(Scanner sc, PrintWriter out, int n) {
		int[] a = new int[n];
		setAll(a, i -> sc.nextInt());
		int ans = 0;
		int money = 300;
		for (int i = 0; i < n; i++) {
			if (money >= a[i]) {
				ans += a[i];
				money -= a[i];
			}
		}
		out.println(ans);
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
