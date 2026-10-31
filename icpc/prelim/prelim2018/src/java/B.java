import java.util.*;

public class B {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			int n = Integer.parseInt(sc.next());	//　幅
			if (n == 0) {
				sc.close();
				return;
			}
			int m = Integer.parseInt(sc.next());	//　高さ
			int t = Integer.parseInt(sc.next());	//　折る操作の回数
			int p = Integer.parseInt(sc.next());	//　穴をあける操作の回数

			// solve
			int[][]a = new int[m * (m + 1) / 2][n * (n + 1) / 2];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					a[i][j] = 1;
				}
			}
			int originX = 0;
			int originY = 0;
			for (int i = 0; i < t; i++) {
				int d = Integer.parseInt(sc.next());
				int c = Integer.parseInt(sc.next());
				if (d == 1) {
					n -= c;
					if (n < c) {
						n += c - n;
					}
					originX += c;
				} else {
					m -= c;
					if (m < c) {
						m += c - m;
					}
					originY += c;
				}
				fold(d, c, a, n, m, originX, originY);
			}
			// output
			int sum = 0;
			for (int i = 0; i < p; i++) {
				int x = Integer.parseInt(sc.next());
				int y = Integer.parseInt(sc.next());
				sum = a[originY + y][originX + x];
				System.out.println(sum);
			}
		}
	}

	private static void fold(int x, int wide, int[][] a, int n, int m, int originX, int originY) {
		// 折る作業
		if (x == 1) {
			for (int i = originY; i < m + originY; i++) {
				for (int j = 0; j < wide; j++) {
					a[i][originX + j] += a[i][originX - j - 1];
				}
			}
		} else {
			for (int i = originX; i < n + originX; i++) {
				for (int j = 0; j < wide; j++) {
					a[originY + j][i] += a[originY - j - 1][i];
				}
			}
		}
	}

}
