import java.io.PrintWriter;
import java.util.*;

import static java.util.Arrays.*;
import static java.lang.Math.*;
//import static java.lang.System.out;

public class A {

	private static final Scanner sc = new Scanner(System.in);
	private static final PrintWriter out = new PrintWriter(System.out);
	private static final TreeSet<Point> ts = new TreeSet<>();

	private static void solve(int h) {
		int w = sc.nextInt();
		out.println(ts.higher(new Point(h, w)));
	}

	private static class Point implements Comparable<Point> {
		private int h, w;

		public Point(int h, int w) {
			this.h = h;
			this.w = w;
		}

		@Override
		public int compareTo(Point o) {
			int d1 = h * h + w * w;
			int d2 = o.h * o.h + o.w * o.w;
			return d1 == d2 ? h - o.h : d1 - d2;
		}

		@Override
		public String toString() {
			return h + " " + w;
		}
	}

	public static void main(String[] args) {
		try {
			// 最初の入力
			int h;
			for (int i = 1; i <= 150; i++) {
				for (int j = i + 1; j <= 150; j++) {
					ts.add(new Point(i, j));
				}
			}
			while ((h = sc.nextInt()) != 0) {
				solve(h); // メインロジック
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
			out.close();
		}
	}

}
