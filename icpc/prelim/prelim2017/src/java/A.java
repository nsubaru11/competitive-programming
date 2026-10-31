import java.io.*;
import java.util.*;

public class A {

	private static void solve(final Scanner sc, final PrintWriter out, int n) {
		int m = sc.nextInt();
		int[] a = new int[n];
		Arrays.setAll(a, x -> sc.nextInt());
		TreeSet<Integer> set = new TreeSet<>();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				set.add(a[i] + a[j]);
			}
		}
		Integer ans = set.floor(m);
		out.println(ans == null ? "NONE" : ans);
	}

	public static void main(String[] args) {
		try (final Scanner sc = new Scanner(System.in);
			 final PrintWriter out = new PrintWriter(System.out)) {
			int n;
			while ((n = sc.nextInt()) != 0) {
				solve(sc, out, n);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
