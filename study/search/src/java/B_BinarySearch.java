import java.util.Arrays;

public class B_BinarySearch {

	public static void main(String[] args) {
		time(100000000, 100);
		test(10, 0);
	}

	private static final void time(int n, int k) {
		double t1, t2, t3, t4, t5, t6;
		t1 = t2 = t3 = t4 = t5 = t6 = 0;
		for (int i = 0; i < k; i++) {
			int target = (int) (Math.random() * n);
			int[] data = new int[n];
			Arrays.setAll(data, j -> (int) (Math.random() * n));
			Arrays.sort(data);

			long t = System.nanoTime();
			normalBinarySearch(data, target);
			t1 += System.nanoTime() - t;

			t = System.nanoTime();
			Arrays.binarySearch(data, target);
			t2 += System.nanoTime() - t;

			t = System.nanoTime();
			AbstractBinarySearch bs = new AbstractBinarySearch() {
				protected int comparator(long n) {
					int i = (int) n;
					return Integer.compare(data[i], target);
				};
			};
			t6 += System.nanoTime() - t;

			t = System.nanoTime();
			bs.normalSearch(0, n);
			t3 += System.nanoTime() - t;

			t = System.nanoTime();
			bs.lowerBoundSearch(0, n);
			t4 += System.nanoTime() - t;

			t = System.nanoTime();
			bs.upperBoundSearch(0, n);
			t5 += System.nanoTime() - t;
		}

		System.out.printf("テスト回数: %d, データ数: %d\n", k, n);
		System.out.printf("通常: %.5fms\n", t1 / k / 1_000_000);
		System.out.printf("Arrays: %.5fms\n", t2 / k / 1_000_000);
		System.out.printf("abs-nrmal: %.5fms\n", (t3 + t6) / k / 1_000_000);
		System.out.printf("abs-lower: %.5fms\n", (t4 + t6) / k / 1_000_000);
		System.out.printf("abs-upper: %.5fms\n", (t5 + t6) / k / 1_000_000);
		System.out.println();
	}

	private static final void test(int n, int k) {
		for (int i = 0; i < k; i++) {
			int target = (int) (Math.random() * n);
			int[] data = new int[n];
			Arrays.setAll(data, j -> (int) (Math.random() * n));
			Arrays.sort(data);
			System.out.println(Arrays.toString(data));
			int normal = normalBinarySearch(data, target);
			int lower = lowerBinarySearch(data, target);
			int upper = upperBinarySearch(data, target);
			int arrays = Arrays.binarySearch(data, target);
			if (normal != arrays)
				System.out.println("normal: " + normal + "\narrays: " + arrays);
			System.out.println("target: " + target + ", index: " + arrays);
			AbstractBinarySearch bs = new AbstractBinarySearch() {
				protected int comparator(long n) {
					int i = (int) n;
					return Integer.compare(data[i], target);
				};
			};
			int normal_bs = bs.normalSearch(0, n);
			int lower_bs = bs.lowerBoundSearch(0, n);
			int upper_bs = bs.upperBoundSearch(0, n);
			if (normal != normal_bs)
				System.out.println("normal: " + normal + "\nabs: " + normal_bs);
			if (lower != lower_bs)
				System.out.println("normal: " + lower + "\nabs: " + lower_bs);
			if (upper != upper_bs)
				System.out.println("normal: " + upper + "\nabs: " + upper_bs);
			System.out.println("normal: " + bs.normalSearch(0, n) + ", lower: " + bs.lowerBoundSearch(0, n)
					+ ", upper: " + bs.upperBoundSearch(0, n));
			System.out.println();
		}
	}

	private static final int normalBinarySearch(int[] data, int target) {
		int l = 0, r = data.length - 1;
		while (l <= r) {
			int m = (l + r) >> 1;
			int v = data[m];
			if (v < target)
				l = m + 1;
			else if (v > target)
				r = m - 1;
			else
				return m;
		}
		return ~l;
	}

	private static final int lowerBinarySearch(int[] data, int target) {
		int l = 0, r = data.length - 1;
		Integer k = null;
		while (l <= r) {
			int m = (l + r) >> 1;
			int v = data[m];
			if (v < target) {
				l = m + 1;
			} else if (v > target) {
				r = m - 1;
			} else {
				k = m;
				r = m - 1;
			}
		}
		return k != null ? k : ~l;
	}

	private static final int upperBinarySearch(int[] data, int target) {
		int l = 0, r = data.length - 1;
		Integer k = null;
		while (l <= r) {
			int m = (l + r) >> 1;
			int v = data[m];
			if (v < target) {
				l = m + 1;
			} else if (v > target) {
				r = m - 1;
			} else {
				l = m + 1;
				k = m;
			}
		}
		return k != null ? k : ~l;
	}

	/**
	 * 整数と長整数に対して通常の二分探索、上限探索(Upper Bound)、下限探索(Lower Bound)を行うための抽象クラスです。
	 * 探索に失敗した際の戻り値は-(挿入位置(境界値) - 1)となっています。
	 */
	@SuppressWarnings("unused")
	private static abstract class AbstractBinarySearch {

		/**
		 * 整数範囲での通常の二分探索を行います。comparatorが0を返した時点で探索を終了します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含まない)
		 * @return int
		 */
		public final int normalSearch(int l, int r) {
			return binarySearch(l, r - 1, SearchType.NORMAL);
		}

		/**
		 * 長整数範囲での通常の二分探索を行います。comparatorが0を返した時点で探索を終了します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含まない)
		 * @return long
		 */
		public final long normalSearch(long l, long r) {
			return binarySearch(l, r - 1, SearchType.NORMAL);
		}

		/**
		 * 整数範囲での上限探索(Upper Bound)を行います。 目的の条件にちょうど当てはまる際にcomparatorが0を返すことが好ましい。
		 * comparatorが0を返した際、その値を記憶します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含まない)
		 * @return 条件にちょうど当てはまる整数。もしくは上限値+1(挿入位置)。
		 */
		public final int upperBoundSearch(int l, int r) {
			return binarySearch(l, r - 1, SearchType.UPPER_BOUND);
		}

		/**
		 * 長整数範囲での上限探索(Upper Bound)を行います。 目的の条件にちょうど当てはまる際にcomparatorが0を返すことが好ましい。
		 * comparatorが0を返した際、その値を記憶します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含まない)
		 * @return 条件にちょうど当てはまる長整数。もしくは上限値+1(挿入位置)。
		 */
		public final long upperBoundSearch(long l, long r) {
			return binarySearch(l, r - 1, SearchType.UPPER_BOUND);
		}

		/**
		 * 整数範囲での下限探索(Lower Bound)を行います。 目的の条件にちょうど当てはまる際にcomparatorが0を返すことが好ましい。
		 * comparatorが0を返した際、その値を記憶します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含まない)
		 * @return 条件にちょうど当てはまる整数。もしくは下限値-1(挿入位置)。
		 */
		public final int lowerBoundSearch(int l, int r) {
			return binarySearch(l, r - 1, SearchType.LOWER_BOUND);
		}

		/**
		 * 長整数範囲での下限探索(Lower Bound)を行います。 目的の条件にちょうど当てはまる際にcomparatorが0を返すことが好ましい。
		 * comparatorが0を返した際、その値を記憶します。
		 *
		 * @param l 下限値 (この数を含む)
		 * @param r 上限値 (この数を含まない)
		 * @return 条件にちょうど当てはまる長整数。もしくは下限値-1(挿入位置)。
		 */
		public final long lowerBoundSearch(long l, long r) {
			return binarySearch(l, r - 1, SearchType.LOWER_BOUND);
		}

		/**
		 * 内部的に利用される探索種別を示す列挙型
		 */
		private enum SearchType {
			NORMAL, UPPER_BOUND, LOWER_BOUND
		}

		/**
		 * 整数範囲での汎用二分探索メソッド
		 */
		private final int binarySearch(int l, int r, SearchType type) {
			Integer k = null;
			while (l <= r) {
				int m = (l + r) >> 1;
				switch (comparator(m)) {
					case 1:
						r = m - 1;
						break;
					case 0:
						switch (type) {
							case UPPER_BOUND:
								l = m + 1;
								break;
							case LOWER_BOUND:
								r = m - 1;
								break;
							case NORMAL:
								return m;
						}
						k = m;
						break;
					case -1:
						l = m + 1;
						break;
				}
			}
			return k != null ? k : ~l;
		}

		/**
		 * 長整数範囲での汎用二分探索メソッド
		 */
		private final long binarySearch(long l, long r, SearchType type) {
			Long k = null;
			while (l <= r) {
				long m = (l + r) >> 1L;
				switch (comparator(m)) {
					case 1:
						r = m - 1;
						break;
					case 0:
						switch (type) {
							case UPPER_BOUND:
								l = m + 1;
								break;
							case LOWER_BOUND:
								r = m - 1;
								break;
							case NORMAL:
								return m;
						}
						k = m;
						break;
					case -1:
						l = m + 1;
						break;
				}
			}
			return k != null ? k : ~l;
		}

		/**
		 * 問題に応じた実装を必要とします。条件を超過する際は1, ちょうど合致する際は0、そうでない場合は-1を返すことが望ましい。
		 */
		abstract protected int comparator(long n);
	}

}
