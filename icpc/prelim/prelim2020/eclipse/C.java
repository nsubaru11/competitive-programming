import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

public class C {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		HashSet<Long> temp = new HashSet<>();	//約数の集合を作る際一時的に使用
		ArrayList<Long> primeFactors = new ArrayList<>(); //素因数の順列
		TreeSet<Long> factors = new TreeSet<>();	//約数の集合

		//あらかじめエラトステネスの篩により素数の集合を作成
		TreeSet<Long> primeNum = new TreeSet<>();	//素数の集合
		int max = (int) Math.sqrt(1000000000000000L) + 2;
		primeNumSet(primeNum, max);

		while (true) {
			//入力
			long n = sc.nextLong();
			if (n == 0)  {
				sc.close();
				out.flush();
				return;
			}

			// n が素数の時の例外処理
			BigInteger prime = new BigInteger(n + "");
			if (prime.isProbablePrime(Integer.MAX_VALUE)) {
				out.println(n + 2L);
			} else {
				//初期化
				primeFactors.clear();
				factors.clear();

				Long origin = n;	//nを保持
				//nを素因数分解する
				PrimeFactorsList(primeFactors, primeNum, origin);

				//約数の集合を作成
				factors.add(1L);	//1を追加することで約数の集合を作れる
				for (long x: primeFactors) {
					for (long y: factors) {
						temp.add(y * x);
					}
					factors.addAll(temp);
					temp.clear();
				}

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
					if (!primeNum.contains(n / w)) {
						s = w + minComb(n / w, n, factors);
					}

					//答えを更新
					ans = Math.min(ans, s);

					// w を更新できなければ終了
					if (factors.higher(w) == null) break;
					w = factors.higher(w);
				}
				out.println(ans);
			}
		}
	}

    private static void primeNumSet (TreeSet<Long> primeNum, int max) {
        //エラトステネスの篩により、素数の集合を作成。
		primeNum.add(2L);
        boolean[] del = new boolean[max];
		for (long i = 3L; i < max; i += 2L) {
                if (del[(int) i] == false) {
				primeNum.add(i);
				for (int j = 2; i * j < max; j++) {
					del[(int) (j * i)] = true;
				}
			}
		}
    }

	private static void PrimeFactorsList(ArrayList<Long> primeFactors, TreeSet<Long> primeNumSet, long n) {
		//素因数分解をListで作成。
		for (long p: primeNumSet) {
			while (n % p == 0L) {
				n /= p;
				primeFactors.add(p);
			}
			if (n == 1L) {
				return;
			}
		}
		primeFactors.add(n);
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