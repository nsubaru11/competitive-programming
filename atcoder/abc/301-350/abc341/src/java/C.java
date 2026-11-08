import lib.io.*;

public class C {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();

		int h = Integer.parseInt(sc.next());
		int w = Integer.parseInt(sc.next());
		int n = Integer.parseInt(sc.next());
		String t = sc.next();
		Character[][] map = new Character[h][w];
		for (int i = 0; i < h; i++) {
			String s = sc.next();
			for (int j = 0; j < w; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		System.out.println(search(map, h, w, n, t));
	}

	private static int search(Character[][] map, int h, int w, int n, String t) {
		int count = 0;
		for (int i = 1; i < h - 1; i++) {
			for (int j = 1; j < w - 1; j++) {
				if (map[i][j] == '.') {
					if (move(t, map, i, j, 0, n)) {
						count++;
					}
				}
			}
		}
		return count;
	}

	private static boolean move(String t, Character[][] map, int i, int j, int idx, int n) {
		boolean f = false;
		if (idx == n) {
			return true;
		}
		if (t.charAt(idx) == 'L') {
			if (map[i][j - 1] == '.') {
				f = move(t, map, i, j - 1, idx + 1, n);
			} else {
				f = false;
				return f;
			}
		} else if (t.charAt(idx) == 'R') {
			if (map[i][j + 1] == '.') {
				f = move(t, map, i, j + 1, idx + 1, n);
			} else {
				f = false;
				return f;
			}
		} else if (t.charAt(idx) == 'U') {
			if (map[i - 1][j] == '.') {
				f = move(t, map, i - 1, j, idx + 1, n);
			} else {
				f = false;
				return f;
			}
		} else if (t.charAt(idx) == 'D') {
			if (map[i + 1][j] == '.') {
				f = move(t, map, i + 1, j, idx + 1, n);
			} else {
				f = false;
				return f;
			}
		}
		return f;
	}
}
