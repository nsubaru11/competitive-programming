import lib.io.*;

public class D {

	private static final FastPrinter out = new FastPrinter();
	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int[][][] cube = sc.nextIntPrefixSum(n, n, n);
		int q = sc.nextInt();
		int sum1 = 0, sum2 = 0;
		for (int i = 0; i < q; i++) {
			int lx = sc.nextInt(), rx = sc.nextInt();
			int ly = sc.nextInt(), ry = sc.nextInt();
			int lz = sc.nextInt(), rz = sc.nextInt();
			sum1 = cube[rx][ry][rz] - cube[rx][ry][lz - 1] - cube[rx][ly - 1][rz] + cube[rx][ly - 1][lz - 1];
			sum2 = -cube[lx - 1][ry][rz] + cube[lx - 1][ly - 1][rz] + cube[lx - 1][ry][lz - 1]
					- cube[lx - 1][ly - 1][lz - 1];
			out.println(sum1 + sum2);
		}
		out.flush();
	}
}
