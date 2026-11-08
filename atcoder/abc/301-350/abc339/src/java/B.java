import java.util.*;

import lib.io.*;

public class B {

	private static int h;
	private static int w;
	private static int n;

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();

		h = Integer.parseInt(sc.next());
		w = Integer.parseInt(sc.next());
		n = Integer.parseInt(sc.next());
		char[][] map = new char[h][w];
		for (int i = 0; i < h; i++) {
			Arrays.fill(map[i], '.');
		}
		map = operate(map, 0, 0, 'U', 0);
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println("");
		}
	}

	private static char[][] operate(char[][] map, int i, int j, char dir, int idx) {
		if (idx == n) {
			return map;
		}
		if (i == h) {
			i = 0;
		}
		if (i == -1) {
			i = h - 1;
		}
		if (j == w) {
			j = 0;
		}
		if (j == -1) {
			j = w - 1;
		}
		if (map[i][j] == '.') {
			map[i][j] = '#';
			if (dir == 'U') {
				operate(map, i, j + 1, 'R', idx + 1);
			} else if (dir == 'R') {
				operate(map, i + 1, j, 'D', idx + 1);
			} else if (dir == 'D') {
				operate(map, i, j - 1, 'L', idx + 1);
			} else if (dir == 'L') {
				operate(map, i - 1, j, 'U', idx + 1);
			}

		} else {
			map[i][j] = '.';
			if (dir == 'U') {
				operate(map, i, j - 1, 'L', idx + 1);
			} else if (dir == 'L') {
				operate(map, i + 1, j, 'D', idx + 1);
			} else if (dir == 'D') {
				operate(map, i, j + 1, 'R', idx + 1);
			} else if (dir == 'R') {
				operate(map, i - 1, j, 'U', idx + 1);
			}
		}
		return map;
	}
}
