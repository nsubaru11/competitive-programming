import lib.io.*;

public class ABC087B {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		FastPrinter out = new FastPrinter();

		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int x = sc.nextInt();
		int cnt = 0;
		for (int i = 0; i < c + 1; i++) {
			if (x % 100 == 50 && i % 2 == 0 && i != c) i++;
			if (x % 100 == 0 && i % 2 == 1 && i != c) i++;
			if (x < 50 * i) break;
			for (int j = 0; j < b + 1; j++) {
				if (x < (50 * i + 100 * j)) break;
				for (int k = 0; k < a + 1; k++) {
					if (x == (50 * i + 100 * j + 500 * k)) {
						cnt++;
						break;
					}
					if (x < (50 * i + 100 * j + 500 * k)) break;
				}
			}
		}
		out.println(cnt);
		out.flush();
	}
}
