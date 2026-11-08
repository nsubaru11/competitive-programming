import java.util.*;

import lib.io.*;

public class C {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		long n = Long.parseLong(sc.next());
		System.out.println(operate(n, new HashMap<Long, Long>()));
	}

	private static long operate(long n, HashMap<Long, Long> memo) {
		if (memo.get(n) != null) return memo.get(n);
		if (n == 1) {
			memo.put(1L, 0L);
			return memo.get(1L);
		}
		memo.put(n, n + operate(n / 2L, memo) + operate((n + 1L) / 2L, memo));
		return memo.get(n);
	}
}
