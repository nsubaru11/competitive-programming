import lib.io.*;

public class D {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		FastPrinter out = new FastPrinter();

		long n = sc.nextLong();
		if (n <= 10) {
			out.println(n - 1);
		} else {
			long sum = 10L;
			int i = 2;
			long add = 9L;
			while (sum + add <= n) {
				sum += add;
				i++;
				if (i % 2 == 1) {
					add *= 10L;
				}
			}
			sum++;
			long strt = 1 * Math.round(Math.pow(10, (i - 1) / 2)) + n - sum;
			String str = Long.toString(strt);
			int len = str.length();
			StringBuilder sb = new StringBuilder(str);
			if (i % 2 == 0) {
				for (int j = len - 1; j >= 0; j--) {
					sb.append(str.charAt(j));
				}
			} else {
				for (int j = len - 2; j >= 0; j--) {
					sb.append(str.charAt(j));
				}
			}
			out.println(sb);
		}
		out.flush();
	}
}
