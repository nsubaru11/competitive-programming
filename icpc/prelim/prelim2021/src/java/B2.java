import java.io.PrintWriter;
import java.util.*;

public class B2 {

	private static int w;	//列
	private static int h;	//行
	private static boolean[][] cell;	//マス目
	private static boolean[] upperFrame; //上枠
	private static boolean[] leftFrame;	//左枠

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while (true) {
			w = Integer.parseInt(sc.next());	//列
			h = Integer.parseInt(sc.next());	//行
			if(w == 0) {
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

			upperFrame = new boolean[w];
			leftFrame = new boolean[h];
			upperFrame[0] = true;

			//上枠左端は0と確定しているためそこから縦に見てtrueの場所を探す。
			changeY(0, 0);
			if (checkFinal()) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}

	private static void changeY(int y, int x) {
		//ある値から縦方向に見てtrueになっているところで、左枠が確定していなければ確定させる。
		for (int Y = y; Y < h; Y++) {
			if (cell[Y][x] == true && leftFrame[Y] == false) {
				leftFrame[Y] = true;
				changeX(Y, x);
			}
		}
		for (int Y = y; Y >= 0; Y--) {
			if (cell[Y][x] == true && leftFrame[Y] == false) {
				leftFrame[Y] = true;
				changeX(Y, x);
			}
		}
	}

	private static void changeX(int y, int x) {
		//ある値から横方向に見てtrueになっているところで、上枠が確定していなければ確定させる。
		for (int X = x; X < w; X++) {
			if (cell[y][X] == true && upperFrame[X] == false) {
				upperFrame[X] = true;
				changeY(y, X);
			}
		}
		for (int X = x; X >= 0; X--) {
			if (cell[y][X] == true && upperFrame[X] == false) {
				upperFrame[X] = true;
				changeY(y, X);
			}
		}
	}

	private static boolean checkFinal() {
		//枠の全てがtrueになっているかのcheckを行います
		for (int i = 0; i < w; i++) {
			if (upperFrame[i] == false) {
				return false;
			}
		}
		for (int i = 0; i < h; i++) {
			if (leftFrame[i] == false) {
				return false;
			}
		}
		return true;
	}

}
