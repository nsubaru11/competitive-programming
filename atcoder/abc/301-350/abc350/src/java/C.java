import lib.io.*;

public class C {

	private static final FastScanner sc = new FastScanner();
	private static final FastPrinter out = new FastPrinter();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[] a = new int[n];
		int[] b = new int[n];
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt() - 1;
			a[i] = x;
			b[x] = i;
		}
		int cnt = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (a[i] != i) {
				sb.append(i + 1).append(" ").append(b[i] + 1).append("\n");
				a[b[i]] = a[i];
				b[a[i]] = b[i];
				cnt++;
			}
		}
		out.println(cnt).print(sb).flush();
	}
}
