import java.util.*;

public class C {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {
			long n = Long.parseLong(sc.next());
			long m = Long.parseLong(sc.next());
			if (n == 0 && m == 0) {
				sc.close();
				return;
			}
			if (n == 0) {
				System.out.println(-m * m);
				continue;
			}
			long score = Long.MIN_VALUE;
			for (long i = 1L; i <= n; i++) {
				long max = (n - i + 1L) * (n - i + 1L) + i - 1L;
				long min = (i + 1L - m % (i + 1L)) * (m / (i + 1L)) * (m / (i + 1L)) + (m % (i + 1L)) * (m / (i + 1L) + 1L) * (m / (i + 1L) + 1L);
				score = Math.max(score, max - min);
			}
			System.out.println(score);
		}
	}

}