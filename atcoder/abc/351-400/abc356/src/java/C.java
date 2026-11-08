import java.util.*;

import lib.io.*;

public class C {
	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		HashMap<Integer, Character> keySet = new HashMap<>();
		for (int i = 0; i < m; i++) {
			int C = sc.nextInt();
			int keys = 0;
			for (int j = 0; j < C; j++) {
				int A = sc.nextInt();
				keys |= 1 << (A - 1);
			}
			char r = sc.nextChar();
			if (keySet.containsKey(keys)) {
				if (keySet.get(keys) != r) {
					System.out.println(0);
					return;
				}
			}
			keySet.put(keys, r);
		}
		System.out.println(test(k, n, keySet));
	}

	private static int test(int k, int n, HashMap<Integer, Character> keySets) {
		int cnt = 0;
		for (int keySet = 0; keySet < 1 << n; keySet++) {
			boolean flag = true;
			for (int keys : keySets.keySet()) {
				char r = keySets.get(keys);
				if ((r == 'o' && Integer.bitCount(keys & keySet) < k)
						|| (r == 'x' && Integer.bitCount(keys & keySet) >= k)) {
					flag = false;
					break;
				}
			}
			if (flag) cnt++;
		}
		return cnt;
	}
}
