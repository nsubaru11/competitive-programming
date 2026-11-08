import java.math.*;
import java.util.*;

public class C2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			long b = Long.parseLong(sc.next());
			if (b == 0) {
				sc.close();
				return;
			}

			// solve
			BigInteger prime = new BigInteger(b + "");
			if (Long.bitCount(b) == 1) {    //2の累乗となるときフロア数の総数は1つになるため例外処理
				System.out.println(b + " " + 1);

			} else if (prime.isProbablePrime(Integer.MAX_VALUE)) { //素数の時はフロア数が2つとなるため例外処理
				System.out.println(b / 2L + " " + 2);

			} else {

				/* 尺取り法
				 * 1. ある i から j まで連続する自然数の和を sum とする。
				 * 2. sum が　b を超えたとき sum が b の値を下回るまで i++ する。
				 * 3. sum が b を下回ったとき j を大きくする。
				 * これを繰り返し、sum == b になったときその地点で終了。
				 */
				long sum = 1L;
				long i = 1L;
				for (long j = 2L; j <= b; j++) {
					sum += j;
					while (sum > b) {
						sum -= i;
						i++;
					}
					if (sum == b) {
						System.out.println(i + " " + (j - i + 1L));
						break;
					}
				}
			}
		}
	}

}
