import java.util.*;

public class B {

	private static int n;
	private static int m;
	private static int p;
	private static int q;
	private static int[][] a;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while(true) {
			n = Integer.parseInt(sc.next());   //あみだくじの本数
			m = Integer.parseInt(sc.next());   //横棒の数
			p = Integer.parseInt(sc.next());   //出発地点
			q = Integer.parseInt(sc.next());   //期待する到着地点
			if(n == 0) {
				sc.close();
				return;
			}

			int[] x = new int[m];   //横棒の場所を一時保存
			for (int i = 0; i < m; i++) {
				x[i] = Integer.parseInt(sc.next());
			}

			a = new int[m + 2][n + 1];   //あみだくじとマス目に落とし込む
			for (int i = 1; i < n + 1; i++) {
				a[1][i] = i;
			}

			for (int i = 2; i < m + 2; i++) {    //横棒の場所に従って値の入れ替え
				for (int j = 1; j < n + 1; j++) {

					if (j == x[i - 2]) {
						a[i][j] = a[i - 1][j + 1];
						a[i][j + 1] = a[i - 1][j];
						j++;

					} else {
						a[i][j] = a[i - 1][j];
					}
				}
			}

			if (a[m + 1][q] == p) {     //期待到着点に出発点の値がある場合true
				System.out.println("OK");

			} else {
				int[] ans = new int[2];     //ans[0] = x, ans[1] = yとする
				ans = check(1, a[m + 1][q]);    //checkに入れて横棒を加える場所を探る

				if (ans[0] == 0) {
					System.out.println("NG");
				} else {
					System.out.println(ans[0] + " " + ans[1]);
				}
			}
		}
	}

	private static int[] check(int idx, int i) {

		int[] ans = new int[2];
		if (i - 1 > 0 && idx < m + 2 && a[idx][i - 1] == p) {	//左隣を調べる
			ans[0] = i - 1;
			ans[1] = idx - 1;
			return ans;

		} else if (i + 1 < n + 1 && a[idx][i + 1] == p) {	//右隣を調べる
			ans[0] = i;
			ans[1] = idx - 1;
			return ans;

		} else {
			if (i - 1 > 0 && idx + 1 < m + 2 && a[idx + 1][i - 1] == a[idx][i]) {
				ans = check(idx + 1, i - 1);	//左下に移動

			} else if (idx + 1 < m + 2 && a[idx + 1][i] == a[idx][i]) {
				ans = check(idx + 1, i);	//真下に移動

			} else if (i + 1 < n + 1 && idx + 1 < m + 2 && a[idx + 1][i + 1] == a[idx][i]) {
				ans = check(idx + 1, i + 1);	//右下に移動

			} else {
				return ans;
			}
		}
		return ans;
	}

}