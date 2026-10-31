import java.util.Scanner;

public class C3 {

	private static void solve(Scanner sc, int b) {
		if (Integer.bitCount(b) == 1) {
			System.out.println(b + " " + 1);
			return;
		}
		// b = j * (j + 1) / 2 - i * (i - 1) / 2 となる j を探す。
		// j^2 + j - i^2 + i - 2b = 0;
		// j = (-1 + sqrt(1 + 4 * i^2 - 4 * i + 8 * b)) / 2
		// j = (-1 + sqrt) / 2;
		for (int l = 1; l <= b; l++) {
			long sqrt = (long) Math.sqrt(1L + 4L * l * l - 4L * l + 8L * b);
			if (sqrt * sqrt == sqrt) {
				long r = (-1L + sqrt) / 2L;
				if (b == r * (r + 1) / 2 - l * (l - 1) / 2) {
					System.out.println((l + 1) + " " + (r - l + 1));
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			int b = sc.nextInt();
			while (b != 0) {
				solve(sc, b);
				b = sc.nextInt();
			}
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}
	}

}
