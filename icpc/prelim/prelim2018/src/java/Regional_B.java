import java.util.*;

import lib.io.*;

public class Regional_B {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();

		int n = Integer.parseInt(sc.next());
		HashSet<Long> set = new HashSet<>();    //オリジナル
		HashSet<Long> setd = new HashSet<>();    //考えうる公差の集合

		for (int i = 0; i < n; i++) {
			long x = sc.nextLong();
			set.add(x);
		}

		for (long x : set) {
			for (long z : set) {
				if (z - x != 0) {
					setd.add(Math.abs(z - x));
				}
			}
		}

		long max = 0;
		HashSet<Long> sets = new HashSet<>();    //引き算後の配列の要素の集合
		HashSet<Long> setw = new HashSet<>();    //割り算後の配列の要素の集合
		for (long x : set) {

			sets.clear();
			for (long ｙ : set) {
				sets.add(ｙ - x);
			}

			for (long d : setd) {

				setw.clear();
				for (long s : sets) {
					if (s % d == 0) {
						setw.add(s / d);
					}
				}

				long i = 0;
				long count = 0;

				while (setw.contains(i)) {
					count++;
					i++;
				}

				if (max < count) {
					max = count;
				}

			}
		}

		System.out.println(max);
	}
}
