import java.util.*;

import lib.io.*;

public class C2 {
	private static final FastScanner sc = new FastScanner();

	public static void main(String[] args) {
		int n = sc.nextInt();    //鍵の所持数
		int m = sc.nextInt();    //テスト回数
		int k = sc.nextInt();    //鍵穴の数

		HashSet<Integer> open = new HashSet<>();    //ドアが開くときの鍵のパターンを覚える
		HashSet<Integer> close = new HashSet<>();    //ドアが開かないときの鍵のパターンを覚える

		for (int i = 0; i < m; i++) {
			int C = sc.nextInt();    //i回目のtestに用いる鍵の数
			int keys = 0;            //keysはi回目のテストで用いる鍵番号を2進数にした総和
			for (int j = 0; j < C; j++) {
				int A = sc.nextInt(); //鍵番号
				keys |= 1 << (A - 1);    //鍵番号を2進数の1が入る場所として覚える、つまりA==3ならkeysに0100(2)を加える
			}
			char r = sc.next().charAt(0);
			if (r == 'o') {
				open.add(keys);
			} else {
				close.add(keys);
			}
		}

		int cnt = 0;
		for (int keySet = 0; keySet < 1 << n; keySet++) {    //2の鍵の本数乗まで確かめる。ここでのkeySetは正しいkeyと仮定したもの
			boolean flag = true;

			for (int o : open) {
				if (Integer.bitCount(o & keySet) < k) {    //openの中のkeyとkeySetの二進数の積集合で1の数がk未満ならfalse
					flag = false;
					break;
				}
			}

			for (int c : close) {
				if (Integer.bitCount(c & keySet) >= k) {    //closeの中のkeyとkeySetの二進数の積集合で1の数がk以上ならfalse
					flag = false;
					break;
				}
			}

			if (flag) cnt++;
		}
		System.out.println(cnt);
	}
}
