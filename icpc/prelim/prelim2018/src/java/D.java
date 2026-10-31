import java.io.PrintWriter;
import java.util.Scanner;

public class D {

	private static int cnt;

	private static void solve(final Scanner sc, final PrintWriter out, int n) {
		int m = sc.nextInt();
		Boolean[][] data = new Boolean[n][n];
		while (m-- > 0) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			data[a][b] = true;
			data[b][a] = false;
		}
		if (!judge(data, n)) {
			out.println(0);
			return;
		}
		int next = updateAndGetNextIdx(data, n);
		if (!judge(data, n)) {
			out.println(0);
			return;
		}
		if (next == -1) {
			out.println(1);
			return;
		}
		cnt = 0;
		dfs(next, data, n);
		out.println(cnt);
	}

	private static void dfs(int next, Boolean[][] data, int n) {
		Boolean[][] data1 = new Boolean[n][n];
		Boolean[][] data2 = new Boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				data1[i][j] = data2[i][j] = data[i][j];
			}
		}
		int i0 = next / n;
		int j0 = next % n;
		data1[i0][j0] = true;
		data1[j0][i0] = false;
		if (judge(data1, n)) {
			int next1 = updateAndGetNextIdx(data1, n);
			if (judge(data1, n)) {
				if (next1 == -1) {
					cnt++;
				} else {
					dfs(next1, data1, n);
				}
			}
		}
		data2[i0][j0] = false;
		data2[j0][i0] = true;
		if (judge(data2, n)) {
			int next2 = updateAndGetNextIdx(data2, n);
			if (judge(data2, n)) {
				if (next2 == -1) {
					cnt++;
				} else {
					dfs(next2, data2, n);
				}
			}
		}
	}

	private static int updateAndGetNextIdx(Boolean[][] data, int n) {
		int max = n / 2;
		boolean isUpdate = true;
		while (isUpdate) {
			isUpdate = false;
			for (int i = 0; i < n; i++) {
				int trueCnt = 0;
				int falseCnt = 0;
				for (int j = 0; j < n; j++) {
					if (i == j) continue;
					if (data[i][j] == null) {
						continue;
					}
					if (data[i][j]) {
						trueCnt++;
					} else {
						falseCnt++;
					}
				}
				if (n == trueCnt + falseCnt) continue;
				if (max == trueCnt) {
					for (int k = 0; k < n; k++) {
						if (i == k) continue;
						if (data[i][k] == null) {
							isUpdate = true;
							data[i][k] = false;
							data[k][i] = true;
						}
					}
				} else if (max == falseCnt) {
					for (int k = 0; k < n; k++) {
						if (i == k) continue;
						if (data[i][k] == null) {
							isUpdate = true;
							data[i][k] = true;
							data[k][i] = false;
						}
					}
				}
			}
		}
		int next = -1;
		outer:
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (i == j) continue;
				if (data[i][j] == null) {
					next = i * n + j;
					break outer;
				}
			}
		}
		return next;
	}

	private static boolean judge(Boolean[][] data, int n) {
		int m = n / 2;
		for (int i = 0; i < n; i++) {
			int t = 0;
			int f = 0;
			for (int j = 0; j < n; j++) {
				if (i == j || data[i][j] == null) continue;
				if (data[i][j]) {
					t++;
				} else {
					f++;
				}
			}
			if (t > m || f > m) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		try (final Scanner sc = new Scanner(System.in);
			 final PrintWriter out = new PrintWriter(System.out)) {
			int n;
			while ((n = sc.nextInt()) != 0) {
				solve(sc, out, n);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
