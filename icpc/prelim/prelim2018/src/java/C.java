import java.math.BigInteger;
import java.util.*;

public class C {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			//input
			long b = Long.parseLong(sc.next());
			if (b == 0) {
				sc.close();
				return;
			}

			// solve
			BigInteger prime = new BigInteger(b + "");
			if (Long.bitCount(b) == 1) {
				System.out.println(b + " " + 1);

			} else if (prime.isProbablePrime(Integer.MAX_VALUE)) {
				System.out.println(b / 2L + " " + 2);

			} else {
				for (long i = 1L; i <= b; i++) {
					long sq = 8L * b + 4L * i * (i - 1L) + 1L;	//jについて解いた時に出てくるrootの中の値
					if (isSquareNum(sq) && (Math.round(Math.sqrt(sq)) % 2 == 1)) {
						long j = (Math.round(Math.sqrt(sq)) - 1L) / 2L;
						long sum = j - i + 1;
						System.out.println(i + " " + sum);
						break;
					}
				}
			}

		}
	}

	private static boolean isSquareNum(long square) {
		//平方数かどうかの判定
		long sqrt = Math.round(Math.sqrt(square));
		if (square == sqrt * sqrt) {
			return true;
		}
		return false;
	}

}
