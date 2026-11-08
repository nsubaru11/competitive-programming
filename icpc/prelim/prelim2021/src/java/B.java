import java.io.*;
import java.util.*;

public class B {

	private static int h;
	private static int w;
	private static boolean[][] cell;
	private static HashSet<Integer> nums_X;
	private static HashSet<Integer> nums_Y;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		nums_X = new HashSet<>();
		nums_Y = new HashSet<>();

		while (true) {
			w = Integer.parseInt(sc.next());    //列
			h = Integer.parseInt(sc.next());    //行
			if (w == 0) {
				sc.close();
				out.flush();
				return;
			}

			cell = new boolean[h][w];
			for (int i = 0; i < w + h - 1; i++) {
				int x = Integer.parseInt(sc.next());
				int y = Integer.parseInt(sc.next());
				sc.next();
				cell[y - 1][x - 1] = true; //埋められたマスをfalseにします。
			}

			while (true) {
				//四角形の３つの頂点のが定まれば、もう一つの頂点の値が定まる
				//中の値を考える必要はない
				if (!ope()) {
					if (check()) {
						out.println("YES");
						break;
					} else {
						out.println("NO");
						break;
					}
				}
			}
		}
	}

	private static boolean ope() {
		//値が決定すところをtrueにするために出発地点を定める
		boolean f = false;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (cell[i][j] == true && fillCell(i, j)) {
					f = true;
				}
			}
		}
		return f;
	}

	private static boolean fillCell(int y, int x) {
		//opeで定めた出発地点から見て縦、横方向にtrueがあるマスを記録する
		nums_X.clear();
		nums_Y.clear();
		for (int I = 0; I < h; I++) {
			if (cell[I][x] == true && I != y) {
				nums_Y.add(I);
			}
		}

		for (int J = 0; J < w; J++) {
			if (cell[y][J] == true && x != J) {
				nums_X.add(J);
			}
		}
		//記録した縦と横の
		//交差する地点をtrueにする
		boolean f = false;
		for (int num_x : nums_X) {
			for (int num_y : nums_Y) {
				if (cell[num_y][num_x] == false) {
					cell[num_y][num_x] = true;
					f = true;
				}
			}
		}
		return f;
	}

	private static boolean check() {
		//全てのマスが埋められたかどうかのcheckを行います
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (cell[i][j] == false) {
					return false;
				}
			}
		}
		return true;
	}

}
