import java.awt.*;

import lib.io.*;

public class ABC086C {

	public static void main(String[] args) {
		FastScanner sc = new FastScanner();
		FastPrinter out = new FastPrinter();

		int n = sc.nextInt();
		Point origin = new Point();
		int curTime = 0;
		for (int i = 0; i < n; i++) {
			int interval = sc.nextInt() - curTime;
			Point cur = new Point(sc.nextInt(), sc.nextInt());
			int abs = Math.abs(origin.x - cur.x) + Math.abs(origin.y - cur.y);
			if (abs > interval || (interval - abs) % 2 != 0) {
				out.println("No");
				out.flush();
				return;
			} else {
				origin.move(cur.x, cur.y);
				curTime += interval;
			}
		}
		out.println("Yes");
		out.flush();
	}
}
