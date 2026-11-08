import lib.io.*;

public class Main {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		// input
		int n = sc.nextInt();// 長方形の個数
		int t = sc.nextInt();// 操作ターン回数
		int s = sc.nextInt();// 計測誤差標準偏差
		int[] w = new int[n];// 各長方形の横幅
		int[] h = new int[n];// 各長方形の縦
		for (int i = 0; i < n; i++) {
			w[i] = sc.nextInt();
			h[i] = sc.nextInt();
		}

		sc.nextLine();
		out.println(n);
		for (int i = 0; i < n; i++) {
			if (w[i] > h[i]) {
				out.print(i, 1, "U", -1);
			} else {
				out.print(i, 0, "U", -1);
			}
			out.println();
		}
		// solve
		while (t-- > 0) {
			out.flush();
		}
	}
}
