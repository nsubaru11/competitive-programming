import lib.io.*;

import static java.lang.Math.*;

public class C {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		long Sx = sc.nextLong();
		long Sy = sc.nextLong();
		long Tx = sc.nextLong();
		long Ty = sc.nextLong();

		long ans = abs(Ty - Sy);
		if (abs(Tx - Sx) > abs(Ty - Sy)) {
			if ((Sx + Sy) % 2L == 0L) {
				if (Sx < Tx) {
					Sx++;
					Sx += abs(Ty - Sy);
				} else {
					Sx -= abs(Ty - Sy);
				}
			} else {
				if (Sx > Tx) {
					Sx--;
					Sx -= abs(Ty - Sy);
				} else {
					Sx += abs(Ty - Sy);
				}
			}
			ans += (abs(Tx - Sx) + 1L) / 2L;
		}
		System.out.println(ans);
	}
}
