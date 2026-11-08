import java.awt.*;

import lib.io.*;

public class B {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		Point a = new Point(sc.nextInt(), sc.nextInt());
		Point b = new Point(sc.nextInt(), sc.nextInt());
		Point c = new Point(sc.nextInt(), sc.nextInt());

		int x = (int) a.distanceSq(b);
		int y = (int) a.distanceSq(c);
		int z = (int) b.distanceSq(c);
		if (x + y == z || x + z == y || y + z == x) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}
}
