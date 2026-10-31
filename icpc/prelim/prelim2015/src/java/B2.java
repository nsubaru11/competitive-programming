import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class B2 {

	private static void solve(final Scanner sc, final PrintWriter out, int n) {
		int[] words = new int[n];
		Arrays.setAll(words, i -> sc.next().length());
		int[] tanka = {5, 7, 5, 7, 7};
		Arrays.parallelPrefix(tanka, Integer::sum);
		Arrays.parallelPrefix(words, Integer::sum);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 5; j++) {
				int k = i;
				while (tanka[j] > words[k]) {
					k++;
				}
				if (tanka[j] != words[k]) break;
			}
		}
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