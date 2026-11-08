import java.io.*;
import java.math.*;
import java.util.*;
import java.util.stream.*;

import static java.lang.Math.*;
import static java.util.Arrays.*;

public class C {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		TreeSet<Long> factors;    //約数の集合

		//あらかじめエラトステネスの篩により素数の集合を作成
		int max = (int) Math.sqrt(1000000000000000L) + 2;
		PrecomputedPrimes primes = new PrecomputedPrimes(max);

		while (true) {
			//入力
			long n = sc.nextLong();
			if (n == 0) {
				sc.close();
				out.flush();
				return;
			}

			// n が素数の時の例外処理
			BigInteger bi = BigInteger.valueOf(n);
			if (bi.isProbablePrime(5)) {
				out.println(n + 2L);
			} else {
				//初期化

				//約数の集合を作成
				factors = factorsTreeSet(n);

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
				long w = factors.ceiling((long) Math.cbrt(n));    //出発地点
				while (true) {
					// (n / w) が素数と仮定したときのd, hを用意する
					long d = n / w;
					long h = 1L;
					long s = w + d + h;
					//(n / w)が素数かどうかの判定、素数でない場合はd, hを定めに行く
					if (!primes.isPrime(n / w)) {
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

	/**
	 * 約数のTreeSet
	 */
	public static TreeSet<Long> factorsTreeSet(long n) {
		TreeSet<Long> hs = new TreeSet<>();
		int sqrt = (int) sqrt(n) + 1;
		for (int i = 1; i < sqrt; i++) {
			if (n % i == 0) {
				hs.add((long) i);
				hs.add(n / i);
			}
		}
		return hs;
	}

	/**
	 * エラトステネスの篩を用いて最大Integer.MAXVALUEの値
	 */
	@SuppressWarnings("unused")
	private static final class PrecomputedPrimes implements Iterable<Long> {
		private final long MAX_VALUE;
		private final long[] oddBits;
		private long[] primes;
		private int cnt;

		public PrecomputedPrimes(long n) {
			cnt = 0;
			MAX_VALUE = n;
			int estimatedCapacity = n < 20 ? 10 : (int) (1.05 * n / (Math.log(n) - 1.0));
			primes = new long[estimatedCapacity];
			oddBits = new long[(int) (((n >>> 1) + 63) >>> 6) + 1];
			final long sqrtN = (long) Math.sqrt(n);

			if (2 <= n) primes[cnt++] = 2;
			if (3 <= n) primes[cnt++] = 3;

			for (long i = 9; i <= n; i += 6) setCompositeOdd(i);
			for (long i = 5; i <= n; i += 6) {
				for (long j = i; j <= i + 2 && j <= n; j += 2) {
					if (isCompositeOdd(j)) continue;
					primes[cnt++] = j;
					if (j > sqrtN) continue;
					final long step = j * 6;
					final long delta2 = j * 2;
					for (long k = j * j; k <= n; k += step) {
						setCompositeOdd(k);
						long k2 = k + delta2;
						if (k2 > n) break;
						setCompositeOdd(k2);
						long k4 = k2 + delta2;
						if (k4 > n) break;
						setCompositeOdd(k4);
					}
				}
			}
			primes = copyOf(primes, cnt);
		}

		public boolean isPrime(long n) {
			if (MAX_VALUE < n) throw new IllegalArgumentException();
			if ((n & 1) == 0) return n == 2;
			return !isCompositeOdd(n);
		}

		public int countPrimesUpTo(long n) {
			if (MAX_VALUE < n) throw new IllegalArgumentException();
			int index = binarySearch(primes, n);
			return index < 0 ? ~index : index + 1;
		}

		public long ceilingPrime(long n) {
			if (MAX_VALUE < n) throw new IllegalArgumentException();
			int index = binarySearch(primes, n);
			index = index < 0 ? ~index : index;
			return index >= cnt ? -1 : primes[index];
		}

		public long higherPrime(long n) {
			return ceilingPrime(n + 1);
		}

		public long floorPrime(long n) {
			if (MAX_VALUE < n) throw new IllegalArgumentException();
			int index = binarySearch(primes, n);
			index = index < 0 ? ~index - 1 : index;
			return index < 0 ? -1 : primes[index];
		}

		public long lowerPrime(long n) {
			return floorPrime(n - 1);
		}

		public long kthPrime(int i) {
			if (i < 0 || cnt <= i) throw new IllegalArgumentException();
			return primes[i];
		}

		public Map<Long, Integer> primeFactorize(long n) {
			if (n < 0) throw new IllegalArgumentException();
			Map<Long, Integer> factors = new HashMap<>();
			int i = 0;
			while (n > 1) {
				if (i >= cnt) throw new IllegalArgumentException();
				long prime = primes[i++];
				while (n % prime == 0) {
					factors.put(prime, factors.getOrDefault(prime, 0) + 1);
					n /= prime;
				}
			}
			return factors;
		}

		@Override
		public Iterator<Long> iterator() {
			return new Iterator<>() {
				private int index = 0;

				@Override
				public boolean hasNext() {
					return index < cnt;
				}

				@Override
				public Long next() {
					if (!hasNext())
						throw new NoSuchElementException();
					return primes[index++];
				}
			};
		}

		public LongStream stream() {
			return Arrays.stream(primes);
		}

		private int bitIndex(long n) {
			return (int) (n >>> 7);
		}

		private long bitMask(long n) {
			return 1L << ((n >>> 1) & 63);
		}

		private void setCompositeOdd(long n) {
			oddBits[bitIndex(n)] |= bitMask(n);
		}

		private boolean isCompositeOdd(long n) {
			return (oddBits[bitIndex(n)] & bitMask(n)) != 0;
		}
	}

}
