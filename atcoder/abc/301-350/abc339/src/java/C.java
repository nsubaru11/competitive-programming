import lib.io.*;

public class C {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();

		long n = Long.parseLong(sc.next());
		long sum = 0L;
		for (long i = 0L; i < n; i++) {
			sum += Long.parseLong(sc.next());
			if (sum < 0L) {
				sum = 0L;
			}
		}
		System.out.println(sum);
	}
}
