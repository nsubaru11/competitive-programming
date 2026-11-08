import java.util.*;

import lib.io.*;

public class D {

	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		long t = sc.nextLong();
		String s = sc.next();
		TreeSet<Long> left = new TreeSet<>();
		HashSet<Long> right = new HashSet<>();
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == '1') {
				right.add(sc.nextLong());
			} else {
				left.add(sc.nextLong());
			}
		}
		HashMap<Long, Integer> lef = new HashMap<>();
		int idx = 0;
		for (long l : left) {
			lef.put(l, idx);
			idx++;
		}
		long cnt = 0;
		for (long rig : right) {
			if (left.ceiling(rig) == null) continue;
			int l = lef.get(left.ceiling(rig));
			if (left.floor(rig + 2L * t) == null) continue;
			int r = lef.get(left.floor(rig + 2L * t));
			cnt += r - l + 1;
		}
		System.out.println(cnt);
	}
}
