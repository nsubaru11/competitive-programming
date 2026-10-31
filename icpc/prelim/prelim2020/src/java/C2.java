import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class C2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		TreeSet<Long> factors = new TreeSet<>();	//約数の集合

		//約数の集合をO(root(n))で作成し問題を解く方法

		while (true) {
			//入力
			long n = sc.nextLong();
			if (n == 0)  {
				sc.close();
				out.flush();
				return;
			}

			BigInteger primeNum = new BigInteger(n + "");
			if (primeNum.isProbablePrime(Integer.MAX_VALUE)) {
				out.println(n + 2L);

			} else {

				//初期化
				factors.clear();

				//約数全列挙
				factorize(factors, n);

				/* 前提条件
				 * ansは最大値で初期化
				 * n = w * d * h
				 * s = w + d + h (w >= d >= h)
				 * w は n の立方根以上である
				 * d は n の立方根以下かつ (n / w) の平方根以上である
				 * h は w と d が定まることにより定まる
				 */

				/* 操作
				 * 1. w の値を n の立方根から上げていく
				 * 2. 各 w について d の値を (n / w) の平方根から上げていく
				 * 3. w　を n　になるまで計算
				 * 4. d　はその値での (d + h) の最小値を保持し、最小値を更新できなくなったらreturn
				 */

				long ans = Long.MAX_VALUE;
				long w = factors.ceiling((long) Math.cbrt(n));	//出発地点
				while (true) {

					// (n / w) が素数と仮定したときのd, hを用意する
					long d = n / w;
					long h = 1L;
					long s = w + d + h;

					//(n / w)が素数かどうかの判定、素数でない場合はd, hを定めに行く
					primeNum = new BigInteger(n / w + "");
					if (!primeNum.isProbablePrime(Integer.MAX_VALUE)) {
						s = w + minComb(n / w, n, factors);
					}

					//より小さい方を答えとする
					ans = Math.min(ans, s);
					// w より大きな数がなければ終了
					if (factors.higher(w) == null) break;
					w = factors.higher(w);
				}
				out.println(ans);
			}
		}
	}

	private static void factorize(TreeSet<Long> factors, long n) {
		//約数の集合を作成
		for (long i = 1L; i <= Math.sqrt(n) + 1; i++) {
			if (n % i == 0) {
				factors.add(i);
				factors.add(n / i);
			}
		}
	}

	private static long minComb(long dh, long n, TreeSet<Long> factors) {
		// min を最大値で初期化
		//dh　= n / w
		long min = Long.MAX_VALUE;
		long h = 1L;
		long d = factors.ceiling((long) Math.sqrt(dh));//d の出発点は dh の平方根以上の n の約数

		/* 操作
		 * 1. dh % d == 0となるまでdの値を変える
		 * 2.　1のときはd と hを定め、最小値の更新
		 * 3.最小値が更新できなくなった地点でreturn
		 * 4.3の場合または、 d を更新できなくなった地点でreturn
		 */

		while (true) {
			if (dh % d == 0) {
				h = dh / d;
				min = Math.min(min, d + h);
				if (min < d + h) {
					return min;
				}
			}
			if (factors.higher(d) == null) {
				return min;
			}
			d = factors.higher(d);
		}
	}

}