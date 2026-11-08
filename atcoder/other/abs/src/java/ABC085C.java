import java.util.*;

import lib.io.*;

public class ABC085C {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		FastPrinter out = new FastPrinter();

		long n = sc.nextLong();
		long y = sc.nextLong();
		for (long i = 0L; i <= n; i++) {
			for (long j = 0L; j <= n - i; j++) {
				if (y == (i * 10000L + j * 5000L + (n - i - j) * 1000L)) {
					StringJoiner sj = new StringJoiner(" ");
					sj.add(Long.toString(i));
					sj.add(Long.toString(j));
					sj.add(Long.toString(n - i - j));
					out.println(sj);
					out.flush();
					return;
				}

			}
		}
		StringJoiner sj = new StringJoiner(" ");
		sj.add(Integer.toString(-1));
		sj.add(Integer.toString(-1));
		sj.add(Integer.toString(-1));
		out.println(sj);
		out.flush();
	}
}
