import java.util.concurrent.atomic.*;

import lib.io.*;
import lib.search.*;

import static java.lang.Math.*;

public class C_3 {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		long m = sc.nextLong();
		int[] a = sc.nextInt(n);
		long total = 0, lowest = Long.MAX_VALUE, highest = Long.MIN_VALUE;
		for (int v : a) {
			total += v;
			lowest = min(lowest, v);
			highest = max(highest, v);
		}
		if (total <= m) {
			System.out.println("infinite");
		} else if (lowest * n >= m) {
			System.out.println(m / n);
		} else {
			int ans = BinarySearch.upperBound((int) lowest, (int) highest + 1, mid -> {
				long sum = 0;
				for (long b : a) sum += min(b, mid);
				return sum > m ? 1 : sum == m ? 0 : -1;
			});
			System.out.println(ans < 0 ? ~ans - 1 : ans);
		}
	}
}
