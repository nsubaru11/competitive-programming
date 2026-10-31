import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class A {

	private static void solve(Scanner sc, PrintWriter out, int m, int n_min, int n_max) {
		int[] a = new int[m];
		Arrays.setAll(a, i -> sc.nextInt());
		Arrays.sort(a);
		int max = 0, ans = -1;
		for (int i = n_min; i <= n_max; i++) {
			int gap = a[m - i] - a[m - i - 1];
			if (max <= gap) {
				max = gap;
				ans = i;
			}
		}
		out.println(ans);
	}

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
			PrintWriter out = new PrintWriter(System.out)) {
			int m, n_min, n_max;
			while (true) {
				m = sc.nextInt();
				n_min = sc.nextInt();
				n_max = sc.nextInt();
				if (m == n_min && n_min == n_max && n_max == 0) break;
				solve(sc, out, m, n_min, n_max);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}