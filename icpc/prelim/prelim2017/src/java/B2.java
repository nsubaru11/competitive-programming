import java.util.*;

public class B2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			// input
			String S = sc.next();
			if (S.equals(".")) {
				sc.close();
				return;
			}
			String T = sc.next();

			// solve
			if (S.equals(T)) {
				System.out.println("IDENTICAL");
			} else {
				int Sidx = 0;
				int Tidx = 0;
				String subS = "";
				String subT = "";
				boolean f = true;
				while (true) {

					// 最終判定　偶数ver
					if (S.indexOf('"', Sidx) == -1 && T.indexOf('"', Tidx) == -1) {
						subS = S.substring(Sidx);
						subT = T.substring(Tidx);
						System.out.println((subS.equals(subT)) ? "CLOSE": "DIFFERENT");
						break;
					} else if (S.indexOf('"', Sidx) == -1 || T.indexOf('"', Tidx) == -1) {
						System.out.println("DIFFERENT");
						break;
					}

					// 偶数番目の二重引用符から、次の二重引用符までの対応する文字列の比較
					subS = S.substring(Sidx, S.indexOf('"', Sidx));
					subT = T.substring(Tidx, T.indexOf('"', Tidx));
					// 通常時判定
					if (subS.equals(subT)) {
						Sidx = S.indexOf('"', Sidx) + 1;
						Tidx = T.indexOf('"', Tidx) + 1;
					} else {
						System.out.println("DIFFERENT");
						break;
					}

					// 奇数番目の二重引用符から、次の二重引用符までの対応する文字列の比較
					subS = S.substring(Sidx, S.indexOf('"', Sidx));
					subT = T.substring(Tidx, T.indexOf('"', Tidx));
					// 通常時判定
					if (!subS.equals(subT)) {
						if (!f) {
							System.out.println("DIFFERENT");
							break;
						}
						f = false;
					}
					Sidx = S.indexOf('"', Sidx) + 1;
					Tidx = T.indexOf('"', Tidx) + 1;
				}
			}
		}
	}

}