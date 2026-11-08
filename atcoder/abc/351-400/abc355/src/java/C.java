import java.util.*;

import lib.io.*;

public class C {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int t = sc.nextInt();
		HashMap<Integer, Integer> vertical = new HashMap<>();
		HashMap<Integer, Integer> horizontal = new HashMap<>();
		for (int i = 0, a = 0, b = 0; i < t; i++) {
			int x = sc.nextInt();
			int ver = (x - 1) / n;
			int hor = (x - 1) % n;
			if (ver == hor) a++;
			if (ver + hor == n - 1) b++;
			if (vertical.get(ver) == null || horizontal.get(hor) == null) {
				if (horizontal.get(hor) == null) {
					horizontal.put(hor, 1);
				} else {
					horizontal.put(hor, horizontal.get(hor) + 1);
				}
				if (vertical.get(ver) == null) {
					vertical.put(ver, 1);
				} else {
					vertical.put(ver, vertical.get(ver) + 1);
				}
			} else {
				vertical.put(ver, vertical.get(ver) + 1);
				horizontal.put(hor, horizontal.get(hor) + 1);
			}
			if (vertical.get(ver) == n || horizontal.get(hor) == n || a == n || b == n) {
				System.out.println(i + 1);
				return;
			}
		}
		System.out.println(-1);
	}
}
